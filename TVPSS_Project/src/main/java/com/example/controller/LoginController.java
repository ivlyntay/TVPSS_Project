package com.example.controller;

import com.example.model.User;
import com.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LoginController {

    @Autowired
    private UserService userService;

    @GetMapping("/login")
    public String showLoginPage() {
        return "login";  // Display the login page
    }

    @PostMapping("/login")
    public String loginUser(String email, String password, Model model) {
        User user = userService.loginUser(email, password);

        if (user != null) {
            if ("schoolAdmin".equals(user.getRole())) {
                return "redirect:/school/crew/crewList";
            } else if ("tvpssAdmin".equals(user.getRole())) {
                return "redirect:/admin/crew/crewList";
            }
        }

        model.addAttribute("message", "Invalid email or password. Please try again.");
        return "login";
    }
}
