package com.example.controller;

import com.example.model.Crew;
import com.example.service.CrewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/school/crew")
public class CrewController {

    @Autowired
    private CrewService crewService;

    @GetMapping("/crewList")
    public String listCrew(Model model) {
        List<Crew> crewList = crewService.getAllCrewMembers();  // Get crew members from the service layer
        model.addAttribute("crewList", crewList);
        model.addAttribute("activePage", "crewList");
        return "school/crew/crewList"; // Return view name for crew list page
    }

    @GetMapping("/view/{id}")
    public String viewCrew(@PathVariable int id, Model model) {
        Crew crew = crewService.getCrewMemberById(id);  // Fetch crew member by ID from the service layer
        if (crew != null) {
            model.addAttribute("crew", crew);
            return "school/crew/viewCrew"; // View page for a specific crew member
        }
        return "redirect:/school/crew/crewList"; // Redirect to crew list if not found
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("crew", new Crew()); // Empty CrewMember for the form
        return "school/crew/addCrew"; // Return the add crew form view
    }

    @PostMapping("/add")
    public String addCrew(@ModelAttribute Crew crew, @RequestParam("crewPhoto") MultipartFile file, RedirectAttributes redirectAttributes) throws IOException {
        if (!isValidEmail(crew.getEmail()) || !isValidContact(crew.getContactNumber())) {
            redirectAttributes.addFlashAttribute("error", "Invalid email or contact number");
            return "redirect:/school/crew/add"; // Redirect to add form if validation fails
        }

        String photoName = crewService.savePhoto(file); // Call savePhoto method from service
        crew.setPhoto(photoName); // Set photo name

        crewService.saveCrewMember(crew);  // Use service to save the new crew member
        redirectAttributes.addFlashAttribute("message", "Crew added successfully");
        return "redirect:/school/crew/crewList"; // Redirect to crew list after successful addition
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable int id, Model model) {
        Crew crew = crewService.getCrewMemberById(id);  // Fetch crew member by ID from the service layer
        if (crew != null) {
            model.addAttribute("crew", crew);
            return "school/crew/editCrew"; // Edit form view for a specific crew member
        }
        return "redirect:/school/crew/crewList"; // Redirect to crew list if crew not found
    }

    @PostMapping("/update")
    public String updateCrew(
            @ModelAttribute Crew crew,
            @RequestParam(value = "crewPhoto", required = false) MultipartFile crewPhoto, 
            RedirectAttributes redirectAttributes) {
        try {
            // Use service to update the crew member, including the photo if uploaded
            crewService.updateCrewMember(crew, crewPhoto);

            redirectAttributes.addFlashAttribute("message", "Crew updated successfully");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error updating crew: " + e.getMessage());
        }
        return "redirect:/school/crew/crewList"; // Redirect to crew list after update
    }

    @GetMapping("/delete/{id}")
    public String deleteCrew(@PathVariable int id, RedirectAttributes redirectAttributes) {
        crewService.deleteCrewMember(id);  // Use service to delete the crew member
        redirectAttributes.addFlashAttribute("message", "Crew deleted successfully");
        return "redirect:/school/crew/crewList"; // Redirect to crew list after deletion
    }

    private boolean isValidEmail(String email) {
        return email != null && email.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$");
    }

    private boolean isValidContact(String contact) {
        return contact != null && contact.matches("^\\d{10,15}$");
    }
}
