package utp.pss.patryklewandowski.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import utp.pss.patryklewandowski.model.Sprint;
import utp.pss.patryklewandowski.model.Task;
import utp.pss.patryklewandowski.model.User;
import utp.pss.patryklewandowski.repository.TaskRepository;

import java.util.List;

@Service
public class TaskService {

    private TaskRepository taskRepository;

    @Autowired
    public void setTaskRepository(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public List<Task> findAll() {
        return taskRepository.findAll();
    }
    public List<Task> findBySprint(Sprint sprint) {
        return taskRepository.findBySprint(sprint);
    }
    public List<Task> findByAssignedUser(User user) {
        return taskRepository.findByAssignedUser(user);
    }
    public Task findById(Long id) {
        return taskRepository.findById(id).orElse(null);
    }
    public Task add(Task task) {
       return taskRepository.save(task);
    }
    public void remove(Task task) {
        taskRepository.delete(task);
    }
}
