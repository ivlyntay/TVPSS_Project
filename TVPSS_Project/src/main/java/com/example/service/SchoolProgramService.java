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
        schoolProgramDao.saveOrUpdateProgramStatus(program);
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