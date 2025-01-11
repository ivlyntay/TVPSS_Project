package com.example.controller;

import com.example.model.User;
import com.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Controller
@RequestMapping("/admin/user")
public class User_TVPSSController {

    @Autowired
    private UserService userService;

    @GetMapping("/userList")
    public String listUser(Model model) {
        List<User> userList = userService.getAllUser();  // Get user from the service layer
        model.addAttribute("userList", userList);
        model.addAttribute("activePage", "userList");
        return "admin/user/userList"; // Return view name for user list page
    }

    @GetMapping("/view/{id}")
    public String viewUser(@PathVariable int id, Model model) {
        User user = userService.getUserById(id);  // Fetch user by ID from the service layer
        if (user != null) {
            model.addAttribute("user", user);
            return "admin/user/viewUser"; // View page for a specific user
        }
        return "redirect:/admin/user/userList"; // Redirect to user list if not found
    }
}
