package utp.pss.patryklewandowski.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import utp.pss.patryklewandowski.model.StoryPoint;

import java.util.List;

@Repository
public interface StoryPointRepository extends JpaRepository<StoryPoint, Long> {
    List<StoryPoint> findByTaskId(Long taskId);
}
