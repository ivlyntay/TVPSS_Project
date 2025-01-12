package com.example.service;

import com.example.model.Content;
import com.example.repository.ContentDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContentService {

    @Autowired
    private ContentDao contentDao;

    // Get all content
    public List<Content> getAllContent() {
        return contentDao.getAllContent();
    }

    // Get content by ID
    public Content getContentById(int id) {
        return contentDao.getContentById(id);
    }

    // Save new content
    public void saveContent(Content content) {
        contentDao.saveContent(content);
    }

    // Update existing content
    public void updateContent(Content content) {
        contentDao.updateContent(content);
    }

    // Delete content by ID
    public void deleteContent(int id) {
        contentDao.deleteContent(id);
    }
}
