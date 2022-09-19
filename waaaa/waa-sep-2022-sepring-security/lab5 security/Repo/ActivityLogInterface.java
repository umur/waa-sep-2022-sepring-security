package miu.edu.lab3.Repo;

import miu.edu.lab3.entity.ActivityLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ActivityLogInterface extends JpaRepository<ActivityLog,Long> {
}
