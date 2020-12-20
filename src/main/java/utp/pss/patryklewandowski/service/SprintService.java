package utp.pss.patryklewandowski.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import utp.pss.patryklewandowski.model.Sprint;
import utp.pss.patryklewandowski.repository.SprintRepository;

import java.util.List;

@Service
public class SprintService {

    private SprintRepository sprintRepository;

    @Autowired
    public void setSprintRepository(SprintRepository sprintRepository) {
        this.sprintRepository = sprintRepository;
    }

    public List<Sprint> findAll() {
        return sprintRepository.findAll();
    }
    public List<Sprint> findByProjectId(Long projectId) {
        return sprintRepository.findByProjectId(projectId);
    }
    public Sprint findById(Long id) {
        return sprintRepository.findById(id).orElse(null);
    }
    public Sprint save(Sprint sprint) {
        return sprintRepository.save(sprint);
    }
    public void remove(Sprint sprint) {
        sprintRepository.delete(sprint);
    }
}
