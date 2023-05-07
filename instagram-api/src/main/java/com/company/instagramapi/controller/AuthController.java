package com.company.instagramapi.controller;

import com.company.instagramapi.modal.User;
import com.company.instagramapi.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth/")
public class AuthController {
    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }
    @PostMapping("register")
    public ResponseEntity<User> registerUser(@RequestBody User user){
     return ResponseEntity.ok(userService.registerUser(user));
    }
    @PostMapping("login")
    public ResponseEntity<User> loginUser(Authentication authentication){
        User user = userService.findUserByUsername(authentication.getName());
        if (user == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(user);
    }
}
