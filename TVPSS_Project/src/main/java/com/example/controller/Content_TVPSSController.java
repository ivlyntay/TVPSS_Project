package com.example.controller;

import com.example.model.Content;
import com.example.model.School;
import com.example.service.ContentService;
import com.example.service.SchoolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/admin/content")
public class Content_TVPSSController {

    @Autowired
    private ContentService contentService;

    @Autowired
    private SchoolService schoolService;

    @GetMapping("/list")
    public String listContent(Model model) {
        List<Content> contentList = contentService.getAllContent();
        model.addAttribute("contentList", contentList);
        System.out.println(contentList);
        return "admin/content/contentList";
    }

    @GetMapping("/view/{id}")
    public String viewContent(@PathVariable int id, Model model) {
        Content content = contentService.getContentById(id);
        if (content != null) {
            model.addAttribute("content", content);
            return "school/content/viewContent";
        }
        return "redirect:/admin/content/list";
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("content", new Content());
        List<School> schools = schoolService.getAllSchools();
        model.addAttribute("schools", schools);
        return "admin/content/addContent";
    }

    @PostMapping("/add")
    public String addContent(
        @RequestParam("videoTitle") String videoTitle,
        @RequestParam("category") String category,
        @RequestParam("uploadDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date uploadDate,
        @RequestParam("videoUrl") String videoUrl,
        @RequestParam("schoolId") int schoolId,
        RedirectAttributes redirectAttributes) {
        
        try {
            Content content = new Content();
            content.setVideoTitle(videoTitle);
            content.setCategory(category);
            content.setUploadDate(uploadDate);
            content.setVideoUrl(videoUrl);
            
            School school = schoolService.getSchoolById(schoolId);
            if (school == null) {
                redirectAttributes.addFlashAttribute("error", "School not found");
                return "redirect:/admin/content/add";
            }
            
            content.setSchool(school);
            contentService.saveContent(content);
            
            redirectAttributes.addFlashAttribute("message", "Content added successfully");
            return "redirect:/admin/content/list";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error adding content: " + e.getMessage());
            return "redirect:/admin/content/add";
        }
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable int id, Model model) {
        Content content = contentService.getContentById(id);
        List<School> schools = schoolService.getAllSchools();
        
        if (content != null) {
            model.addAttribute("content", content);
            model.addAttribute("schools", schools);
            return "admin/content/editContent";
        }
        return "redirect:/admin/content/list";
    }

    @PostMapping("/update")
    public String updateContent(
        @RequestParam("id") int id,
        @RequestParam("videoTitle") String videoTitle,
        @RequestParam("category") String category,
        @RequestParam("uploadDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date uploadDate,
        @RequestParam("videoUrl") String videoUrl,
        @RequestParam("schoolId") int schoolId,
        RedirectAttributes redirectAttributes) {
        
        try {
            Content content = contentService.getContentById(id);
            if (content == null) {
                redirectAttributes.addFlashAttribute("error", "Content not found");
                return "redirect:/admin/content/list";
            }
            
            School school = schoolService.getSchoolById(schoolId);
            if (school == null) {
                redirectAttributes.addFlashAttribute("error", "School not found");
                return "redirect:/admin/content/edit/" + id;
            }
            
            content.setVideoTitle(videoTitle);
            content.setCategory(category);
            content.setUploadDate(uploadDate);
            content.setVideoUrl(videoUrl);
            content.setSchool(school);
            
            contentService.updateContent(content);
            redirectAttributes.addFlashAttribute("message", "Content updated successfully");
            return "redirect:/admin/content/list";
            
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error updating content: " + e.getMessage());
            return "redirect:/admin/content/edit/" + id;
        }
    }

    @GetMapping("/delete/{id}")
    public String deleteContent(@PathVariable int id, RedirectAttributes redirectAttributes) {
        contentService.deleteContent(id);
        redirectAttributes.addFlashAttribute("message", "Content deleted successfully");
        return "redirect:/admin/content/list";
    }
}
