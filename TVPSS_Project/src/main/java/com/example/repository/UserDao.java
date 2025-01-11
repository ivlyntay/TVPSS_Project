package com.example.repository;

import com.example.model.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class UserDao {

    @PersistenceContext
    private EntityManager entityManager;

    public boolean saveUser(User user) {
        try {
            System.out.println("Attempting to save user: " + user);
            entityManager.persist(user);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }


    
    public User findByEmail(String email) {
        List<User> users = entityManager.createQuery("SELECT u FROM User u WHERE u.email = :email", User.class)
                                        .setParameter("email", email)
                                        .getResultList();
        if (users.isEmpty()) {
            return null; // No user found
        }
        return users.get(0); // Return the first matching user
    }
    
    public User findByEmailAndPassword(String email, String password) {
        List<User> users = entityManager.createQuery("SELECT u FROM User u WHERE u.email = :email AND u.password = :password", User.class)
                                        .setParameter("email", email)
                                        .setParameter("password", password)
                                        .getResultList();
        return users.isEmpty() ? null : users.get(0);
    }

}
