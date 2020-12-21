package utp.pss.patryklewandowski.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import utp.pss.patryklewandowski.model.User;
import utp.pss.patryklewandowski.service.UserService;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserRestController {

    private final UserService userService;

    @Autowired
    public UserRestController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<User> findAll() {
        return userService.findAll();
    }

    @GetMapping(path = "/{userName}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> findByLogin(@PathVariable String userName) {
        User user = userService.findByLogin(userName);
        if(user != null) {
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> add(@RequestBody User user) {
        if(user.getLogin().isBlank() || user.getPassword().isBlank()) {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build();
        }
        else {
            User saved = userService.addWithDefaultRole(user);
            URI location = ServletUriComponentsBuilder
                    .fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(saved.getLogin())
                    .toUri();
            return ResponseEntity.created(location).body(saved);
        }
    }

    @DeleteMapping(path = "/{userName}")
    public ResponseEntity<?> remove(@PathVariable String userName) {
        User user = userService.findByLogin(userName);
        if(user != null) {
            userService.remove(user);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
