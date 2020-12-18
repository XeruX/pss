package utp.pss.patryklewandowski.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import utp.pss.patryklewandowski.model.Project;
import utp.pss.patryklewandowski.model.User;
import utp.pss.patryklewandowski.repository.ProjectRepository;

import java.util.List;

@Service
public class ProjectService {

    public ProjectRepository projectRepository;

    @Autowired
    public void setProjectRepository(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    public List<Project> findAll() {
        return projectRepository.findAll();
    }
    public Project findById(Long id) {
        return projectRepository.findById(id).orElse(null);
    }
    public Project findByName(String name) {
        return projectRepository.findByName(name);
    }
    public Project save(Project project) {
        return projectRepository.save(project);
    }
    public Project save(Project project, User projectAdmin) {
        project.getParticipants().add(projectAdmin);
        return projectRepository.save(project);
    }
    public void remove(Project project) {
        projectRepository.delete(project);
    }
}
