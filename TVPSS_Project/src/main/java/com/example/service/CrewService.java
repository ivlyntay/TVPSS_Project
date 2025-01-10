package com.example.service;

import com.example.model.Crew;
import com.example.repository.CrewDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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
    public void updateCrewMember(Crew crewMember) {
        crewMemberDao.updateCrewMember(crewMember);
    }

    // Delete a crew member by ID
    public void deleteCrewMember(int id) {
        crewMemberDao.deleteCrewMember(id);
    }
}
