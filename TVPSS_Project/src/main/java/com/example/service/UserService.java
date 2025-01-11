package com.example.service;

import com.example.repository.UserDao;
import com.example.model.User;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

	@Autowired
    private UserDao userDao;
	@Autowired
    private HttpSession session; // Inject HttpSession to access session attributes

    public User getUserByEmailAndPassword(String email, String password) {
        return userDao.getUserByEmailAndPassword(email, password);
    }

    public void registerUser(User user) {
        userDao.saveUser(user);
    }

    public User getLoggedInUser() {
        // Retrieve the logged-in user from the session
        return (User) session.getAttribute("loggedInUser"); // Make sure the user object is stored with this key
    }
}

