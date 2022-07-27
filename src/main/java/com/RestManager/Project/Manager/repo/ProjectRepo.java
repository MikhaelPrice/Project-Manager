package com.RestManager.Project.Manager.repo;

import com.RestManager.Project.Manager.domain.Project;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectRepo extends JpaRepository<Project, Long> {
}
