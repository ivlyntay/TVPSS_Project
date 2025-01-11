package com.example.controller;

import com.example.model.Crew;
import com.example.service.CrewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Controller
@RequestMapping("/admin/crew")
public class Crew_TVPSSController {

    @Autowired
    private CrewService crewService;

    @GetMapping("/crewList")
    public String listCrew(
            @RequestParam(value = "schoolName", required = false) String schoolName, 
            Model model) {
        List<Crew> crewList;
        if (schoolName != null && !schoolName.isEmpty()) {
            crewList = crewService.getCrewMembersBySchoolName(schoolName); // Filter by school name
        } else {
            crewList = crewService.getAllCrewMembers(); // Fetch all crew members
        }

        // Fetch unique school names for the dropdown
        List<String> uniqueSchools = crewService.getUniqueSchoolNames();

        model.addAttribute("crewList", crewList);
        model.addAttribute("uniqueSchools", uniqueSchools);
        model.addAttribute("selectedSchool", schoolName); // Preserve selected value
        model.addAttribute("activePage", "crewList");

        return "admin/crew/crewList"; // Return view name for crew list page
    }

    @GetMapping("/view/{id}")
    public String viewCrew(@PathVariable int id, Model model) {
        Crew crew = crewService.getCrewMemberById(id);
        if (crew != null) {
            model.addAttribute("crew", crew);
            model.addAttribute("schoolName", crew.getUser().getSchoolName());  // Assuming getUser() gives you the associated User object
            return "admin/crew/viewCrew";
        }
        return "redirect:/admin/crew/crewList";
    }
}
