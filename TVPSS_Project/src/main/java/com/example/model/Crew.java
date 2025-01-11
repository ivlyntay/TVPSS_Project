package com.example.model;

import javax.persistence.*;

@Entity
@Table(name = "crew_member")
public class Crew {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "full_name", nullable = false)
    private String fullName;

    @Column(name = "role", nullable = false)
    private String role;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "contact_number", nullable = false)
    private String contactNumber;

    @Column(name = "gender", nullable = false)
    private String gender;

    @Column(name = "ic_number", nullable = false)
    private String icNumber;

    @Column(name = "photo", nullable = true)
    private String photo;

    // Default constructor
    public Crew() {}

    // Constructor with parameters
    public Crew(String fullName, String role, String email, String contactNumber, String gender, String icNumber, String photo, User user) {
        this.fullName = fullName;
        this.role = role;
        this.email = email;
        this.contactNumber = contactNumber;
        this.gender = gender;
        this.icNumber = icNumber;
        this.photo = photo;
        this.user = user;
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

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
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

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getIcNumber() {
        return icNumber;
    }

    public void setIcNumber(String icNumber) {
        this.icNumber = icNumber;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

}
