package com.example.controller;

import com.example.model.User;
import com.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;

@Controller
public class LoginController {

    @Autowired
    private UserService userService;

    @GetMapping("/login")
    public String showLoginPage() {
        return "login";  // Display the login page
    }

    @PostMapping("/login")
    public String loginUser(String email, String password, Model model, HttpSession session) {
        // Attempt to authenticate the user
        User user = userService.loginUser(email, password);

        if (user != null) {
            // Set user in session to keep them logged in
            session.setAttribute("user", user);
            session.setAttribute("role", user.getRole());
            session.setAttribute("userId", user.getId());  // Save userId in session

            // Redirect based on role
            if ("schoolAdmin".equals(user.getRole())) {
                return "redirect:/school/dashboard"; // Redirect to school crew list
            } else if ("tvpssAdmin".equals(user.getRole())) {
                return "redirect:/admin/dashboard"; // Redirect to admin crew list
            } else {
                model.addAttribute("message", "You don't have access to this system.");
                return "login"; // Show login again if role doesn't match
            }
        }

        model.addAttribute("message", "Invalid email or password. Please try again.");
        return "login";  // Return to login page with error message
    }
}
