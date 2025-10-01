package com.Harsh.activity.Controller;


import com.Harsh.activity.Dto.ActivityRequest;
import com.Harsh.activity.Dto.ActivityResponse;
import com.Harsh.activity.Service.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/activities")
public class ActivityController {


    @Autowired
    private ActivityService activityService;

    @PostMapping
    public ResponseEntity<ActivityResponse> trackActivity(@RequestBody ActivityRequest activityRequest, @RequestHeader("X-User-ID") String userId ){
        activityRequest.setUserId(userId);
        return ResponseEntity.ok(activityService.trackActivity(activityRequest));
    }
}
