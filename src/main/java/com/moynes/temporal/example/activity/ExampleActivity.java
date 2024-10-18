package com.moynes.temporal.example.activity;

import io.temporal.activity.ActivityInterface;
import io.temporal.activity.ActivityMethod;

@ActivityInterface
public interface ExampleActivity {
    @ActivityMethod
    public String getUUID();

    @ActivityMethod
    public String firstActivity(String uuid);

    @ActivityMethod
    public String secondActivity(String uuid, String activityResult);

    @ActivityMethod
    public void finalActivity(String uuid);

}
