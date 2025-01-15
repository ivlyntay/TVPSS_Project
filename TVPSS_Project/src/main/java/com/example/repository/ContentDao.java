package com.example.repository;

import com.example.model.Content;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ContentDao {

    @Autowired
    private SessionFactory sessionFactory;

    // Get all content
    public List<Content> getAllContent() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("from Content", Content.class).list();
        }
    }

    // Get content by ID
    public Content getContentById(int id) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(Content.class, id);
        }
    }

    // Save new content
    public void saveContent(Content content) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.save(content);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            throw e;
        }
    }

    // Update existing content
    public void updateContent(Content content) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.update(content);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            throw e;
        }
    }

    // Delete content by ID
    public void deleteContent(int id) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            Content content = session.get(Content.class, id);
            if (content != null) {
                session.delete(content);
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            throw e;
        }
    }
    
    public List<Content> getContentBySchool(int schoolId) {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("FROM Content WHERE school.id = :schoolId", Content.class)
                    .setParameter("schoolId", schoolId)
                    .list();
        }
    }
    public List<Content> getContentBySchoolName(String schoolName) {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery(
                "FROM Content c WHERE c.school.name = :schoolName", Content.class)
                .setParameter("schoolName", schoolName)
                .list();
        }
    }
}
