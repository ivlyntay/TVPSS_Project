package com.example.service;

import com.example.model.Crew;
import com.example.repository.CrewDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Service
@Transactional
public class CrewService {

    @Autowired
    private CrewDao crewMemberDao;

    // Get all crew members
    public List<Crew> getAllCrewMembers() {
        return crewMemberDao.getAllCrewMembersWithUser();
    }

    // Get a crew member by ID
    public Crew getCrewMemberById(int id) {
        return crewMemberDao.getCrewMemberById(id);
    }

    // Get crew members by userId
    public List<Crew> getCrewMembersByUserId(Integer userId) {
        return crewMemberDao.getCrewMembersByUserId(userId); // Fetch crew members by userId
    }

    // Save a new crew member
    public void saveCrewMember(Crew crewMember) {
        crewMemberDao.saveCrewMember(crewMember);
    }

    // Update an existing crew member
    public void updateCrewMember(Crew crewMember, MultipartFile crewPhoto) throws IOException {
        if (crewPhoto != null && !crewPhoto.isEmpty()) {
            String photoName = savePhoto(crewPhoto);
            crewMember.setPhoto(photoName); // Set the new photo in the crew member object
        }

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

        String uploadDir = new File("src/main/webapp/crew_photos").getAbsolutePath();
        File uploadDirectory = new File(uploadDir);

        if (!uploadDirectory.exists()) {
            uploadDirectory.mkdirs();
        }

        String fileName = file.getOriginalFilename();
        File destination = new File(uploadDirectory, fileName);
        file.transferTo(destination);

        return fileName;
    }

    // Get a crew member by ID and user ID
    public Crew getCrewMemberByIdAndUserId(int id, Integer userId) {
        return crewMemberDao.getCrewMemberByIdAndUserId(id, userId); // Fetch crew member by ID and user ID
    }
}
