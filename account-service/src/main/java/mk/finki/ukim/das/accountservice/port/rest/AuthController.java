package mk.finki.ukim.das.accountservice.port.rest;

import mk.finki.ukim.das.accountservice.config.JwtUtils;
import mk.finki.ukim.das.accountservice.domain.model.User;
import mk.finki.ukim.das.accountservice.domain.model.UserId;
import mk.finki.ukim.das.accountservice.domain.repository.UserRepository;
import mk.finki.ukim.das.accountservice.domain.request.LoginRequest;
import mk.finki.ukim.das.accountservice.domain.request.SignupRequest;
import mk.finki.ukim.das.accountservice.domain.response.JwtResponse;
import mk.finki.ukim.das.accountservice.domain.response.MessageResponse;
import mk.finki.ukim.das.accountservice.service.impl.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin(origins = "http://localhost:8080", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JwtUtils jwtUtils;

    // function that responds to POST requests for signing in,
    // returns ResponseEntity.OK (code 200) if the user has an account
    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

        return ResponseEntity.ok(new JwtResponse(jwt,
                userDetails.getId(),
                userDetails.getUsername(),
                userDetails.getEmail(),
                null));
    }

    // function that responds to POST requests for signing up,
    // returns ResponseEntity.BAD_REQUEST (code 400) if the request body is invalid
    // and ResponseEntity.OK (code 200) if the sign up was successful
    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
        if (userRepository.existsByUsername(signUpRequest.getUsername())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Username is already taken!"));
        }

        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Email is already in use!"));
        }

        // Create new user's account
        User user = new User(new UserId(), signUpRequest.getUsername(),
                signUpRequest.getEmail(),
                encoder.encode(signUpRequest.getPassword()));

        userRepository.save(user);

        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
    }
}

