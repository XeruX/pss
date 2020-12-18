package utp.pss.patryklewandowski.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import utp.pss.patryklewandowski.model.StoryPoint;
import utp.pss.patryklewandowski.repository.StoryPointRepository;

import java.util.List;

@Service
public class StoryPointService {

    private StoryPointRepository storyPointRepository;

    @Autowired
    public void setStoryPointRepository(StoryPointRepository storyPointRepository) {
        this.storyPointRepository = storyPointRepository;
    }

    public List<StoryPoint> findAll() {
        return storyPointRepository.findAll();
    }
    public List<StoryPoint> findByTaskId(Long taskId) {
        return storyPointRepository.findByTaskId(taskId);
    }
    public StoryPoint findById(Long id) {
        return storyPointRepository.findById(id).orElse(null);
    }
    public StoryPoint add(StoryPoint sp) {
        return storyPointRepository.save(sp);
    }
    public void remove(StoryPoint sp) {
        storyPointRepository.delete(sp);
    }
}
