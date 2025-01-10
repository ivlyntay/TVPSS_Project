package com.example.controller;

import com.example.model.Crew;
import com.example.repository.CrewDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/school/crew")
public class CrewController {

    @Autowired
    private CrewDao crewMemberDao;

    @GetMapping("/crewList")
    public String listCrew(Model model) {
        List<Crew> crewList = crewMemberDao.getAllCrewMembers();  // Get crew members from the database
        model.addAttribute("crewList", crewList);
        model.addAttribute("activePage", "crewList");
        return "school/crew/crewList"; // Return view name for crew list page
    }

    @GetMapping("/view/{id}")
    public String viewCrew(@PathVariable int id, Model model) {
        Crew crew = crewMemberDao.getCrewMemberById(id);  // Fetch crew member by ID from the database
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

        String photoName = savePhoto(file);
        crew.setPhoto(photoName); // Set photo name

        crewMemberDao.saveCrewMember(crew);  // Save the new crew member to the database
        redirectAttributes.addFlashAttribute("message", "Crew added successfully");
        return "redirect:/school/crew/crewList"; // Redirect to crew list after successful addition
    }
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable int id, Model model) {
        Crew crew = crewMemberDao.getCrewMemberById(id);  // Fetch crew member by ID from the database
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
            // If the photo is not empty, handle the photo update
            if (crewPhoto != null && !crewPhoto.isEmpty()) {
                // Save the photo to the server (you can modify this to your preferred path)
                String photoName = savePhoto(crewPhoto);

                // Set the new photo name in the Crew object
                crew.setPhoto(photoName);
            }

            // Update the crew member in the database
            crewMemberDao.updateCrewMember(crew);
            redirectAttributes.addFlashAttribute("message", "Crew updated successfully");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error updating crew: " + e.getMessage());
        }
        return "redirect:/school/crew/crewList"; // Redirect to crew list after update
    }


    @GetMapping("/delete/{id}")
    public String deleteCrew(@PathVariable int id, RedirectAttributes redirectAttributes) {
        crewMemberDao.deleteCrewMember(id);  // Delete crew member from the database
        redirectAttributes.addFlashAttribute("message", "Crew deleted successfully");
        return "redirect:/school/crew/crewList"; // Redirect to crew list after deletion
    }

    private boolean isValidEmail(String email) {
        return email != null && email.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$");
    }

    private boolean isValidContact(String contact) {
        return contact != null && contact.matches("^\\d{10,15}$");
    }

    private String savePhoto(MultipartFile file) throws IOException {
        if (file.isEmpty()) {
            return "default.jpg"; // Return default image name if no file is uploaded
        }

        // Define the upload directory
        String uploadDir = new File("src/main/webapp/uploaded_photos").getAbsolutePath();
        File uploadDirectory = new File(uploadDir);

        // Create the directory if it doesn't exist
        if (!uploadDirectory.exists()) {
            uploadDirectory.mkdirs();
        }

        // Save the file
        String fileName = file.getOriginalFilename();
        File destination = new File(uploadDirectory, fileName);
        file.transferTo(destination);

        return fileName; // Return the saved file name
    }


}
