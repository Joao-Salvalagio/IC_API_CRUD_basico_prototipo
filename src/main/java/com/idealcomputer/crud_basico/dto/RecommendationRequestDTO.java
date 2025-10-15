package com.idealcomputer.crud_basico.dto;

public class RecommendationRequestDTO { //Data Transfer Object
    private String usage;
    private String budget;

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
}
