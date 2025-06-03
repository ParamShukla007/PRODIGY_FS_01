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
import org.springframework.web.bind.annotation.RequestParam;

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
    public String registerUser(@ModelAttribute("user") User user, 
                         @RequestParam("agreement") boolean agreement,
                         Model model,
                         HttpSession session) {
        try {
            if (!agreement) {
                throw new Exception("You have not agreed to the terms and conditions");
            }
            
            // Set password encoding
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            
            // Validate role (security check)
            if (!user.getRole().equals("USER") && !user.getRole().equals("ADMIN")) {
                throw new Exception("Invalid role selected");
            }
            
            this.userRepository.save(user);
            
            model.addAttribute("user", new User());
            session.setAttribute("message", new Message("Successfully Registered!", "alert-success"));
            return "redirect:/login";
            
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("user", user);
            session.setAttribute("message", new Message("Something went wrong!" + e.getMessage(), "alert-danger"));
            return "redirect:/signup";
        }
    }
}

