package com.example.service;

import com.example.model.School;
import com.example.repository.SchoolDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SchoolService {

    @Autowired
    private SchoolDao schoolDao;

    // Get all schools
    public List<School> getAllSchools() {
        return schoolDao.getAllSchools();
    }

    // Get school by ID
    public School getSchoolById(int id) {
        return schoolDao.getSchoolById(id);
    }

    // Save a new school
    public void saveSchool(School school) {
        schoolDao.saveSchool(school);
    }

    // Update an existing school
    public void updateSchool(School school) {
        schoolDao.updateSchool(school);
    }

    // Delete school by ID
    public void deleteSchool(int id) {
        schoolDao.deleteSchool(id);
    }
}
