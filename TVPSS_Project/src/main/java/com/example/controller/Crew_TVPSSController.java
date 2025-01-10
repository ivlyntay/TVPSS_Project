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
    public String listCrew(Model model) {
        List<Crew> crewList = crewService.getAllCrewMembers();  // Get crew members from the service layer
        model.addAttribute("crewList", crewList);
        model.addAttribute("activePage", "crewList");
        return "admin/crew/crewList"; // Return view name for crew list page
    }

    @GetMapping("/view/{id}")
    public String viewCrew(@PathVariable int id, Model model) {
        Crew crew = crewService.getCrewMemberById(id);  // Fetch crew member by ID from the service layer
        if (crew != null) {
            model.addAttribute("crew", crew);
            return "admin/crew/viewCrew"; // View page for a specific crew member
        }
        return "redirect:/admin/crew/crewList"; // Redirect to crew list if not found
    }
}
