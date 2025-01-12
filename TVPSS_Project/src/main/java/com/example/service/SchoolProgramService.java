// SchoolProgramService.java
package com.example.service;

import com.example.model.Program;
import com.example.repository.SchoolProgramDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SchoolProgramService {
    
    @Autowired
    private SchoolProgramDao schoolProgramDao;
    
    public Program getProgramBySchool(String schoolName) {
        return schoolProgramDao.getProgramBySchool(schoolName);
    }
    
    public void saveOrUpdateProgramStatus(Program program) {
        try {
            if (program.getSchoolName() == null || program.getSchoolName().isEmpty()) {
                throw new IllegalArgumentException("School name cannot be empty");
            }
            
            // Set default values if needed
            if (program.getEquipmentLevel() == null) {
                program.setEquipmentLevel("BASIC");
            }
            if (program.getStatusVersion() == null) {
                program.setStatusVersion("1");
            }
            
            schoolProgramDao.saveOrUpdateProgramStatus(program);
        } catch (Exception e) {
            // Log the error
            e.printStackTrace();
            throw new RuntimeException("Failed to save program status: " + e.getMessage());
        }
    }
    
    public void updateEquipmentLevel(String schoolName, String equipmentLevel) {
        schoolProgramDao.updateEquipmentLevel(schoolName, equipmentLevel);
    }
    
    // Calculate version based on program status
    public String calculateVersion(Program program) {
        if (program.isYoutubeStatus() && program.isCollaborateStatus() && program.isGreenScreenStatus()) {
            return "4";
        } else if (program.isStudioStatus() && program.isRecordingStatus()) {
            return "3";
        } else if (program.isLogoStatus()) {
            return "2";
        } else {
            return "1";
        }
    }
}