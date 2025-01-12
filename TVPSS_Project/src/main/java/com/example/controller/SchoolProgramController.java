// SchoolProgramController.java
package com.example.controller;

import com.example.model.Program;
import com.example.service.SchoolProgramService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/school/program")
public class SchoolProgramController {
    
    @Autowired
    private SchoolProgramService schoolProgramService;
    
    @GetMapping("/ProgramStatus")
    public String showProgramStatus(@RequestParam(required = false) String schoolName, Model model) {
        Program program = schoolProgramService.getProgramBySchool(schoolName);
        if (program == null) {
            program = new Program(); // Initialize with defaults if needed
        }
        model.addAttribute("program", program);
        return "/school/program/ProgramStatus";
    }
    
    @PostMapping("/program-status/save")
    @ResponseBody
    public String saveProgramStatus(@ModelAttribute Program program) {
        try {
            // Calculate and set version
            String version = schoolProgramService.calculateVersion(program);
            program.setStatusVersion(version);
            
            schoolProgramService.saveOrUpdateProgramStatus(program);
            return "success";
        } catch (Exception e) {
            return "error: " + e.getMessage();
        }
    }
    
    @GetMapping("/equipment_management")
    public String showEquipmentManagement(@RequestParam(required = false) String schoolName, Model model) {
        Program program = schoolProgramService.getProgramBySchool(schoolName);
        model.addAttribute("program", program);
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