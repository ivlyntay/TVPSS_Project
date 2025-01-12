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
        return "school/profile/editProfile";  // The JSP or Thymeleaf template for editing the profile
    }

    // Update Profile - Handle the form submission to update the user's details
    @PostMapping("/updateProfile")
    public String updateProfile(@ModelAttribute("user") User user, HttpSession session, RedirectAttributes redirectAttributes) {
        if (!isAuthenticated(session)) {
            return "redirect:/login";  // Redirect to login if the user is not authenticated
        }

        try {
            User loggedInUser = (User) session.getAttribute("user");
            user.setId(loggedInUser.getId());  // Preserve the user ID

            // Debug: Print user data before update
            System.out.println("Updating user: " + user);

            userService.updateUser(user);  // Update in the database

            // Update session user data
            session.setAttribute("user", user);

            // Add success message
            redirectAttributes.addFlashAttribute("message", "Profile updated successfully.");
            return "redirect:/school/profile/profile";  // Redirect to profile page
        } catch (Exception e) {
            // Add error message
            redirectAttributes.addFlashAttribute("error", "Error updating profile: " + e.getMessage());
            return "redirect:/school/profile/editProfile";  // Redirect back to edit form
        }
    }
    
    @GetMapping("/settings")
    public String settings(HttpSession session, Model model) {
        if (!isAuthenticated(session)) {
            return "redirect:/login";
        }

        // Add the active page attribute for highlighting the current menu item
        model.addAttribute("activePage", "settings");

        User user = (User) session.getAttribute("user");
        if (user != null) {
            model.addAttribute("user", user);  // Add user to the model to be used in the view
        }
        return "school/profile/settings";  // Ensure settings.html is located in the correct directory
    }

    @PostMapping("/updatePassword")
    public String updatePassword(@RequestParam("currentPassword") String currentPassword,
                                 @RequestParam("newPassword") String newPassword,
                                 @RequestParam("confirmPassword") String confirmPassword,
                                 HttpSession session, RedirectAttributes redirectAttributes) {
        if (!isAuthenticated(session)) {
            return "redirect:/login";
        }
        User loggedInUser = (User) session.getAttribute("user");
        if (!loggedInUser.getPassword().equals(currentPassword)) {
            redirectAttributes.addFlashAttribute("error", "Current password is incorrect.");
            return "redirect:/school/profile/settings";
        }
        if (!newPassword.equals(confirmPassword)) {
            redirectAttributes.addFlashAttribute("error", "New password and confirm password do not match.");
            return "redirect:/school/profile/settings";
        }
        try {
            loggedInUser.setPassword(newPassword);
            userService.updateUser(loggedInUser);
            session.setAttribute("user", loggedInUser);
            redirectAttributes.addFlashAttribute("message", "Password updated successfully.");
            return "redirect:/school/profile/settings";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error updating password: " + e.getMessage());
            return "redirect:/school/profile/settings";
        }
    }
    

}
