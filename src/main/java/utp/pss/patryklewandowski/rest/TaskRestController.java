package utp.pss.patryklewandowski.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import utp.pss.patryklewandowski.model.Sprint;
import utp.pss.patryklewandowski.model.Task;
import utp.pss.patryklewandowski.model.User;
import utp.pss.patryklewandowski.service.SprintService;
import utp.pss.patryklewandowski.service.TaskService;
import utp.pss.patryklewandowski.service.UserService;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/task")
public class TaskRestController {

    public final TaskService taskService;
    public final SprintService sprintService;
    public final UserService userService;

    @Autowired
    public TaskRestController(TaskService taskService, SprintService sprintService, UserService userService) {
        this.taskService = taskService;
        this.sprintService = sprintService;
        this.userService = userService;
    }

    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Task> findById(@PathVariable Long id) {
        Task task = taskService.findById(id);
        if(task == null) {
            return ResponseEntity.notFound().build();
        }
        else {
            return ResponseEntity.ok(task);
        }
    }
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Task> findAll() {
        return taskService.findAll();
    }
    @GetMapping(path = "/sprint/{sprintId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Task>> findBySprint(@PathVariable Long sprintId) {
        Sprint sprint = sprintService.findById(sprintId);
        if(sprint == null)
            return ResponseEntity.notFound().build();
        List<Task> task = taskService.findBySprint(sprint);
        if(task.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(task);
        }
    }
    @GetMapping(path = "/user/{userName}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Task>> findByAssignedUser(@PathVariable String userName) {
        User user = userService.findByLogin(userName);
        if(user == null)
            return ResponseEntity.notFound().build();
        List<Task> task = taskService.findByAssignedUser(user);
        if(task.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(task);
        }
    }
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> add(@RequestBody Task task) {
        if(task.getId() != null
            || task.getSprint() == null
            || task.getName().isBlank()
            || task.getDescription().isBlank()
            || task.getPriority() == null
            || task.getAssignedUser() == null) {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build();
        }
        else {
            Task saved = taskService.add(task);
            URI location = ServletUriComponentsBuilder
                    .fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(saved.getId())
                    .toUri();
            return ResponseEntity.created(location).body(saved);
        }
    }
    @DeleteMapping(path = "/{taskId}")
    public ResponseEntity<?> remove(@PathVariable Long taskId) {
        Task task = taskService.findById(taskId);
        if(task != null) {
            taskService.remove(task);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
