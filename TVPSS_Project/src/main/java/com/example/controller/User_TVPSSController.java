package com.example.controller;

import com.example.model.User;
import com.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.List;
@Controller
@RequestMapping("/admin")
public class User_TVPSSController {

    @Autowired
    private UserService userService;

    // Ensure the user is authenticated before accessing the admin area
    private boolean isAuthenticated(HttpSession session) {
        return session.getAttribute("user") != null;
    }

    // ------------------- Profile Section -------------------

    @GetMapping("/profile/profile")
    public String viewProfile(HttpSession session, Model model) {
        if (!isAuthenticated(session)) {
            return "redirect:/login";
        }
        User user = (User) session.getAttribute("user");
        
        // Add the logged-in user to the model
        model.addAttribute("loggedInUser", user);

        model.addAttribute("user", user);
        return "admin/profile/profile";
    }

    @GetMapping("/profile/editProfile")
    public String editProfile(HttpSession session, Model model) {
        if (!isAuthenticated(session)) {
            return "redirect:/login";
        }
        User user = (User) session.getAttribute("user");

        // Add the logged-in user to the model
        model.addAttribute("loggedInUser", user);

        model.addAttribute("user", user);
        return "admin/profile/editProfile";
    }

    @PostMapping("/profile/updateProfile")
    public String updateProfile(@ModelAttribute("user") User user, HttpSession session, RedirectAttributes redirectAttributes) {
        if (!isAuthenticated(session)) {
            return "redirect:/login";
        }
        User loggedInUser = (User) session.getAttribute("user");
        user.setId(loggedInUser.getId());
        userService.updateUser(user);
        session.setAttribute("user", user);

        redirectAttributes.addFlashAttribute("message", "Profile updated successfully.");
        return "redirect:/admin/profile";
    }

    @GetMapping("/profile/settings")
    public String settings(HttpSession session, Model model) {
        if (!isAuthenticated(session)) {
            return "redirect:/login";
        }

        User user = (User) session.getAttribute("user");

        // Add the logged-in user to the model
        model.addAttribute("loggedInUser", user);

        model.addAttribute("user", user);  // Add user to the model to be used in the view
        return "admin/profile/settings";  // Ensure settings.html is located in the correct directory
    }

    @PostMapping("/profile/updatePassword")
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
            return "redirect:/admin/profile/settings";
        }
        if (!newPassword.equals(confirmPassword)) {
            redirectAttributes.addFlashAttribute("error", "New password and confirm password do not match.");
            return "redirect:/admin/profile/settings";
        }
        try {
            loggedInUser.setPassword(newPassword);
            userService.updateUser(loggedInUser);
            session.setAttribute("user", loggedInUser);
            redirectAttributes.addFlashAttribute("message", "Password updated successfully.");
            return "redirect:/admin/profile/settings";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error updating password: " + e.getMessage());
            return "redirect:/admin/profile/settings";
        }
    }

    // ------------------- User Management Section -------------------

    @GetMapping("/user/userList")
    public String listUsers(HttpSession session, Model model) {
        if (!isAuthenticated(session)) {
            return "redirect:/login"; // Redirect to login if not authenticated
        }

        // Add the logged-in user's details to the model
        User loggedInUser = (User) session.getAttribute("user");
        model.addAttribute("loggedInUser", loggedInUser); // This will hold the logged-in user's data

        // Fetch only users with the role "schoolAdmin"
        List<User> userList = userService.getUsersByRole("schoolAdmin");
        model.addAttribute("userList", userList);
        model.addAttribute("activePage", "userList");
        return "admin/user/userList";
    }


    @GetMapping("/user/view/{id}")
    public String viewUser(@PathVariable int id, HttpSession session, Model model) {
        User user = userService.getUserById(id);
        
        if (user != null) {
            // Add the logged-in user's details to the model
            User loggedInUser = (User) session.getAttribute("user");
            model.addAttribute("loggedInUser", loggedInUser);  // This will hold the logged-in user's data

            model.addAttribute("user", user);
            return "admin/user/viewUser";
        }
        return "redirect:/admin/user/userList";
    }

    @GetMapping("/user/delete/{id}")
    public String deleteUser(@PathVariable int id, RedirectAttributes redirectAttributes, HttpSession session) {
        if (!isAuthenticated(session)) {
            return "redirect:/login";  // Redirect to login if not authenticated
        }

        System.out.println("Deleting user with ID: " + id);

        userService.deleteUserAccount(id);  // Use service to delete the user
        redirectAttributes.addFlashAttribute("message", "User deleted successfully");
        return "redirect:/admin/user/userList"; // Redirect to user list after deletion
    }
}
