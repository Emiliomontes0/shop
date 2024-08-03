package com.Lunexconline.Ecommerce.Controller;

import com.Lunexconline.Ecommerce.Entity.User;
import com.Lunexconline.Ecommerce.Model.AuthenticationRequest;
import com.Lunexconline.Ecommerce.Model.AuthenticationResponse;
import com.Lunexconline.Ecommerce.Service.CustomUserDetailsService;
import com.Lunexconline.Ecommerce.Service.UserService;
import com.Lunexconline.Ecommerce.Util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final CustomUserDetailsService userDetailsService;
    private final JwtUtil jwtUtil;
    private static final String HARDCODED_EMAIL = "example@example.com";
    private static final String HARDCODED_PASSWORD = "password";

    @Autowired
    public UserController(UserService userService, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager, CustomUserDetailsService userDetailsService, JwtUtil jwtUtil) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.userDetailsService = userDetailsService;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/register")
    public ResponseEntity<User> registerUser(@RequestBody User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword())); // Encode password before saving
        User savedUser = userService.saveUser(user);
        return ResponseEntity.ok(savedUser);
    }






    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthenticationRequest authenticationRequest) {
        User user = userService.findByEmail(authenticationRequest.getEmail());
        if (user != null && passwordEncoder.matches(authenticationRequest.getPassword(), user.getPassword())) {
            UserDetails userDetails = userDetailsService.loadUserByUsername(user.getEmail());
            final String jwt = jwtUtil.generateToken(userDetails);
            return ResponseEntity.ok("Login successful: " + new AuthenticationResponse(jwt));
        } else {
            return ResponseEntity.status(401).body("Incorrect email or password");
        }
    }

    /*@PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthenticationRequest authenticationRequest) {
        User user = userService.findByEmail(authenticationRequest.getEmail());
        if (user != null && passwordEncoder.matches(authenticationRequest.getPassword(), user.getPassword())) {
            return ResponseEntity.ok("Login successful");
        } else {
            return ResponseEntity.status(401).body("Incorrect email or password");
        }
    }*/

}

