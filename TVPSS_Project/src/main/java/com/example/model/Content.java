package com.example.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "content")
public class Content {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "video_title", nullable = false)
    private String videoTitle;

    @Column(name = "category", nullable = false)
    private String category;

    @Column(name = "upload_date", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date uploadDate;

    @Column(name = "video_url", nullable = false)
    private String videoUrl;

    @ManyToOne
    @JoinColumn(name = "school_id", nullable = false)
    private School school;
    @Override
    public String toString() {
        return "Content{" +
                "id=" + id +
                ", videoName='" + videoTitle + '\'' +
                ", category='" + category + '\'' +
                ", uploadedDate=" + uploadDate +
                ", video_url=" + videoUrl +
                '}';
    }
    // Default constructor
    public Content() {}

    // Constructor with parameters
    public Content(String videoTitle, String category, Date uploadDate, String videoUrl, School school) {
        this.videoTitle = videoTitle;
        this.category = category;
        this.uploadDate = uploadDate;
        this.videoUrl = videoUrl;
        this.school = school;
    }

    // Getters and setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getVideoTitle() {
        return videoTitle;
    }

    public void setVideoTitle(String videoTitle) {
        this.videoTitle = videoTitle;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Date getUploadDate() {
        return uploadDate;
    }

    public void setUploadDate(Date uploadDate) {
        this.uploadDate = uploadDate;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    public School getSchool() {
        return school;
    }

    public void setSchool(School school) {
        this.school = school;
    }
}
