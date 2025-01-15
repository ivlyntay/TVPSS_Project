package com.example.repository;

import com.example.model.School;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class SchoolDao {

    @Autowired
    private SessionFactory sessionFactory;

    // Get all schools
    public List<School> getAllSchools() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("FROM School", School.class).list();
        }
    }

    // Get school by ID
    public School getSchoolById(int id) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(School.class, id);
        }
    }

    // Save school
    public void saveSchool(School school) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.save(school);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            throw e;
        }
    }

    // Update school
    public void updateSchool(School school) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.update(school);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            throw e;
        }
    }

    // Delete school by ID
    public void deleteSchool(int id) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            School school = session.get(School.class, id);
            if (school != null) {
                session.delete(school);
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            throw e;
        }
    }
    
    public School findByName(String name) {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("FROM School WHERE name = :name", School.class)
                    .setParameter("name", name)
                    .uniqueResult();
        }
    }
}
