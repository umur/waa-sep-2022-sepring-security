package edu.miu.controller;

import edu.miu.aspect.annotation.ExecutionTime;
import edu.miu.entity.ActivityLog;
import edu.miu.service.imp.ActivityLogServiceImp;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/activityLogs")
@RequiredArgsConstructor
public class ActivityLogController {
    public final ActivityLogServiceImp activityLogService;

    @ExecutionTime
    @GetMapping
    public List<ActivityLog> findAll() {
        return activityLogService.findAll();
    }
    @ExecutionTime
    @GetMapping("/{id}")
    public void saveActivityLogById(ActivityLog activityLog) {
         activityLogService.saveActivityLog(activityLog);
    }

}
