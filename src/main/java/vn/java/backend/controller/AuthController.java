package vn.java.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.java.backend.model.entity.User;
import vn.java.backend.model.request.LoginRequest;
import vn.java.backend.model.request.RegisterRequest;
import vn.java.backend.service.UserService;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegisterRequest request) {
        userService.registerUser(request);
        return ResponseEntity.ok("Check your Gmail to verify your account!");
    }

    @GetMapping("/verify")
    public ResponseEntity<String> verify(@RequestParam String code) {
        return userService.verifyUser(code)
                ? ResponseEntity.ok("Account verified successfully!")
                : ResponseEntity.badRequest().body("Invalid verification code!");
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequest request) {
        User user = userService.login(request);
        return ResponseEntity.ok("Login successful! Welcome " + user.getUsername());
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout() {
        return ResponseEntity.ok("Logout successful! (Client should remove JWT token)");
    }
}
