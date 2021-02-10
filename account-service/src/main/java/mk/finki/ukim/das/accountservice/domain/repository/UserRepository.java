package mk.finki.ukim.das.accountservice.domain.repository;

import mk.finki.ukim.das.accountservice.domain.model.User;
import mk.finki.ukim.das.accountservice.domain.model.UserId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, UserId> {

    // returns a User Entity by username
    Optional<User> findByUsername(String username);

    // returns true if username exists in database, false if it doesn't
    Boolean existsByUsername(String username);

    // returns true if email exists in database, false if it doesn't
    Boolean existsByEmail(String email);

}
