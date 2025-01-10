package com.example.service;

import com.example.model.Crew;
import com.example.repository.CrewDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Service
public class CrewService {

    @Autowired
    private CrewDao crewMemberDao;

    // Get all crew members
    public List<Crew> getAllCrewMembers() {
        return crewMemberDao.getAllCrewMembers();
    }

    // Get a crew member by ID
    public Crew getCrewMemberById(int id) {
        return crewMemberDao.getCrewMemberById(id);
    }

    // Save a new crew member
    public void saveCrewMember(Crew crewMember) {
        crewMemberDao.saveCrewMember(crewMember);
    }

    // Update an existing crew member
    public void updateCrewMember(Crew crewMember, MultipartFile crewPhoto) throws IOException {
        // Handle photo upload if a new photo is provided
        if (crewPhoto != null && !crewPhoto.isEmpty()) {
            // Save the new photo and get the filename
            String photoName = savePhoto(crewPhoto);
            crewMember.setPhoto(photoName);  // Set the new photo in the crew member object
        }

        // Update the crew member using the DAO
        crewMemberDao.updateCrewMember(crewMember);
    }
    
    // Delete a crew member by ID
    public void deleteCrewMember(int id) {
        crewMemberDao.deleteCrewMember(id);
    }

    // Save the photo for a crew member
    public String savePhoto(MultipartFile file) throws IOException {
        if (file.isEmpty()) {
            return "default.jpg"; // Return default image name if no file is uploaded
        }

        // Define the upload directory
        String uploadDir = new File("src/main/webapp/crew_photos").getAbsolutePath();
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
