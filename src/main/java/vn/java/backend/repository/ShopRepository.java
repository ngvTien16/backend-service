package vn.java.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import vn.java.backend.model.entity.Shop;
import vn.java.backend.model.entity.User;

import java.util.Optional;


public interface ShopRepository extends JpaRepository<Shop,Long> {
    Optional<Shop> findByOwner(User owner);
    boolean existsByOwner(User owner);
    boolean existsByName(String name );
}
