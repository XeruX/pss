package utp.pss.patryklewandowski.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import utp.pss.patryklewandowski.model.Sprint;
import utp.pss.patryklewandowski.service.SprintService;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/sprint")
public class SprintRestController {

    private final SprintService sprintService;

    @Autowired
    public SprintRestController(SprintService sprintService) {
        this.sprintService = sprintService;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Sprint> findAll() {
        return sprintService.findAll();
    }

    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Sprint> findById(@PathVariable Long id) {
        Sprint sprint = sprintService.findById(id);
        if(sprint != null) {
            return ResponseEntity.ok(sprint);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping(path = "/project/{projectId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Sprint>> findByProjectId(@PathVariable Long projectId) {
        List<Sprint> sprint = sprintService.findByProjectId(projectId);
        if(sprint != null) {
            return ResponseEntity.ok(sprint);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> save(@RequestBody Sprint sprint) {
        if(sprint.getId() != null) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
        else if(sprint.getProject() == null) {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build();
        }
        else {
            Sprint saved = sprintService.save(sprint);
            URI location = ServletUriComponentsBuilder
                    .fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(saved.getId())
                    .toUri();
            return ResponseEntity.created(location).body(saved);
        }
    }

    /*@PutMapping(path = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> update(@RequestBody Sprint updateSprint, @PathVariable Long id) {
        if(updateSprint.getId() == id && sprintService.findById(id) != null) {
            sprintService.save(updateSprint);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }*/

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<?> remove(@PathVariable Long id) {
        Sprint sprint = sprintService.findById(id);
        if(sprint != null) {
            sprintService.remove(sprint);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
