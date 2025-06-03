package com.example.authentication.user_authentication.controllers;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import com.example.authentication.user_authentication.dao.UserRepository;
import com.example.authentication.user_authentication.entities.User;
import com.example.authentication.user_authentication.helper.Message;

import jakarta.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    
    @ModelAttribute
    public void addCommonAttributes(Model model, Principal principal) {
        if (principal != null) {
            String username = principal.getName();
            User user = userRepository.getUserByUserEmail(username);
            model.addAttribute("user", user);
        }
    }

    @GetMapping("/dashboard1")
    public String dashboard(Model model, Principal principal) {
        String username = principal.getName();
        User admin = userRepository.getUserByUserEmail(username);
        model.addAttribute("admin", admin);
        return "admin_dashboard";
    }
    
    // @GetMapping("/users")
    // public String usersList(Model model) {
    //     List<User> users = userRepository.findAll();
    //     model.addAttribute("users", users);
    //     return "admin_users";
    // }

    @GetMapping("/profile1")
    public String profile(Model m, Principal p)
    {
        String userName = p.getName();
        User user = userRepository.getUserByUserEmail(userName);
        m.addAttribute("user", user);
        m.addAttribute("title", "Profile");
        return "admin_profile";
    }

    @PostMapping("/update-password1/{user_id}")
    public String updatePassword(@PathVariable("user_id") Integer user_id, Model m)
    {
        m.addAttribute("title", "Update User Password");
        User user = this.userRepository.findById(user_id).get();
        m.addAttribute("user", user);
        return "admin_update_password";
    }

     //update user password
    @PostMapping("/process-update-password1")
    public String updateUserPassword(
            @RequestParam("newPassword") String newPassword,
            @RequestParam("confirmPassword") String confirmPassword,
            Principal principal,
            HttpSession session) {
        try {
            // Validate that the passwords match
            if (!newPassword.equals(confirmPassword)) {
                session.setAttribute("message", new Message("Passwords do not match!", "danger"));
                return "redirect:/admin/update-password1/" + userRepository.getUserByUserEmail(principal.getName()).getUser_id();
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
        return "redirect:/admin/profile1";
    }

    @GetMapping("/users")
    public String usersList(Model model) {
        List<User> users = userRepository.findAll();
        model.addAttribute("users", users);
        model.addAttribute("title", "User Management");
        return "admin_users";
}
}
