package com.RestManager.Project.Manager.controller;

import com.RestManager.Project.Manager.domain.Record;
import com.RestManager.Project.Manager.domain.User;
import com.RestManager.Project.Manager.domain.Views;
import com.RestManager.Project.Manager.service.RecordService;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("record")
public class RecordController {
    private final RecordService recordService;

    @Autowired
    public RecordController(RecordService recordService) {
        this.recordService = recordService;
    }

    @PostMapping
    @JsonView(Views.FullProject.class)
    public Record create(
            @RequestBody Record record,
            @AuthenticationPrincipal User user
    ) {
        return recordService.create(record, user);
    }
}