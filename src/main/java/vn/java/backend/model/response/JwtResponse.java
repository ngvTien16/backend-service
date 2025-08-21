package vn.java.backend.model.response;

import lombok.*;

import java.time.Instant;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor@Builder
public class JwtResponse {
    private String message;
    private Long userId;
    private String username;
    private String email;

    private String role;                 // CUSTOMER/SELLER/ADMIN
    private List<String> authorities;    // ["ROLE_CUSTOMER"]

    private String tokenType;            // "Bearer"
    private String token;

    private Instant issuedAt;
    private Instant expiresAt;

}
