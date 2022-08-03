package com.RestManager.Project.Manager.repo;

import com.RestManager.Project.Manager.domain.Record;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecordRepo extends JpaRepository<Record, Long> {
}