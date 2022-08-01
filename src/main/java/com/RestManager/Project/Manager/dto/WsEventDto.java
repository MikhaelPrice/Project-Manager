package com.RestManager.Project.Manager.dto;

import com.RestManager.Project.Manager.domain.Views;
import com.fasterxml.jackson.annotation.JsonRawValue;
import com.fasterxml.jackson.annotation.JsonView;
import jdk.jfr.EventType;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@JsonView(Views.Id.class)
public class WsEventDto {
    private ObjectType objectType;
    private EventType eventType;

    @JsonRawValue
    private String body;

    public WsEventDto(ObjectType objectType, com.RestManager.Project.Manager.dto.EventType eventType, String value) {

    }
}
