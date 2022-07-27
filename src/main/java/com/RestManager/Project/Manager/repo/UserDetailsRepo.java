package com.RestManager.Project.Manager.repo;

import com.RestManager.Project.Manager.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDetailsRepo extends JpaRepository<User, String> {
}
