package com.example.service;

import com.example.model.Program;
import com.example.repository.ProgramListDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProgramListService {

    @Autowired
    private ProgramListDao programListDao;

    // Get a program by ID
    public Program getProgramById(int id) {
        return programListDao.getProgramById(id);
    }

    // Get all programs
    public List<Program> getAllPrograms() {
        return programListDao.getAllPrograms();
    }

    // Get programs with filters
    public List<Program> getFilteredPrograms(String statusVersion, String equipmentLevel, String sortOrder) {
        return programListDao.getFilteredPrograms(statusVersion, equipmentLevel, sortOrder);
    }

    // Search programs by school name
    public List<Program> searchProgramsBySchoolName(String schoolName) {
        return programListDao.searchProgramsBySchoolName(schoolName);
    }

    // Update a program
    public void updateProgram(Program program) {
        programListDao.updateProgram(program);
    }
}
