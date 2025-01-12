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

    @Column(name = "status_version", nullable = false)
    private String statusVersion;

    @Column(name = "equipment_level", nullable = false)
    private String equipmentLevel;

    @Column(name = "last_edited", nullable = false)
    private Date lastEdited;

    // Default constructor
    public Program() {}

    // Constructor with parameters
    public Program(String schoolName, String statusVersion, String equipmentLevel, Date lastEdited) {
        this.schoolName = schoolName;
        this.statusVersion = statusVersion;
        this.equipmentLevel = equipmentLevel;
        this.lastEdited = lastEdited;
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

    @Override
    public String toString() {
        return "Program{" +
                "id=" + id +
                ", schoolName='" + schoolName + '\'' +
                ", statusVersion='" + statusVersion + '\'' +
                ", equipmentLevel='" + equipmentLevel + '\'' +
                ", lastEdited=" + lastEdited +
                '}';
    }
}
