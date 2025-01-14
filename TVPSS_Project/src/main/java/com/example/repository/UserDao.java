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

    // Save or update a user
    public boolean saveUser(User user) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.saveOrUpdate(user); // save or update the user
            transaction.commit();
            return true;
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
            return false;
        }
    }
    
 // Update a crew member
    public void updateUser(User user) {
        Transaction transaction = null;
        try (Session session = sessionFactory.getCurrentSession()) {
            transaction = session.beginTransaction();

            User existingUser = session.get(User.class, user.getId());
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

                session.update(existingUser);
            }

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            throw e;
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
    public List<User> findUsersByRole(String role) {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("from User u where u.role = :role", User.class)
                          .setParameter("role", role)
                          .list();
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

    // Find user by ID
    public User findById(int id) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(User.class, id);
        }
    }

    // Retrieve all users
    public List<User> findAll() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("from User", User.class).list();
        }
    }

    // Delete a user by ID
    public boolean deleteUserAccount(int id) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            User user = session.get(User.class, id);
            if (user != null) {
                session.delete(user);
                transaction.commit();
                return true;
            }
            return false;
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
            return false;
        }
    }

}
