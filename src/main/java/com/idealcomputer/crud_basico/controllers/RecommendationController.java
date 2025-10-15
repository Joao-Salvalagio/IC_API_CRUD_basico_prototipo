package com.idealcomputer.crud_basico.controllers;

import com.idealcomputer.crud_basico.dto.RecommendationRequestDTO;
import com.idealcomputer.crud_basico.dto.RecommendationResponseDTO;
import com.idealcomputer.crud_basico.services.RecommendationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/recommendations")
@CrossOrigin(origins = "http://localhost:5173")
public class RecommendationController {

    @Autowired
    private RecommendationService recommendationService;

    @PostMapping("/generate")
    public ResponseEntity<RecommendationResponseDTO> generate(@RequestBody RecommendationRequestDTO request) {
        RecommendationResponseDTO response = recommendationService.generateBuild(request);
        return ResponseEntity.ok(response);
    }
}