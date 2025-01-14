package com.example.service;

import com.example.model.Crew;
import com.example.model.User;
import com.example.repository.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Service
@Transactional
public class UserService {

    @Autowired
    private UserDao userDao;

    // Register a new user
    public boolean registerUser(User user) {
        if (userDao.findByEmail(user.getEmail()) != null) {
            return false; // Email already exists
        }
        try {
            userDao.saveUser(user);
            return true;
        } catch (Exception e) {
            System.out.println("Error saving user: " + e.getMessage());
            return false;
        }
    }

 // Retrieve users by role
    public List<User> getUsersByRole(String role) {
        try {
            return userDao.findUsersByRole(role);
        } catch (Exception e) {
            System.out.println("Error fetching users by role: " + e.getMessage());
            throw new RuntimeException("Error fetching users by role");
        }
    }

    // Login a user with email and password
    public User loginUser(String email, String password) {
        User user = userDao.findByEmail(email);
        if (user != null && user.getPassword().equals(password)) {
            return user;
        }
        return null;
    }

    // Find a user by ID
    public User findById(int id) {
        return userDao.findById(id);
    }

    // Update an existing user
    public void updateUser(User user) {
        userDao.updateUser(user);
    }

    // Retrieve all users (for user list)
    public List<User> getAllUser() {
        try {
            return userDao.findAll();
        } catch (Exception e) {
            System.out.println("Error fetching user list: " + e.getMessage());
            throw new RuntimeException("Error fetching user list");
        }
    }

    // Retrieve user by ID (used in viewUser)
    public User getUserById(int id) {
        try {
            return userDao.findById(id);
        } catch (Exception e) {
            System.out.println("Error fetching user by ID: " + e.getMessage());
            throw new RuntimeException("Error fetching user by ID");
        }
    }
 // Delete a user by ID
    public void deleteUserAccount(int id) {
        userDao.deleteUserAccount(id);
    }
 // Save the photo for a user (school admin)
    public String savePhoto(MultipartFile file) throws IOException {
        if (file.isEmpty()) {
            return "default.jpg"; // Return default image name if no file is uploaded
        }

        String uploadDir = new File("src/main/webapp/crew_photos").getAbsolutePath();
        File uploadDirectory = new File(uploadDir);

        if (!uploadDirectory.exists()) {
            uploadDirectory.mkdirs();
        }

        String fileName = file.getOriginalFilename();
        File destination = new File(uploadDirectory, fileName);
        file.transferTo(destination);

        return fileName;
    }

}
