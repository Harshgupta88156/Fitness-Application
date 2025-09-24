package com.fitness.AIService.Service;

import com.fitness.AIService.Repository.RecommendationsRepository;
import com.fitness.AIService.model.Recommendations;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;



import java.util.List;
@Service
@RequiredArgsConstructor
public class RecommendationsService {

    private final RecommendationsRepository recommendationsRepository;
    public List<Recommendations> getUserRecommendations(String UserId) {

        return recommendationsRepository.findByUserId(UserId);


    }

    public Recommendations getActivityRecommendations(String ActivityId) {
         return recommendationsRepository.findByActivityId(ActivityId)
                 .orElseThrow(()-> new RuntimeException("No such Activity exist - Activity ID :"+ ActivityId));
    }
}
