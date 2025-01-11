package com.example.controller;

import com.example.model.Crew;
import com.example.model.User;
import com.example.service.CrewService;
import com.example.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/school/crew")
public class CrewController {

    @Autowired
    private CrewService crewService;
    @Autowired
    private UserService userService;

    // Ensure the user is authenticated before accessing the crew list
    private boolean isAuthenticated(HttpSession session) {
        return session.getAttribute("user") != null;  // Check if user is logged in (using session)
    }

    @GetMapping("/crewList")
    public String listCrew(HttpSession session, Model model) {
        if (!isAuthenticated(session)) {
            return "redirect:/login";  // Redirect to login if not authenticated
        }

        User user = (User) session.getAttribute("user");  // Get the logged-in user from the session
        if (user == null) {
            return "redirect:/login";  // Redirect to login if user is not found in session
        }

        List<Crew> crewList = crewService.getCrewMembersByUserId(user.getId());  // Get crew members for the logged-in user
        model.addAttribute("crewList", crewList);
        model.addAttribute("activePage", "crewList");
        return "school/crew/crewList"; // Return view name for crew list page
    }

    @GetMapping("/view/{id}")
    public String viewCrew(@PathVariable int id, HttpSession session, Model model) {
        if (!isAuthenticated(session)) {
            return "redirect:/login";  // Redirect to login if not authenticated
        }

        Crew crew = crewService.getCrewMemberById(id);  // Fetch crew member by ID from the service layer
        if (crew != null) {
            model.addAttribute("crew", crew);
            return "school/crew/viewCrew"; // View page for a specific crew member
        }
        return "redirect:/school/crew/crewList"; // Redirect to crew list if not found
    }

    @GetMapping("/add")
    public String showAddForm(HttpSession session, Model model) {
        if (!isAuthenticated(session)) {
            return "redirect:/login";  // Redirect to login if not authenticated
        }

        model.addAttribute("crew", new Crew()); // Empty CrewMember for the form
        return "school/crew/addCrew"; // Return the add crew form view
    }

    @PostMapping("/add")
    public String addCrew(@ModelAttribute Crew crew, 
                          @RequestParam("crewPhoto") MultipartFile file, 
                          RedirectAttributes redirectAttributes, 
                          HttpSession session) {
        // Retrieve userId from session
        Integer userId = (Integer) session.getAttribute("userId"); // Change Long to Integer

        if (userId == null) {
            redirectAttributes.addFlashAttribute("error", "User ID is missing.");
            return "redirect:/school/crew/add";
        }

        // Fetch User entity
        User user = userService.findById(userId);
        if (user == null) {
            redirectAttributes.addFlashAttribute("error", "User not found.");
            return "redirect:/school/crew/add";
        }

        crew.setUser(user); // Set the User object in Crew

        try {
            String photoName = crewService.savePhoto(file);
            crew.setPhoto(photoName);
            crewService.saveCrewMember(crew);
            redirectAttributes.addFlashAttribute("message", "Crew added successfully.");
            return "redirect:/school/crew/crewList";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error adding crew: " + e.getMessage());
            return "redirect:/school/crew/add";
        }
    }



    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable int id, HttpSession session, Model model) {
        if (!isAuthenticated(session)) {
            return "redirect:/login";  // Redirect to login if not authenticated
        }

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
            RedirectAttributes redirectAttributes, HttpSession session) {
        if (!isAuthenticated(session)) {
            return "redirect:/login";  // Redirect to login if not authenticated
        }

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
    public String deleteCrew(@PathVariable int id, RedirectAttributes redirectAttributes, HttpSession session) {
        if (!isAuthenticated(session)) {
            return "redirect:/login";  // Redirect to login if not authenticated
        }

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
