package com.RestManager.Project.Manager.controller;

import com.RestManager.Project.Manager.domain.User;
import com.RestManager.Project.Manager.repo.ProjectRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;

@Controller
@RequestMapping("/")
public class MainController {

    @Autowired
    public MainController(ProjectRepo projectRepo) {
        this.projectRepo = projectRepo;
    }

    private final ProjectRepo projectRepo;

    @GetMapping
    public String main(Model model, @AuthenticationPrincipal User user){
        HashMap<Object, Object> data = new HashMap<>();
        data.put("profile", user);
        data.put("projects", projectRepo.findAll());
        model.addAttribute("frontendData", data);
        return "index";
    }

}
