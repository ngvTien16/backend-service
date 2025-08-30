package vn.java.backend.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vn.java.backend.model.request.CreateShopRequest;
import vn.java.backend.model.response.ShopResponse;

import vn.java.backend.repository.ShopRepository;
import vn.java.backend.service.ShopService;

@RestController
@RequestMapping("/api/seller")
@RequiredArgsConstructor
public class SellerController {

    private final ShopService shopService;

    @PostMapping("/register-shop")
    public ResponseEntity<ShopResponse> registerShop(
            @AuthenticationPrincipal UserDetails principal,
            @Valid @RequestBody CreateShopRequest req) {
        ShopResponse resp = shopService.registerShop(principal.getUsername(), req);
        return ResponseEntity.ok(resp);

    }


}
