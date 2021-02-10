package mk.finki.ukim.das.accountservice.service;

import mk.finki.ukim.das.accountservice.model.User;

import java.util.Optional;
import java.util.Set;

public interface UserService {

    // returns all users in database
    Set<User> findAll();

    // returns the ID of the user with the given username
    Long getId(String username);

}
