package com.RestManager.Project.Manager.repo;

import com.RestManager.Project.Manager.domain.Project;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProjectRepo extends JpaRepository<Project, Long> {
    @EntityGraph(attributePaths = { "records" })
    List<Project> findAll();
}