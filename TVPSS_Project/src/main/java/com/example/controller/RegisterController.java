package com.example.controller;
import com.example.model.User;
import com.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
@Controller
public class RegisterController {
   @Autowired
   private UserService userService;
   @GetMapping("/register")
   public String showRegisterPage(Model model) {
       model.addAttribute("user", new User()); // Pass an empty User object to the form
       return "register"; // JSP view for the registration form
   }
   @PostMapping("/register")
   public String registerUser(
           @ModelAttribute("user") User user,
           RedirectAttributes redirectAttributes) {
      
       // Validate email format
       if (!isValidEmail(user.getEmail())) {
           redirectAttributes.addFlashAttribute("error", "Invalid email format.");
           return "redirect:/register";
       }
       // Validate password length
       if (!isValidPassword(user.getPassword())) {
           redirectAttributes.addFlashAttribute("error", "Password must be at least 8 characters long.");
           return "redirect:/register";
       }
       // Attempt to register the user
       boolean isRegistered = userService.registerUser(user);
       if (isRegistered) {
           redirectAttributes.addFlashAttribute("message", "Registration successful! Please log in.");
           return "redirect:/login"; // Redirect to the login page
       } else {
           redirectAttributes.addFlashAttribute("error", "Email already in use. Please try again.");
           return "redirect:/register"; // Redirect back to the registration page
       }
   }
   private boolean isValidEmail(String email) {
       return email != null && email.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$");
   }
   private boolean isValidPassword(String password) {
       return password != null && password.length() >= 8;
   }
}