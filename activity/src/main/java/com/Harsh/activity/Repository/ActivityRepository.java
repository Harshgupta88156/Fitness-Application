package com.Harsh.activity.Repository;

import com.Harsh.activity.model.Activity.Activity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ActivityRepository extends MongoRepository<Activity, String> {
}
