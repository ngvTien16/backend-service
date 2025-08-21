package vn.java.backend.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/test")
public class TestController {

    @GetMapping("/admin")
    @PreAuthorize("hasRole('ADMIN')")
    public String adminOnly() {
        return "Hello Admin!";
    }

    @GetMapping("/seller")
    @PreAuthorize("hasRole('SELLER')")
    public String sellerOnly() {
        return "Hello Seller!";
    }

    @GetMapping("/customer")
    @PreAuthorize("hasRole('CUSTOMER')")
    public String customerOnly() {
        return "Hello Customer!";
    }
}
