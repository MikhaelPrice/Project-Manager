package com.RestManager.Project.Manager.controller;

import com.RestManager.Project.Manager.domain.Project;
import com.RestManager.Project.Manager.domain.User;
import com.RestManager.Project.Manager.domain.Views;
import com.RestManager.Project.Manager.repo.ProjectRepo;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;

@Controller
@RequestMapping("/")
public class MainController {

    private final ProjectRepo projectRepo;

    @Value("${spring.profiles.active}")
    private String profile;
    private final ObjectWriter writer;

    @Autowired
    public MainController(ProjectRepo projectRepo, ObjectMapper mapper) {
        this.projectRepo = projectRepo;
        this.writer = mapper
                .setConfig(mapper.getSerializationConfig())
                .writerWithView(Views.FullProject.class);
    }

    @GetMapping
    public String main(Model model,
                       @AuthenticationPrincipal User user)
            throws JsonProcessingException {
        HashMap<Object, Object> data = new HashMap<>();
        if (user != null) {
            data.put("profile", user);
            String projects = writer.writeValueAsString(projectRepo.findAll());
            model.addAttribute("projects", projects);
        }
        model.addAttribute("frontendData", data);
        model.addAttribute("isDevMode", "dev".equals(profile));
        return "index";
    }

}