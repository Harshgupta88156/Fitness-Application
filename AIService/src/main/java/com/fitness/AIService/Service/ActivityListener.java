package com.fitness.AIService.Service;


import com.fitness.AIService.model.Activity;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
@Data
public class ActivityListener {

    private final ActivityAIService activityAIService;

    @KafkaListener(topics = "${kafka.topic.name}", groupId = "activity-process-group")
    public void listenActivity(Activity activity){
        try{
            log.info("Activity received from Activity from Kafka with userId "+ activity.getUserId());

            activityAIService.generateRecommendations(activity);
        } catch (Exception e) {
            log.error("Failed to process activity: {}", activity, e);
            throw new RuntimeException("Unable to process activity!!!", e);
        }
    }

}


