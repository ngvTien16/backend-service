package vn.java.backend.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.java.backend.model.entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {
    Page<Product> findByNameContainingIgnoreCase(String name , Pageable pageable);
    Page<Product> findByCategory_Id(Long categoryId, Pageable pageable);
    Page<Product> findByShop_Id(Long shopId, Pageable pageable);
    Page<Product> findByCategory_IdAndShop_Id(Long categoryId, Long shopId, Pageable pageable);

}
