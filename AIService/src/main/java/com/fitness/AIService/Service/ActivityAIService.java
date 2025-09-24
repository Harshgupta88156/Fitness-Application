package com.fitness.AIService.Service;


import com.fitness.AIService.model.Activity;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
@Data
public class ActivityAIService {

    public final GeminiService geminiService;

    public void generateRecommendations(Activity activity) {
        String prompt = createPromptForActivity(activity);
        log.info("Response from AI{}", geminiService.getRecommendations(prompt));

    }

    private String createPromptForActivity(Activity activity) {
        String type = activity.getType() != null ? activity.getType().name() : "UNKNOWN";
        int duration = activity.getDuration() != null ? activity.getDuration() : 0;
        int calories = activity.getCaloriesBurned() != null ? activity.getCaloriesBurned() : 0;
        String metrics = activity.getMetrics() != null ? activity.getMetrics().toString() : "{}";

        return String.format("""
                        Analyze this fitness activity and provide detailed recommendations in the following EXACT JSON format:
                        {
                          "analysis": {
                            "overall": "Overall analysis here",
                            "pace": "Pace analysis here",
                            "heartRate": "Heart rate analysis here",
                            "caloriesBurned": "Calories analysis here"
                          },
                          "improvements": [
                            {
                              "area": "Area name",
                              "recommendation": "Detailed recommendation"
                            }
                          ],
                          "suggestions": [
                            {
                              "workout": "Workout name",
                              "description": "Detailed workout description"
                            }
                          ],
                          "safety": [
                            "Safety point 1",
                            "Safety point 2"
                          ]
                        }
                        
                        Analyze this activity:
                        Activity Type: %s
                        Duration: %d minutes
                        Calories Burned: %d
                        Additional Metrics: %s
                        
                        Provide detailed analysis focusing on performance, improvements, next workout suggestions, and safety guidelines.
                        Ensure the response follows the EXACT JSON format shown above.
                        """,
                type, duration, calories, metrics);

    }
}
