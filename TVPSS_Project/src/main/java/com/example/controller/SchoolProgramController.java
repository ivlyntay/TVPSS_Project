// SchoolProgramController.java
package com.example.controller;

import com.example.model.Program;
import com.example.service.SchoolProgramService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.sql.Date;

@Controller
@RequestMapping("/school/program")
public class SchoolProgramController {
    
    @Autowired
    private SchoolProgramService schoolProgramService;
    
    @GetMapping("/ProgramStatus")
    public String showProgramStatus(@RequestParam(required = false) String schoolName, Model model) {
        // Create a default school if schoolName is not provided
        if (schoolName == null || schoolName.isEmpty()) {
            schoolName = "SEKOLAH KEBANGSAAN MAWAI"; // Default school or get from session
        }
        
        // Get program status
        Program program = schoolProgramService.getProgramBySchool(schoolName);
        if (program == null) {
            program = new Program();
            program.setSchoolName(schoolName);
        }
        
        // Add both school and program to the model
        model.addAttribute("activePage", "ProgramStatus");
        model.addAttribute("school", program); // This will allow accessing school.schoolName
        model.addAttribute("program", program);
        return "school/program/ProgramStatus"; // Update path to match your template location
    }
    
    @PostMapping("/program-status/save")
    @ResponseBody
    public String saveProgramStatus(@ModelAttribute Program program) {
        try {
            // Add debug logging
            System.out.println("Received program data: " + program.toString());
            
            // Validate school name is not null
            if (program.getSchoolName() == null || program.getSchoolName().isEmpty()) {
                return "error: School name is required";
            }
            
            // Calculate and set version
            String version = schoolProgramService.calculateVersion(program);
            program.setStatusVersion(version);
            
            // Set last edited date
            program.setLastEdited(new Date(System.currentTimeMillis()));
            
            // Save the program
            schoolProgramService.saveOrUpdateProgramStatus(program);
            
            return "success";
        } catch (Exception e) {
            // Add detailed error logging
            e.printStackTrace();
            return "error: " + e.getMessage();
        }
    }
    
    @GetMapping("/equipment_management")
    public String showEquipmentManagement(@RequestParam(required = false) String schoolName, Model model) {
        Program program = schoolProgramService.getProgramBySchool(schoolName);
        model.addAttribute("program", program);
        model.addAttribute("activePage", "ProgramStatus");
        return "/school/program/equipment_management";
    }
    
    @PostMapping("/equipment/save")
    @ResponseBody
    public String saveEquipmentLevel(
            @RequestParam String schoolName,
            @RequestParam String equipmentLevel) {
        try {
            schoolProgramService.updateEquipmentLevel(schoolName, equipmentLevel);
            return "success";
        } catch (Exception e) {
            return "error: " + e.getMessage();
        }
    }
}