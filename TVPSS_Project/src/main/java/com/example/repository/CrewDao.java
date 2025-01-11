package com.example.repository;

import com.example.model.Crew;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public class CrewDao {

    @Autowired
    private SessionFactory sessionFactory;

    // Get all crew members
    public List<Crew> getAllCrewMembersWithUser() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("SELECT c FROM Crew c JOIN FETCH c.user", Crew.class).list();
        }
    }

    // Get crew member by ID
    public Crew getCrewMemberById(int id) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(Crew.class, id);
        }
    }

    // Get crew members by user ID
    public List<Crew> getCrewMembersByUserId(Integer userId) {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("from Crew where user.id = :userId", Crew.class)
                          .setParameter("userId", userId)
                          .list();
        }
    }

    // Save a crew member
    public void saveCrewMember(Crew crewMember) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.save(crewMember);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            throw e;
        }
    }

    
    // Update a crew member
    public void updateCrewMember(Crew crewMember) {
        Transaction transaction = null;
        try (Session session = sessionFactory.getCurrentSession()) {
            transaction = session.beginTransaction();

            Crew existingCrew = session.get(Crew.class, crewMember.getId());
            if (existingCrew != null) {
                existingCrew.setFullName(crewMember.getFullName());
                existingCrew.setIcNumber(crewMember.getIcNumber());
                existingCrew.setEmail(crewMember.getEmail());
                existingCrew.setContactNumber(crewMember.getContactNumber());
                existingCrew.setGender(crewMember.getGender());
                existingCrew.setRole(crewMember.getRole());
                if (crewMember.getPhoto() != null && !crewMember.getPhoto().isEmpty()) {
                    existingCrew.setPhoto(crewMember.getPhoto());
                }

                session.update(existingCrew);
            }

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            throw e;
        }
    }

    // Delete a crew member
    public void deleteCrewMember(int id) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            Crew crewMember = session.get(Crew.class, id);
            if (crewMember != null) {
                session.delete(crewMember);
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            throw e;
        }
    }

    // Get crew member by ID and user ID
    public Crew getCrewMemberByIdAndUserId(int id, Integer userId) {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("from Crew where id = :id and user.id = :userId", Crew.class)
                          .setParameter("id", id)
                          .setParameter("userId", userId)
                          .uniqueResult();
        }
    }
}
