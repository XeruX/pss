package utp.pss.patryklewandowski.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import utp.pss.patryklewandowski.model.Sprint;
import utp.pss.patryklewandowski.model.Task;
import utp.pss.patryklewandowski.model.User;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findBySprint(Sprint sprint);
    List<Task> findByAssignedUser(User user);
}
