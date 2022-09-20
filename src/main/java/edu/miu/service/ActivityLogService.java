package edu.miu.service;

import edu.miu.entity.ActivityLog;

import java.util.List;

public interface ActivityLogService {
    public List<ActivityLog> findAll();
    public ActivityLog getActivityLogById();
    public void saveActivityLog(ActivityLog activityLog);


}
