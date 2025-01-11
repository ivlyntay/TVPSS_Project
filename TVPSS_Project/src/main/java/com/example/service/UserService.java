package com.example.service;

import com.example.model.User;
import com.example.repository.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
        try {
            User existingUser = userDao.findById(user.getId());
            if (existingUser != null) {
                // Update user fields (customize fields as needed)
                existingUser.setFullName(user.getFullName());
                existingUser.setIcNumber(user.getIcNumber());
                existingUser.setSchoolName(user.getSchoolName());
                existingUser.setEmail(user.getEmail());
                existingUser.setContactNumber(user.getContactNumber());
                existingUser.setDistrict(user.getDistrict());
                existingUser.setYoutubeLink(user.getYoutubeLink());
                existingUser.setYoutubeChannelName(user.getYoutubeChannelName());

                // Save the updated user object
                userDao.saveUser(existingUser);
            }
        } catch (Exception e) {
            System.out.println("Error updating user: " + e.getMessage());
            throw new RuntimeException("Error updating user");
        }
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
}
