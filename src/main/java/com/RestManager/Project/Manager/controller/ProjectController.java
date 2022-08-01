package com.RestManager.Project.Manager.controller;

import com.RestManager.Project.Manager.domain.Project;
import com.RestManager.Project.Manager.domain.Views;
import com.RestManager.Project.Manager.repo.ProjectRepo;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("project")
public class ProjectController {

    private final ProjectRepo projectRepo;

    @Autowired
    public ProjectController(ProjectRepo projectRepo) {
        this.projectRepo = projectRepo;
    }

    @GetMapping
    @JsonView(Views.IdName.class)
    public List<Project> list() {
        return projectRepo.findAll();
    }

    @GetMapping("{id}")
    @JsonView(Views.FullProject.class)
    public Project getOne(@PathVariable("id") Project project) {
        return project;
    }

    @PostMapping
    public Project create(@RequestBody Project project) {
        project.setCreationDate(LocalDateTime.now());
        return projectRepo.save(project);
    }

    @PutMapping("{id}")
    public Project update(@PathVariable("id") Project projectFromDb, @RequestBody Project project) {
        BeanUtils.copyProperties(project, projectFromDb, "id");
        return projectRepo.save(projectFromDb);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable("id") Project project) {
        projectRepo.delete(project);
    }

    @MessageMapping("/changeProject")
    @SendTo("/topic/activity")
    public Project change(Project project) {
        return projectRepo.save(project);
    }
}
