package vn.java.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import vn.java.backend.model.entity.Category;

public interface CategoryRepository extends JpaRepository<Category,Long> {
}
