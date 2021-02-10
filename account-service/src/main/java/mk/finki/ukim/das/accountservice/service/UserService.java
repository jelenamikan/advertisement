package mk.finki.ukim.das.accountservice.service;

import mk.finki.ukim.das.accountservice.domain.model.User;
import mk.finki.ukim.das.accountservice.domain.model.UserId;

import java.util.Set;

public interface UserService {

    Set<User> findAll();

    UserId getId(String username);

}
