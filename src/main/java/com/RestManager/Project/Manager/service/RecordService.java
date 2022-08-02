package com.RestManager.Project.Manager.service;

import com.RestManager.Project.Manager.domain.Record;
import com.RestManager.Project.Manager.domain.User;
import com.RestManager.Project.Manager.repo.RecordRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RecordService {

    private final RecordRepo recordRepo;

    @Autowired
    public RecordService(RecordRepo recordRepo) {
        this.recordRepo = recordRepo;
    }

    public Record create(Record record, User user){
        record.setAuthor(user);
        recordRepo.save(record);
        return record;
    }
}

