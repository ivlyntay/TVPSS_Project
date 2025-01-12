package com.example.model;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "program_status")
public class Program {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "school_name", nullable = false)
    private String schoolName;

    @Column(name = "status_version", nullable = false, length = 50)
    private String statusVersion;

    @Column(name = "equipment_level", nullable = false, length = 50)
    private String equipmentLevel;

    @Column(name = "last_edited", nullable = false)
    private Date lastEdited;

    @Column(name = "logo_status", nullable = false)
    private boolean logoStatus;

    @Column(name = "studio_status", nullable = false)
    private boolean studioStatus;

    @Column(name = "recording_status", nullable = false)
    private boolean recordingStatus;

    @Column(name = "youtube_status", nullable = false)
    private boolean youtubeStatus;

    @Column(name = "youtube_link")
    private String youtubeLink;

    @Column(name = "inside_outside_status", nullable = false)
    private boolean insideOutsideStatus;

    @Column(name = "collaborate_status", nullable = false)
    private boolean collaborateStatus;

    @Column(name = "green_screen_status", nullable = false)
    private boolean greenScreenStatus;

    // Default constructor
    public Program() {}

    // Constructor with parameters
    public Program(String schoolName, String statusVersion, String equipmentLevel, Date lastEdited,
                   boolean logoStatus, boolean studioStatus, boolean recordingStatus, boolean youtubeStatus,
                   String youtubeLink, boolean insideOutsideStatus, boolean collaborateStatus, boolean greenScreenStatus) {
        this.schoolName = schoolName;
        this.statusVersion = statusVersion;
        this.equipmentLevel = equipmentLevel;
        this.lastEdited = lastEdited;
        this.logoStatus = logoStatus;
        this.studioStatus = studioStatus;
        this.recordingStatus = recordingStatus;
        this.youtubeStatus = youtubeStatus;
        this.youtubeLink = youtubeLink;
        this.insideOutsideStatus = insideOutsideStatus;
        this.collaborateStatus = collaborateStatus;
        this.greenScreenStatus = greenScreenStatus;
    }

    // Getters and setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

    public String getStatusVersion() {
        return statusVersion;
    }

    public void setStatusVersion(String statusVersion) {
        this.statusVersion = statusVersion;
    }

    public String getEquipmentLevel() {
        return equipmentLevel;
    }

    public void setEquipmentLevel(String equipmentLevel) {
        this.equipmentLevel = equipmentLevel;
    }

    public Date getLastEdited() {
        return lastEdited;
    }

    public void setLastEdited(Date lastEdited) {
        this.lastEdited = lastEdited;
    }

    public boolean isLogoStatus() {
        return logoStatus;
    }

    public void setLogoStatus(boolean logoStatus) {
        this.logoStatus = logoStatus;
    }

    public boolean isStudioStatus() {
        return studioStatus;
    }

    public void setStudioStatus(boolean studioStatus) {
        this.studioStatus = studioStatus;
    }

    public boolean isRecordingStatus() {
        return recordingStatus;
    }

    public void setRecordingStatus(boolean recordingStatus) {
        this.recordingStatus = recordingStatus;
    }

    public boolean isYoutubeStatus() {
        return youtubeStatus;
    }

    public void setYoutubeStatus(boolean youtubeStatus) {
        this.youtubeStatus = youtubeStatus;
    }

    public String getYoutubeLink() {
        return youtubeLink;
    }

    public void setYoutubeLink(String youtubeLink) {
        this.youtubeLink = youtubeLink;
    }

    public boolean isInsideOutsideStatus() {
        return insideOutsideStatus;
    }

    public void setInsideOutsideStatus(boolean insideOutsideStatus) {
        this.insideOutsideStatus = insideOutsideStatus;
    }

    public boolean isCollaborateStatus() {
        return collaborateStatus;
    }

    public void setCollaborateStatus(boolean collaborateStatus) {
        this.collaborateStatus = collaborateStatus;
    }

    public boolean isGreenScreenStatus() {
        return greenScreenStatus;
    }

    public void setGreenScreenStatus(boolean greenScreenStatus) {
        this.greenScreenStatus = greenScreenStatus;
    }

    @Override
    public String toString() {
        return "Program{" +
                "id=" + id +
                ", schoolName='" + schoolName + '\'' +
                ", statusVersion='" + statusVersion + '\'' +
                ", equipmentLevel='" + equipmentLevel + '\'' +
                ", lastEdited=" + lastEdited +
                ", logoStatus=" + logoStatus +
                ", studioStatus=" + studioStatus +
                ", recordingStatus=" + recordingStatus +
                ", youtubeStatus=" + youtubeStatus +
                ", youtubeLink='" + youtubeLink + '\'' +
                ", insideOutsideStatus=" + insideOutsideStatus +
                ", collaborateStatus=" + collaborateStatus +
                ", greenScreenStatus=" + greenScreenStatus +
                '}';
    }
}
