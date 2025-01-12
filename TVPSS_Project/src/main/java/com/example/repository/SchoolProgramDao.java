package com.example.repository;

import com.example.model.Program;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.Date;

@Repository
public class SchoolProgramDao {
    
    @Autowired
    private SessionFactory sessionFactory;
    
    // Get program by school name
    public Program getProgramBySchool(String schoolName) {
        try (Session session = sessionFactory.openSession()) {
            String hql = "FROM Program WHERE schoolName = :schoolName";
            return session.createQuery(hql, Program.class)
                .setParameter("schoolName", schoolName)
                .uniqueResult();
        }
    }
    
    // Save or update program status
    public void saveOrUpdateProgramStatus(Program program) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            try {
                program.setLastEdited(new Date(System.currentTimeMillis()));
                session.saveOrUpdate(program);
                transaction.commit();
            } catch (Exception e) {
                transaction.rollback();
                throw e;
            }
        }
    }
    
    // Update equipment level
    public void updateEquipmentLevel(String schoolName, String equipmentLevel) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            try {
                Program program = getProgramBySchool(schoolName);
                if (program != null) {
                    program.setEquipmentLevel(equipmentLevel);
                    program.setLastEdited(new Date(System.currentTimeMillis()));
                    session.update(program);
                }
                transaction.commit();
            } catch (Exception e) {
                transaction.rollback();
                throw e;
            }
        }
    }
}
