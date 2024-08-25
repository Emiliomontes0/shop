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
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;



@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final CustomUserDetailsService userDetailsService;
    private final JwtUtil jwtUtil;

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



    @PostMapping("/test-response")
    public ResponseEntity<?> testResponse(HttpServletResponse response) {
        return ResponseEntity.ok("Response works");
    }



    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthenticationRequest authenticationRequest, HttpServletResponse response) {
        User user = userService.findByEmail(authenticationRequest.getEmail());
        if (user != null && passwordEncoder.matches(authenticationRequest.getPassword(), user.getPassword())) {
            UserDetails userDetails = userDetailsService.loadUserByUsername(user.getEmail());
            final String jwt = jwtUtil.generateToken(userDetails);

            Cookie cookie = new Cookie("token", jwt);
            cookie.setHttpOnly(true);
            cookie.setSecure(true); // Use true if your app uses HTTPS
            cookie.setPath("/");
            cookie.setMaxAge(7 * 24 * 60 * 60); // 7 days
            response.addCookie(cookie);

            return ResponseEntity.ok(new AuthenticationResponse(jwt));
        } else {
            return ResponseEntity.status(401).body("Incorrect email or password");
        }
    }
}

