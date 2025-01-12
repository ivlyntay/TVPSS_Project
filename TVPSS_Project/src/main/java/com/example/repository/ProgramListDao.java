package com.example.repository;

import com.example.model.Program;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProgramListDao {

    @Autowired
    private SessionFactory sessionFactory;

    // Get program by ID
    public Program getProgramById(int id) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(Program.class, id);
        }
    }

    // Get all programs
    public List<Program> getAllPrograms() {
        try (Session session = sessionFactory.openSession()) {
            String hql = "FROM Program";
            return session.createQuery(hql, Program.class).list();
        }
    }

    // Get programs by filters
    public List<Program> getFilteredPrograms(String statusVersion, String equipmentLevel, String sortOrder) {
        try (Session session = sessionFactory.openSession()) {
            StringBuilder hql = new StringBuilder("FROM Program WHERE 1=1");
            if (statusVersion != null && !statusVersion.isEmpty()) {
                hql.append(" AND statusVersion = :statusVersion");
            }
            if (equipmentLevel != null && !equipmentLevel.isEmpty()) {
                hql.append(" AND equipmentLevel = :equipmentLevel");
            }
            if ("newest".equals(sortOrder)) {
                hql.append(" ORDER BY lastEdited DESC");
            } else if ("oldest".equals(sortOrder)) {
                hql.append(" ORDER BY lastEdited ASC");
            }

            Query<Program> query = session.createQuery(hql.toString(), Program.class);
            if (statusVersion != null && !statusVersion.isEmpty()) {
                query.setParameter("statusVersion", statusVersion);
            }
            if (equipmentLevel != null && !equipmentLevel.isEmpty()) {
                query.setParameter("equipmentLevel", equipmentLevel);
            }

            return query.list();
        }
    }

    // Search programs by school name
    public List<Program> searchProgramsBySchoolName(String schoolName) {
        try (Session session = sessionFactory.openSession()) {
            String hql = "FROM Program WHERE lower(schoolName) LIKE :schoolName";
            return session.createQuery(hql, Program.class)
                    .setParameter("schoolName", "%" + schoolName.toLowerCase() + "%")
                    .list();
        }
    }

    // Update a program
    public void updateProgram(Program program) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            try {
                // Get existing program
                Program existingProgram = session.get(Program.class, program.getId());
                if (existingProgram == null) {
                    throw new RuntimeException("Program not found");
                }

                // Update fields
                existingProgram.setStatusVersion(program.getStatusVersion());
                existingProgram.setEquipmentLevel(program.getEquipmentLevel());
                existingProgram.setLastEdited(program.getLastEdited());

                // Save changes
                session.update(existingProgram);
                transaction.commit();
            } catch (Exception e) {
                transaction.rollback();
                throw e;
            }
        }
    }
}
