package vn.java.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import vn.java.backend.model.entity.Category;

import java.util.Optional;


public interface CategoryRepository extends JpaRepository<Category,Long> {
    boolean existsByName(String name);
    Optional<Category> findByName(String name);
}
