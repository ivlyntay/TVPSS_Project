package com.example.controller;

import com.example.model.User;
import com.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/auth")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/login")
    public String showLoginForm() {
        return "auth/login"; // Return the login view (login.jsp or Thymeleaf template)
    }

    @PostMapping("/login")
    public String login(@RequestParam String email, @RequestParam String password, HttpSession session, Model model) {
        User user = userService.getUserByEmailAndPassword(email, password);
        if (user != null) {
            session.setAttribute("loggedInUser", user); // Store the logged-in user in session
            return "redirect:/school/crew/crewList"; // Redirect to the dashboard or another page
        } else {
            return "login"; // Return to login page if authentication fails
        }
    }


    @GetMapping("/register")
    public String showRegistrationForm() {
        return "register"; // Return the registration view (register.jsp or Thymeleaf template)
    }

    @PostMapping("/register")
    public String register(@RequestParam String email, @RequestParam String password, @RequestParam String fullName,
                           @RequestParam String icNumber, @RequestParam String schoolName, @RequestParam String district,
                           @RequestParam String contactNumber, @RequestParam String role, Model model) {
        if (userService.getUserByEmailAndPassword(email, password) != null) {
            model.addAttribute("message", "Email already exists. Try another.");
            return "register"; // Return to registration page with error
        } else {
            // Register user
            User newUser = new User(generateId(), fullName, icNumber, schoolName, district, email, contactNumber, password, role);
            userService.registerUser(newUser);

            // Store user data in session
            model.addAttribute("message", "Registration successful! You can now login.");
            return "login"; // Return to login page after successful registration
        }
    }

    private int generateId() {
        return (int) (Math.random() * 1000); // A simple id generator for now
    }
}
