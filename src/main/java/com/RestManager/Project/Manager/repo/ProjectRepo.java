package com.RestManager.Project.Manager.repo;

import com.RestManager.Project.Manager.domain.Project;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectRepo extends JpaRepository<Project, Long> {

    Page<Project> findAll(Pageable pageable);

    Page<Project> findByTag(String tag, Pageable pageable);

}
