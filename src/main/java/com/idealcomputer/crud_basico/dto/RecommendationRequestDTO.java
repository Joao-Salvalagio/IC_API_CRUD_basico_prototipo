package com.idealcomputer.crud_basico.dto;

public class RecommendationRequestDTO {
    private String usage;
    private String budget;
    private String detail; // NOVO CAMPO

    // Getters e Setters
    public String getUsage() {
        return usage;
    }
    public void setUsage(String usage) {
        this.usage = usage;
    }
    public String getBudget() {
        return budget;
    }
    public void setBudget(String budget) {
        this.budget = budget;
    }
    public String getDetail() { // Getter para o novo campo
        return detail;
    }
    public void setDetail(String detail) { // Setter para o novo campo
        this.detail = detail;
    }
}