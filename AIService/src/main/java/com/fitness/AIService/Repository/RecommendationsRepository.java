package com.fitness.AIService.Repository;

import com.fitness.AIService.model.Recommendations;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface RecommendationsRepository extends MongoRepository<Recommendations, String> {
    List<Recommendations> findByUserId(String userId);

    Optional<Recommendations> findByActivityId(String activityId);

}
