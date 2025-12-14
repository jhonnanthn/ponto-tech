package br.com.ponto_tech.application.core.domain.dto;

import lombok.Data;

@Data
public class TimeClockRecordDTO {
    private String recordId;
    private String userId;
    private String timestamp;
    private String location;
    private String deviceId;
    private String recognizedFaceId;
    private String status;
    private String createdAt;
}

