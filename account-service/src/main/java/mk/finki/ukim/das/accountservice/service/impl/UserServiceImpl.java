package mk.finki.ukim.das.accountservice.service.impl;

import mk.finki.ukim.das.accountservice.domain.model.User;
import mk.finki.ukim.das.accountservice.domain.model.UserId;
import mk.finki.ukim.das.accountservice.domain.repository.UserRepository;
import mk.finki.ukim.das.accountservice.service.UserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // returns all users in the database
    @Override
    public Set<User> findAll() {
        Set<User> users = new HashSet<>();
        userRepository.findAll().iterator().forEachRemaining(users::add);
        return users;
    }

    // returns the ID of the user with the given username
    @Override
    public UserId getId(String username) {
        Optional<User> user = userRepository.findByUsername(username);
        if (!user.isPresent()){
            throw new UsernameNotFoundException("User Not Found with username: " + username);
        }
        return user.get().getId();
    }

    // returns UserDetails for the given user by his username, if it doesn't exists throws Exception
    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + username));

        return UserDetailsImpl.build(user);
    }

}
