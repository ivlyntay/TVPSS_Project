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

    // Save a user
    public boolean saveUser(User user) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.save(user);
            transaction.commit();
            return true;
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
            return false;
        }
    }

    // Find user by email
    public User findByEmail(String email) {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("from User u where u.email = :email", User.class)
                          .setParameter("email", email)
                          .uniqueResult();
        }
    }

    // Find user by email and password
    public User findByEmailAndPassword(String email, String password) {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("from User u where u.email = :email and u.password = :password", User.class)
                          .setParameter("email", email)
                          .setParameter("password", password)
                          .uniqueResult();
        }
    }
}
