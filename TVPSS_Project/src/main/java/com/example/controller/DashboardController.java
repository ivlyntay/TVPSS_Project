package com.example.controller;

import com.example.model.Content;
import com.example.model.Crew;
import com.example.model.School;
import com.example.model.User;
import com.example.service.ContentService;
import com.example.service.CrewService;
import com.example.service.SchoolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/school")
public class DashboardController {
	@Autowired
    private CrewService crewService;
    @Autowired
    private ContentService contentService;

    @Autowired
    private SchoolService schoolService;

    @GetMapping("/dashboard")
    public String dashboard(Model model, HttpSession session, RedirectAttributes redirectAttributes) {
        // Get logged in user
        User loggedInUser = (User) session.getAttribute("user");
        if (loggedInUser == null) {
            redirectAttributes.addFlashAttribute("error", "Please login first");
            return "redirect:/login";
        }

        // Get school by user's school name
        String schoolName = loggedInUser.getSchoolName();
        School school = schoolService.findBySchoolName(schoolName);
        if (school == null) {
            redirectAttributes.addFlashAttribute("error", "School not found");
            return "redirect:/school/content/list";
        }

        // Get content for this specific school only
        List<Content> schoolContent = contentService.getContentBySchool(school.getId());
        
        // Calculate statistics
        int totalContent = schoolContent.size();

        // Content by category for this school
        Map<String, Long> contentByCategory = schoolContent.stream()
            .collect(Collectors.groupingBy(
                content -> content.getCategory().toString(),
                Collectors.counting()
            ));

        // Get recent uploads for this school (limit to 5)
        List<Content> recentUploads = schoolContent.stream()
            .sorted((c1, c2) -> c2.getUploadDate().compareTo(c1.getUploadDate()))
            .limit(5)
            .collect(Collectors.toList());
        // Get crew members for this user/school
        List<Crew> crewMembers = crewService.getCrewMembersByUserId(loggedInUser.getId());
        int totalCrewMembers = crewMembers.size();

        // Rest of your existing code...
        model.addAttribute("totalCrewMembers", totalCrewMembers);
        // Add attributes to model
        model.addAttribute("schoolName", schoolName);
        model.addAttribute("totalContent", totalContent);
        model.addAttribute("contentByCategory", contentByCategory);
        model.addAttribute("recentUploads", recentUploads);

        return "school/dashboard/dashboard";
    }
}