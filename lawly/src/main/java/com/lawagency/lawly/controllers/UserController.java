package com.lawagency.lawly.controllers;

import com.lawagency.lawly.dtos.LoginDTO;
import com.lawagency.lawly.dtos.RegistrationDTO;
import com.lawagency.lawly.dtos.UserTokenState;
import com.lawagency.lawly.model.Role;
import com.lawagency.lawly.model.User;
import com.lawagency.lawly.security.util.TokenGenerator;
import com.lawagency.lawly.services.UserService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private TokenGenerator tokenGenerator;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserService userService;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;


    @PostMapping("/login")
    public ResponseEntity<UserTokenState> createAuthenticationToken(
            @RequestBody LoginDTO loginDTO, HttpServletResponse response) {

        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginDTO.getUsername(), loginDTO.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = tokenGenerator.generate(loginDTO.getUsername());
        int expiresIn = tokenGenerator.getExpiredIn();

        return ResponseEntity.ok(new UserTokenState(jwt, expiresIn));
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody RegistrationDTO registrationDTO) {
        if (userService.existsByUsername(registrationDTO.getUsername())) {
            return ResponseEntity.badRequest().body("Username is already taken!");
        }

        if (userService.existsByEmail(registrationDTO.getEmail())) {
            return ResponseEntity.badRequest().body("Email is already in use!");
        }

        // Create new user's account
        Role role = new Role();
        role.setId(2L);
        role.setName("USER");

        User user = new User();
        user.setUsername(registrationDTO.getUsername());
        user.setPassword(passwordEncoder.encode(registrationDTO.getPassword()));
        user.setName(registrationDTO.getName());
        user.setSurname(registrationDTO.getSurname());
        user.setEmail(registrationDTO.getEmail());
        user.setRole(role);

        userService.save(user);

        return ResponseEntity.ok("User registered successfully");
    }

}
