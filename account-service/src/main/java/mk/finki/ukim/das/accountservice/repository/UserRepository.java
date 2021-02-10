package mk.finki.ukim.das.accountservice.repository;

import mk.finki.ukim.das.accountservice.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    // returns a User Entity by username
    Optional<User> findByUsername(String username);

    // returns true if username exists in database, false if it doesn't
    Boolean existsByUsername(String username);

    // returns true if email exists in database, false if it doesn't
    Boolean existsByEmail(String email);

}
