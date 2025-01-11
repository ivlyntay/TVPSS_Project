package com.example.model;

import javax.persistence.*;

@Entity // Marks this class as a Hibernate entity
@Table(name = "users") // Maps the entity to the "users" table
public class User {

    @Id // Marks this field as the primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-generates the ID value
    private int id;

    @Column(name = "full_name", nullable = false) // Maps to the "full_name" column, not null
    private String fullName;

    @Column(name = "ic_number", nullable = false, unique = true) // Maps to "ic_number", unique
    private String icNumber;

    @Column(name = "school_name", nullable = false) // Maps to "school_name", not null
    private String schoolName;

    @Column(name = "district") // Maps to "district"
    private String district;

    @Column(name = "email", nullable = false, unique = true) // Maps to "email", unique
    private String email;

    @Column(name = "contact_number", nullable = false) // Maps to "contact_number", not null
    private String contactNumber;

    @Column(name = "password", nullable = false) // Maps to "password", not null
    private String password;

    @Column(name = "youtube_link") // Maps to "youtube_link"
    private String ytbLink;

    @Column(name = "youtube_name") // Maps to "youtube_name"
    private String ytbName;

    @Column(name = "photo") // Maps to "photo"
    private String photo;

    @Column(name = "role", nullable = false) // Maps to "role", not null
    private String role;

    // Default constructor required by Hibernate
    public User() {}

    // Constructor with primary fields
    public User(int id, String fullName, String icNumber, String schoolName, String district, String email,
                String contactNumber, String password, String role) {
        this.id = id;
        this.fullName = fullName;
        this.icNumber = icNumber;
        this.schoolName = schoolName;
        this.district = district;
        this.email = email;
        this.contactNumber = contactNumber;
        this.password = password;
        this.role = role;
    }

    // Constructor with YouTube details
    public User(int id, String fullName, String icNumber, String schoolName, String district, String email,
                String contactNumber, String ytbLink, String ytbName, String photo) {
        this.id = id;
        this.fullName = fullName;
        this.icNumber = icNumber;
        this.schoolName = schoolName;
        this.district = district;
        this.email = email;
        this.contactNumber = contactNumber;
        this.ytbLink = ytbLink;
        this.ytbName = ytbName;
        this.photo = photo;
    }

    // Getters and setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getIcNumber() {
        return icNumber;
    }

    public void setIcNumber(String icNumber) {
        this.icNumber = icNumber;
    }

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getYoutubeLink() {
        return ytbLink;
    }

    public void setYoutubeLink(String ytbLink) {
        this.ytbLink = ytbLink;
    }

    public String getYoutubeChannelName() {
        return ytbName;
    }

    public void setYoutubeChannelName(String ytbName) {
        this.ytbName = ytbName;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    // Method to combine YouTube name and link
    public String getFormattedYoutubeLink() {
        if (ytbLink != null && ytbName != null) {
            return "<a href='" + ytbLink + "' target='_blank'>" + ytbName + "</a>";
        }
        return null;
    }
}
