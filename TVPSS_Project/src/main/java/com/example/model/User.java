package com.example.model;
import javax.persistence.*;
import java.util.Collection;
import java.util.Collections;

@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "full_name")
    private String fullName;

    @Column(name = "ic_number")
    private String icNumber;

    @Column(name = "email")
    private String email;

    @Column(name = "contact_number")
    private String contactNumber;

    @Column(name = "role")
    private String role;

    @Column(name = "school_name")
    private String schoolName;

    @Column(name = "district")
    private String district;

    @Column(name = "password")
    private String password;

    @Column(name = "photo")
    private String photo;

    @Column(name = "ytbName")
    private String ytbName;

    @Column(name = "ytbLink")
    private String ytbLink;
	
    public User() {}
   public User(int id, String fullName, String icNumber, String schoolName, String district,
               String email, String contactNumber, String password, String role) {
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
	public String getDistrict() {
		return district;
	}
	public void setDistrict(String district) {
		this.district = district;
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
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
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
	// New Method to combine YouTube name and link
	public String getFormattedYoutubeLink() {
		if (ytbLink != null && ytbName != null) {
			return "<a href='" + ytbLink + "' target='_blank'>" + ytbName + "</a>";
		}
		return null; // Or return a default message if either is missing
	}
}

