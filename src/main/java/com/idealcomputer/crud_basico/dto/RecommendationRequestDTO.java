package com.idealcomputer.crud_basico.dto;

import lombok.Data;

@Data
public class RecommendationRequestDTO {
    private String usage;
    private String budget;
    private String detail;
}