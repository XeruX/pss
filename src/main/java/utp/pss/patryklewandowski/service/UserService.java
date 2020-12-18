package utp.pss.patryklewandowski.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import utp.pss.patryklewandowski.model.User;
import utp.pss.patryklewandowski.model.UserRole;
import utp.pss.patryklewandowski.repository.UserRepository;
import utp.pss.patryklewandowski.repository.UserRoleRepository;

import javax.validation.constraints.NotNull;
import java.util.List;

@Service
public class UserService {

    private static final String DEFAULT_ROLE = "ROLE_USER";
    private static final String ADMIN_ROLE = "ROLE_ADMIN";
    private UserRepository userRepository;
    private UserRoleRepository roleRepository;
//    private PasswordEncoder passwordEncoder;

//    @Autowired
//    public UserService(PasswordEncoder passwordEncoder) {
//        this.passwordEncoder = passwordEncoder;
//    }

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Autowired
    public void setRoleRepository(UserRoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public List<User> findAll() {
        return  userRepository.findAll();
    }
    public User findByLogin(String login) {
        return userRepository.findById(login).orElse(null);
    }
    // Dodawanie nowego użytkownika
    public User addWithDefaultRole(User user) {
        UserRole defaultRole = roleRepository.findByRole(DEFAULT_ROLE);
        user.getRoles().add(defaultRole);
        //String passwordHash = passwordEncoder.encode(user.getPassword());
        //user.setPassword(passwordHash);
        return userRepository.save(user);
    }
    // Aktualizacja już istniejącego użytkownika
    public void update(User user, @NotNull Boolean admin) {
        if(admin) {
            addAdmin(user);
        }
        userRepository.save(user);
    }
    public void remove(User user) {
        userRepository.delete(user);
    }
    public void addAdmin(User user) {
        UserRole adminRole = roleRepository.findByRole(ADMIN_ROLE);
        user.getRoles().add(adminRole);
    }
}
