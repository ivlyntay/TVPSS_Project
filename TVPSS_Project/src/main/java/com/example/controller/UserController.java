package com.example.controller;

import com.example.model.User;
import com.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
@Controller
@RequestMapping("/school/profile")
public class UserController {

    @Autowired
    private UserService userService;

    // Ensure the user is authenticated before accessing the profile
    private boolean isAuthenticated(HttpSession session) {
        return session.getAttribute("user") != null;  // Check if user is logged in (using session)
    }

    // View Profile - Display the current profile details
    @GetMapping("/profile")
    public String viewProfile(HttpSession session, Model model) {
        if (!isAuthenticated(session)) {
            return "redirect:/login";  // Redirect to login if the user is not authenticated
        }

        // Get the logged-in user from the session
        User user = (User) session.getAttribute("user");
        
        // Debugging log to check if the user is found in the session
        if (user == null) {
            System.out.println("No user found in session!");
        } else {
            System.out.println("User found: " + user.getEmail());
        }

        // Add the user to the model to display profile information
        model.addAttribute("user", user);  // Pass the user object to the view

        return "school/profile/profile";  // Return the correct template location
    }

    // Edit Profile - Show a form with the current user's data pre-filled
    @GetMapping("/editProfile")
    public String editProfile(HttpSession session, Model model) {
        if (!isAuthenticated(session)) {
            return "redirect:/login";  // Redirect to login if the user is not authenticated
        }

        User user = (User) session.getAttribute("user");  // Retrieve the logged-in user from the session
        model.addAttribute("user", user);  // Pass the user object to the form
        return "editProfile";  // The JSP or Thymeleaf template for editing the profile
    }

    // Update Profile - Handle the form submission to update the user's details
    @PostMapping("/updateProfile")
    public String updateProfile(@ModelAttribute("user") User user, HttpSession session, RedirectAttributes redirectAttributes) {
        if (!isAuthenticated(session)) {
            return "redirect:/login";  // Redirect to login if the user is not authenticated
        }

        try {
            User loggedInUser = (User) session.getAttribute("user");  // Retrieve the logged-in user from the session
            user.setId(loggedInUser.getId());  // Ensure the user ID is preserved during the update
            userService.updateUser(user);  // Update the user in the database

            // Update the session with the new user details
            session.setAttribute("user", user);

            redirectAttributes.addFlashAttribute("message", "Profile updated successfully.");
            return "redirect:/school/profile";  // Redirect to the profile page after successful update
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error updating profile: " + e.getMessage());
            return "redirect:/school/editProfile";  // Redirect back to the edit profile page if an error occurs
        }
    }
}
