package com.example.controller;

import com.example.model.User;
import com.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/UserController")
    public String registerUser(@ModelAttribute User user, Model model) {
        boolean isRegistered = userService.registerUser(user);

        if (isRegistered) {
            // Redirect based on user role
            if ("schoolAdmin".equals(user.getRole())) {
                return "redirect:/school/crew/crewList";  // Redirect to school admin crew list
            } else if ("tvpssAdmin".equals(user.getRole())) {
                return "redirect:/admin/crew/crewList";  // Redirect to admin crew list
            }
        }

        // In case of any error (including registration failure or invalid role), return to the registration page with a message
        model.addAttribute("message", "Registration failed. Email already exists or other error.");
        return "register";  // Show registration form again with error message
    }
}
