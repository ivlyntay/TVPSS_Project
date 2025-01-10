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
    public List<Crew> getAllCrewMembers() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("from Crew", Crew.class).list();
        }
    }

    // Get crew member by ID
    public Crew getCrewMemberById(int id) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(Crew.class, id);
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

    public void updateCrewMember(Crew crewMember) {
        Transaction transaction = null;
        try (Session session = sessionFactory.getCurrentSession()) {
            transaction = session.beginTransaction();

            // Ensure the crew member already exists before updating
            Crew existingCrew = session.get(Crew.class, crewMember.getId());
            if (existingCrew != null) {
                // Update all editable fields
                existingCrew.setFullName(crewMember.getFullName());
                existingCrew.setIcNumber(crewMember.getIcNumber());
                existingCrew.setEmail(crewMember.getEmail());
                existingCrew.setContactNumber(crewMember.getContactNumber());
                existingCrew.setGender(crewMember.getGender());
                existingCrew.setRole(crewMember.getRole());

                // Update the photo if a new photo is provided
                if (crewMember.getPhoto() != null && !crewMember.getPhoto().isEmpty()) {
                    existingCrew.setPhoto(crewMember.getPhoto());
                }

                // Save the updated crew member
                session.update(existingCrew);
            }

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            // Optionally log the exception for debugging
            throw e;
        }
    }
    // Delete a crew member by ID
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
}
