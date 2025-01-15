package com.example.controller;

import com.example.model.Content;
import com.example.model.Crew;
import com.example.model.School;
import com.example.service.ContentService;
import com.example.service.CrewService;
import com.example.service.SchoolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/admin/dashboard")
public class Dashboard_TVPSSController {

    @Autowired
    private ContentService contentService;

    @Autowired
    private CrewService crewService;

    @Autowired
    private SchoolService schoolService;

    @GetMapping("")
    public String showDashboard(Model model) {
        // Get all content
        List<Content> allContent = contentService.getAllContent();
        
        // Calculate content analytics
        Map<String, Long> contentByCategory = allContent.stream()
            .collect(Collectors.groupingBy(Content::getCategory, Collectors.counting()));
        
        // Get recent uploads (last 5)
        List<Content> recentUploads = allContent.stream()
            .sorted((c1, c2) -> c2.getUploadDate().compareTo(c1.getUploadDate()))
            .limit(5)
            .collect(Collectors.toList());
            
        // Get crew statistics
        List<Crew> allCrew = crewService.getAllCrewMembers();
        int totalCrewMembers = allCrew.size();
        
        // Get school statistics
        List<School> allSchools = schoolService.getAllSchools();
        int totalSchools = allSchools.size();
        
        // Calculate content per school
        Map<String, Integer> contentPerSchool = new HashMap<>();
        allSchools.forEach(school -> {
            long count = allContent.stream()
                .filter(content -> content.getSchool().getId() == school.getId())
                .count();
            contentPerSchool.put(school.getName(), (int) count);
        });

        // Add all data to model
        model.addAttribute("contentByCategory", contentByCategory);
        model.addAttribute("recentUploads", recentUploads);
        model.addAttribute("totalCrewMembers", totalCrewMembers);
        model.addAttribute("totalSchools", totalSchools);
        model.addAttribute("totalContent", allContent.size());
        model.addAttribute("contentPerSchool", contentPerSchool);
        
        return "admin/dashboard/dashboard";
    }
}

