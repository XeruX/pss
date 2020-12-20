package utp.pss.patryklewandowski.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import utp.pss.patryklewandowski.model.StoryPoint;
import utp.pss.patryklewandowski.service.StoryPointService;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/sp")
public class StoryPointRestController {

    private final StoryPointService spService;

    @Autowired
    public StoryPointRestController(StoryPointService spService) {
        this.spService = spService;
    }

    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<StoryPoint> findById(@PathVariable Long id) {
        StoryPoint sp = spService.findById(id);
        if(sp == null) {
            return ResponseEntity.notFound().build();
        }
        else {
            return ResponseEntity.ok(sp);
        }
    }
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<StoryPoint> findAll() {
        return spService.findAll();
    }
    @GetMapping(path = "/task/{taskId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<StoryPoint>> findByTaskId(@PathVariable Long taskId) {
        List<StoryPoint> sp = spService.findByTaskId(taskId);
        if(sp.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(sp);
        }
    }
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> add(@RequestBody StoryPoint sp) {
        if(sp.getId() != null || sp.getDescription().isBlank()) {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build();
        }
        else {
            StoryPoint saved = spService.add(sp);
            URI location = ServletUriComponentsBuilder
                    .fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(saved.getId())
                    .toUri();
            return ResponseEntity.created(location).body(saved);
        }
    }
    @DeleteMapping(path = "/{storyPointId}")
    public ResponseEntity<?> remove(@PathVariable Long storyPointId) {
        StoryPoint sp = spService.findById(storyPointId);
        if(sp != null) {
            spService.remove(sp);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
