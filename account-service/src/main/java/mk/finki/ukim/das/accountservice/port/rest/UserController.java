package mk.finki.ukim.das.accountservice.port.rest;

import mk.finki.ukim.das.accountservice.domain.model.User;
import mk.finki.ukim.das.accountservice.domain.model.UserId;
import mk.finki.ukim.das.accountservice.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    // responds to GET request for users, returns a Set of all users
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Set<User> getAllObjects(){
        return userService.findAll();
    }

    // responds to GET request for one user by username, returns the user's ID
    @GetMapping("/{username}")
    @ResponseStatus(HttpStatus.OK)
    public UserId getUserById(@PathVariable String username){
        return userService.getId(username);
    }

}
