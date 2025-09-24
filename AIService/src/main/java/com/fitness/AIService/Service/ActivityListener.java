package com.fitness.AIService.Service;


import com.fitness.AIService.model.Activity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class ActivityListener {

    @KafkaListener(topics = "${kafka.topic.name}", groupId = "activity-process-group")
    public void listenActivity(Activity activity){
        log.info("Activity received from Activity from Kafka with userId "+ activity.getUserId());
    }

}
