package vn.java.backend.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import vn.java.backend.model.entity.Role;
import vn.java.backend.model.entity.Shop;
import vn.java.backend.model.entity.User;
import vn.java.backend.model.request.CreateShopRequest;
import vn.java.backend.model.response.ShopResponse;
import vn.java.backend.repository.ShopRepository;
import vn.java.backend.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class ShopService {

    private final ShopRepository shopRepository;
    private final UserRepository userRepository;

    @Transactional
    public ShopResponse registerShop(String username , CreateShopRequest req){
        User user = userRepository.findByUsername(username).orElseThrow(()-> new RuntimeException("User not found : "+ username));

        if(user.getRole() != Role.CUSTOMER){
            throw new RuntimeException("Only CUSTOMER can register a shop");
        }


        if (shopRepository.existsByOwner(user)){
            throw new RuntimeException("User already has a shop ");
        }

        // name shop unique
        if(shopRepository.existsByName(req.getName())){
            throw new RuntimeException("Shop name already exists");
        }

        // create shop
        Shop shop = Shop.builder().name(req.getName()).description(req.getDescription()).owner(user).build();
        shop = shopRepository.save(shop);

        user.setRole(Role.SELLER);
        userRepository.save(user);

        return ShopResponse.builder()
                .id(shop.getId())
                .name(shop.getName())
                .description(shop.getDescription())
                .ownerId(user.getId())
                .ownerUsername(user.getUsername())
                .build();

    }



}
