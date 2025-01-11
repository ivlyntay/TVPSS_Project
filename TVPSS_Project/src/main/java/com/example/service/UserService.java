package com.example.service;
import com.example.model.User;
import com.example.repository.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import org.springframework.transaction.annotation.Transactional;
@Service
public class UserService {

    @Autowired
    private UserDao userDao;

    @Transactional
    public boolean registerUser(User user) {
        return userDao.saveUser(user);
    }
//    @Transactional
//    public boolean registerUser(User user) {
//        if (userDao.findByEmail(user.getEmail()) != null) {
//            return false;  // Email already exists
//        }
//
//        userDAO.saveUser(user);
//        return true;
//    }

    public User loginUser(String email, String password) {
        User user = userDao.findByEmail(email);

        if (user != null && user.getPassword().equals(password)) {
            return user;  // Return user if credentials are valid
        }

        return null;  // Return null if invalid credentials
    }
}
