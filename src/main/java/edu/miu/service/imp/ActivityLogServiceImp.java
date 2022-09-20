package edu.miu.service.imp;

import edu.miu.dto.ActivityLogDto;
import edu.miu.entity.ActivityLog;
import edu.miu.repo.ActivityLogRepo;
import edu.miu.service.ActivityLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ActivityLogServiceImp implements ActivityLogService {

    @Autowired
    private ActivityLogRepo activityLogRepo;


    @Override
    public List<ActivityLog> findAll() {
        return null;
    }

    @Override
    public ActivityLog getActivityLogById() {
        return null;
    }

    @Override
    public void saveActivityLog(ActivityLog activityLog) {
         activityLogRepo.save(activityLog);
    }
}
