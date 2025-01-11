package com.example.repository;

import com.example.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserDao {

	@Autowired
    private SessionFactory sessionFactory;

    public User getUserByEmailAndPassword(String email, String password) {
        Session session = sessionFactory.getCurrentSession();
        try {
            return session.createQuery("FROM User WHERE email = :email AND password = :password", User.class)
                          .setParameter("email", email)
                          .setParameter("password", password)
                          .uniqueResult();
        } catch (Exception e) {
            return null; // Handle exception as needed
        }
    }

    public void saveUser(User user) {
        Session session = sessionFactory.getCurrentSession();
        session.save(user);
    }
}
