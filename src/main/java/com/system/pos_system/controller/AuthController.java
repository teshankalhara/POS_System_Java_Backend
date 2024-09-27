package com.system.pos_system.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.system.pos_system.entity.User;
import com.system.pos_system.security.JwtUtils;

@RestController
@RequestMapping("/user")
@CrossOrigin("*")
public class AuthController {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtils jwtUtils;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User user) {
        if (user.getUsername() == null || user.getUsername() == "") {
            return ResponseEntity.status(400).body("Username Required!");
        }
        if (user.getPassword() == null || user.getPassword() == "") {
            return ResponseEntity.status(400).body("Password Required!");
        }
        try {
            Authentication authentication = authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
            SecurityContextHolder.getContext().setAuthentication(authentication);
            String jwtToken = jwtUtils.generateJwtToken(authentication);
            return ResponseEntity.status(200).body(jwtToken);
        } catch (AuthenticationException e) {
            return ResponseEntity.status(401).body("Authentication Failed!");
        } catch (Exception e) {
            return ResponseEntity.status(403).body("Username or Password Incorrect!");
        }
    }
}
