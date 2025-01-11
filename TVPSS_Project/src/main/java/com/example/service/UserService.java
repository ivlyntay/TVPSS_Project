package com.example.service;

import com.example.model.User;
import com.example.repository.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserService {

    @Autowired
    private UserDao userDao;
    
    
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

    public User loginUser(String email, String password) {
        User user = userDao.findByEmail(email);
        if (user != null && user.getPassword().equals(password)) {
            return user;
        }
        return null;
    }

    public User findById(int id) {
        return userDao.findById(id);
    }
    // Add the updateUser method here
    public void updateUser(User user) {
        try {
            User existingUser = userDao.findById(user.getId());
            if (existingUser != null) {
                // Update user fields (you can customize the fields that need to be updated)
                existingUser.setFullName(user.getFullName());
                existingUser.setIcNumber(user.getIcNumber());
                existingUser.setSchoolName(user.getSchoolName());
                existingUser.setEmail(user.getEmail());
                existingUser.setContactNumber(user.getContactNumber());
                existingUser.setDistrict(user.getDistrict());
                existingUser.setYoutubeLink(user.getYoutubeLink());
                existingUser.setYoutubeChannelName(user.getYoutubeChannelName());
                // You can add more fields here if necessary

                // Save the updated user object
                userDao.saveUser(existingUser);  // Save to the database
            }
        } catch (Exception e) {
            System.out.println("Error updating user: " + e.getMessage());
            throw new RuntimeException("Error updating user");
        }
    }
}