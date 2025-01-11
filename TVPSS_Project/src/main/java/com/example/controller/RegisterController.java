package com.example.controller;

import com.example.model.User;
import com.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
@Controller
public class RegisterController {
   @Autowired
   private UserService userService;
   @GetMapping("/register")
   public String showRegisterPage(Model model) {
       model.addAttribute("user", new User());
       return "register"; // Return to the registration form view
   }
   @PostMapping("/register")
   public String registerUser(@ModelAttribute("user") User user, Model model) {
       if (userService.registerUser(user)) {
           model.addAttribute("message", "Registration successful!");
           return "login"; // Redirect to login page after successful registration
       } else {
           model.addAttribute("error", "Email already in use. Please try again.");
           return "register"; // Return back to registration form
       }
   }
}
