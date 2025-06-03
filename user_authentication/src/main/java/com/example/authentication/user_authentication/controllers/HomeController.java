package com.example.authentication.user_authentication.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import com.example.authentication.user_authentication.dao.UserRepository;
import com.example.authentication.user_authentication.entities.User;
import jakarta.servlet.http.HttpSession;
import com.example.authentication.user_authentication.helper.Message;

@Controller
public class HomeController {

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;
    
    @GetMapping("/")
    public String Home(Model model)
    {
        model.addAttribute("title", "Home");
        return "home";
    }

    @GetMapping("/signup")
    public String signup(Model model) {
        model.addAttribute("title", "Register");
        model.addAttribute("user", new User());  // Changed to lowercase
        return "signup";
    }

    @GetMapping("/login")
    public String login(Model model)
    {
        model.addAttribute("title", "Login");
        return "login";
    }

    //handler for registration
    @PostMapping("/do_register")
    public String registerUser(
            @ModelAttribute("user") User user,
            HttpSession session) {
        try {
            // Encrypt the password
            user.setPassword(passwordEncoder.encode(user.getPassword()));

            // Set other default values (e.g., roles, enabled status)
            user.setRole("ROLE_USER");

            // Save the user to the database
            userRepository.save(user);

            // Set success message
            session.setAttribute("message", new Message("User registered successfully!", "success"));
            return "redirect:/login";
        } catch (Exception e) {
            e.printStackTrace();
            session.setAttribute("message", new Message("Something went wrong! " + e.getMessage(), "danger"));
            return "redirect:/signup";
        }
    }
}

