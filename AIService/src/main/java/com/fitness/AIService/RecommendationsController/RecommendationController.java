package com.fitness.AIService.RecommendationsController;


import com.fitness.AIService.Service.RecommendationsService;
import com.fitness.AIService.model.Recommendations;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/recommendations")

public class RecommendationController {

    private final RecommendationsService recommendationsService;

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Recommendations>> getRecommendationByUser(@PathVariable String userId){
        return ResponseEntity.ok(recommendationsService.getUserRecommendations(userId));
    }


    @GetMapping("/activity/{activityId}")
    public ResponseEntity<Recommendations> getRecommendationByActivity(@PathVariable String activityId){
        return ResponseEntity.ok(recommendationsService.getActivityRecommendations(activityId));
    }


}
