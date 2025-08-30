package vn.java.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.java.backend.model.entity.User;
import vn.java.backend.model.request.LoginRequest;
import vn.java.backend.model.request.RegisterRequest;
import vn.java.backend.model.response.JwtResponse;
import vn.java.backend.service.JwtService;
import vn.java.backend.service.UserService;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    private UserService userService;

    @Autowired
    private JwtService jwtService;

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
    public ResponseEntity<JwtResponse> login(@RequestBody LoginRequest request) {
        User user = userService.login(request);

        // Tạo JWT có claim authorities từ role
        String token = jwtService.generateToken(user);

        // Lấy authorities đưa ra response (giống trong token)
        List<String> authorities = List.of("ROLE_" + user.getRole().name());

        JwtResponse resp = JwtResponse.builder()
                .message("Login successful!")
                .userId(user.getId())
                .username(user.getUsername())
                .email(user.getEmail())
                .role(user.getRole().name())
                .authorities(authorities)
                .tokenType("Bearer")
                .token(token)
                .issuedAt(jwtService.getIssuedAt(token).toInstant())
                .expiresAt(jwtService.getExpiration(token).toInstant())
                .build();

        return ResponseEntity.ok(resp);
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout() {
        return ResponseEntity.ok("Logout successful! (Client should remove JWT token)");
    }
    
}
