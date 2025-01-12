package com.example.controller;

import com.example.model.Program;
import com.example.service.ProgramListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/admin/program")
public class ProgramListController {

    @Autowired
    private ProgramListService programListService;

    @GetMapping("/programList")
    public String listPrograms(
            @RequestParam(value = "search", required = false) String search,
            @RequestParam(value = "statusVersion", required = false) String statusVersion,
            @RequestParam(value = "equipmentLevel", required = false) String equipmentLevel,
            @RequestParam(value = "lastEdited", required = false) String lastEdited,
            Model model) {

        List<Program> programs;
        
        if (search != null && !search.isEmpty()) {
            programs = programListService.searchProgramsBySchoolName(search);
        } else if (statusVersion != null || equipmentLevel != null || lastEdited != null) {
            programs = programListService.getFilteredPrograms(statusVersion, equipmentLevel, lastEdited);
        } else {
            programs = programListService.getAllPrograms();
        }

        model.addAttribute("programs", programs);
        model.addAttribute("activePage", "programList");
        return "admin/program/programList"; // Updated path to match actual location
    }

    @PostMapping("/update")
    @ResponseBody
    public String updateProgram(@RequestBody Program program) {
        try {
            programListService.updateProgram(program);
            return "success";
        } catch (Exception e) {
        	System.err.println("Error updating program: " + e.getMessage());
            return "error: " + e.getMessage();
        }
    }
}