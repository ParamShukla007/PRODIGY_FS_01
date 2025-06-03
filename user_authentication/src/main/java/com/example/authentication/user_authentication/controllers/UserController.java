package com.example.authentication.user_authentication.controllers;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import com.example.authentication.user_authentication.helper.Message;
import com.example.authentication.user_authentication.dao.UserRepository;
import com.example.authentication.user_authentication.entities.User;

import jakarta.servlet.http.HttpSession;

import org.springframework.ui.Model;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    // Add this method to make user available in all templates
    @ModelAttribute
    public void addCommonAttributes(Model model, Principal principal) {
        if (principal != null) {
            String username = principal.getName();
            User user = userRepository.getUserByUserEmail(username);
            model.addAttribute("user", user);
        }
    }

    @GetMapping("/dashboard")
    public String dashboard(Model model, Principal principal)
    {
        String username = principal.getName();
        //get the userd details using username(email)
        User user = userRepository.getUserByUserEmail(username);
        System.out.println("User: " + user);
        model.addAttribute("user", user);
        return "user_dashboard";
    }

    @GetMapping("/profile")
    public String profile(Model m, Principal p)
    {
        String userName = p.getName();
        User user = userRepository.getUserByUserEmail(userName);
        m.addAttribute("user", user);
        m.addAttribute("title", "Profile");
        return "profile";
    }

      @PostMapping("/update-password/{user_id}")
    public String updatePassword(@PathVariable("user_id") Integer user_id, Model m)
    {
        m.addAttribute("title", "Update User Password");
        User user = this.userRepository.findById(user_id).get();
        m.addAttribute("user", user);
        return "update_password";
    }

     //update user password
    @PostMapping("/process-update-password")
    public String updateUserPassword(
            @RequestParam("newPassword") String newPassword,
            @RequestParam("confirmPassword") String confirmPassword,
            Principal principal,
            HttpSession session) {
        try {
            // Validate that the passwords match
            if (!newPassword.equals(confirmPassword)) {
                session.setAttribute("message", new Message("Passwords do not match!", "danger"));
                return "redirect:/user/update-password/" + userRepository.getUserByUserEmail(principal.getName()).getUser_id();
            }

            // Get the currently logged-in user
            String userName = principal.getName();
            User oldUser = userRepository.getUserByUserEmail(userName);

            // Update the password
            oldUser.setPassword(passwordEncoder.encode(confirmPassword));
            this.userRepository.save(oldUser);

            // Success message
            session.setAttribute("message", new Message("Password updated successfully!", "success"));
        } catch (Exception e) {
            e.printStackTrace();
            session.setAttribute("message", new Message("Something went wrong! " + e.getMessage(), "danger"));
        }
        return "redirect:/user/profile";
    }

}
