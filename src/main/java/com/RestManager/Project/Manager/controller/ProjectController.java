package com.RestManager.Project.Manager.controller;

import com.RestManager.Project.Manager.domain.Project;
import com.RestManager.Project.Manager.domain.User;
import com.RestManager.Project.Manager.domain.Views;
import com.RestManager.Project.Manager.dto.EventType;
import com.RestManager.Project.Manager.dto.ObjectType;
import com.RestManager.Project.Manager.repo.ProjectRepo;
import com.RestManager.Project.Manager.util.WsSender;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.function.BiConsumer;

@RestController
@RequestMapping("project")
public class ProjectController {

    private final ProjectRepo projectRepo;
    private final BiConsumer<EventType, Project> wsSender;

    @Autowired
    public ProjectController(ProjectRepo projectRepo, WsSender wsSender) {
        this.projectRepo = projectRepo;
        this.wsSender = wsSender.getSender(ObjectType.PROJECT, Views.IdName.class);
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
    public Project create(@RequestBody Project project,
                          @AuthenticationPrincipal User user) throws IOException {

        project.setCreationDate(LocalDateTime.now());
        project.setAuthor(user);
        Project updatedProject = projectRepo.save(project);
        wsSender.accept(EventType.CREATE, updatedProject);
        return updatedProject;
    }

    @PutMapping("{id}")
    public Project update(@PathVariable("id") Project projectFromDb, @RequestBody Project project) {
        BeanUtils.copyProperties(project, projectFromDb, "id");
        Project updatedProject = projectRepo.save(projectFromDb);
        wsSender.accept(EventType.UPDATE, updatedProject);
        return updatedProject;
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable("id") Project project) {
        projectRepo.delete(project);
        wsSender.accept(EventType.REMOVE, project);
    }

}