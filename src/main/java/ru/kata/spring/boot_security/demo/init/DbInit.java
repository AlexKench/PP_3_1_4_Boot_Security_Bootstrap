package ru.kata.spring.boot_security.demo.init;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.kata.spring.boot_security.demo.models.Role;
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.repositories.RoleRepository;
import ru.kata.spring.boot_security.demo.services.UserService;


import javax.annotation.PostConstruct;
import java.util.Set;

@Component
public class DbInit {
    private final UserService userService;
    private final RoleRepository roleRepository;
    @Autowired
    public DbInit(UserService userService, RoleRepository roleRepository) {
        this.userService = userService;
        this.roleRepository = roleRepository;
    }


    @PostConstruct
    private void postConstruct() {
        Role roleAdmin = new Role("ROLE_ADMIN");
        Role roleUser = new Role("ROLE_USER");
        roleRepository.save(roleAdmin);
        roleRepository.save(roleUser);

        User user = new User("user", "user", 500, "user","user", Set.of(roleUser));
        User admin = new User("admin", "admin", 500, "admin","admin", Set.of(roleAdmin));
        userService.save(user);
        userService.save(admin);
    }
}
