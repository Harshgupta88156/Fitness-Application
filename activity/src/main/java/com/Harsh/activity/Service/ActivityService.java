package com.Harsh.activity.Service;


import com.Harsh.activity.Dto.ActivityRequest;
import com.Harsh.activity.Dto.ActivityResponse;
import com.Harsh.activity.Repository.ActivityRepository;
import com.Harsh.activity.model.Activity.Activity;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service

@RequiredArgsConstructor
public class ActivityService {

    @Value("${kafka.topic.name}")
    private String topicName;

    private final ActivityRepository activityRepository;
    private final UserValidationService userValidationService;
    private final KafkaTemplate<String, Activity> kafkaTemplate;

    public ActivityResponse trackActivity(ActivityRequest activityRequest){

        boolean isValidUser = userValidationService.validateUser(activityRequest.getUserId());

        if(!isValidUser){
            throw new RuntimeException("Invalid User: " + activityRequest.getUserId());
        }
        Activity activity = Activity.builder()
                .userId(activityRequest.getUserId())
                .type(activityRequest.getType())
                .caloriesBurned(activityRequest.getCaloriesBurned())
                .metrics(activityRequest.getAdditionalMetrics())
                .startTime(activityRequest.getStartTime())
                .duration(activityRequest.getDuration())
                .build();

        Activity savedActivity = activityRepository.save(activity);
        try{
            kafkaTemplate.send(topicName, savedActivity.getId(), savedActivity);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mapToResponse(savedActivity);

    }

    private ActivityResponse mapToResponse(Activity savedActivity) {
        ActivityResponse activityResponse = new ActivityResponse();
        activityResponse.setId(savedActivity.getId());
        activityResponse.setType(savedActivity.getType());
        activityResponse.setDuration(savedActivity.getDuration());
        activityResponse.setAdditionalMetrics(savedActivity.getMetrics());
        activityResponse.setUserId(savedActivity.getUserId());
        activityResponse.setCaloriesBurned(savedActivity.getCaloriesBurned());
        activityResponse.setStartTime(savedActivity.getStartTime());
        activityResponse.setCreatedAt(savedActivity.getCreatedAt());
        activityResponse.setUpdatedAt(savedActivity.getUpdatedAt());

        return activityResponse;



    }
}
