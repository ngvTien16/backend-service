package vn.java.backend.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import vn.java.backend.model.entity.Category;
import vn.java.backend.model.entity.Product;
import vn.java.backend.model.entity.Shop;
import vn.java.backend.model.request.CreateProductRequest;
import vn.java.backend.model.request.UpdateProductRequest;
import vn.java.backend.model.response.ProductResponse;
import vn.java.backend.model.response.ShopResponse;
import vn.java.backend.repository.CategoryRepository;
import vn.java.backend.repository.ProductRepository;
import vn.java.backend.repository.ShopRepository;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepo;
    private final CategoryRepository categoryRepo;
    private final ShopRepository shopRepo;

    // CREATE
    @Transactional
    public ProductResponse create(CreateProductRequest req) {
        requireNonBlank(req.getName(), "Product name is required");
        requireNonNull(req.getPrice(), "Price is required");
        requireNonNull(req.getCategoryId(), "CategoryId is required");
        requireNonNull(req.getShopId(), "ShopId is required");
        requireNonNegative(req.getPrice(), "Price must be >= 0");

        Category cat = categoryRepo.findById(req.getCategoryId())
                .orElseThrow(() -> new RuntimeException("Category not found"));
        Shop shop = shopRepo.findById(req.getShopId())
                .orElseThrow(() -> new RuntimeException("Shop not found"));

        Product p = new Product();
        p.setCategory(cat);
        p.setShop(shop);
        p.setName(req.getName().trim());
        p.setDescription(req.getDescription());
        p.setPrice(req.getPrice());

        productRepo.save(p);
        return toDto(p);
    }

    // UPDATE
    @Transactional
    public ProductResponse update(Long id, UpdateProductRequest req) {
        Product p = productRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        if (req.getName() != null) {
            requireNonBlank(req.getName(), "Product name cannot be blank");
            p.setName(req.getName().trim());
        }
        if (req.getDescription() != null) {
            p.setDescription(req.getDescription());
        }
        if (req.getPrice() != null) {
            requireNonNegative(req.getPrice(), "Price must be >= 0");
            p.setPrice(req.getPrice());
        }
        if (req.getCategoryId() != null) {
            Category cat = categoryRepo.findById(req.getCategoryId())
                    .orElseThrow(() -> new RuntimeException("Category not found"));
            p.setCategory(cat);
        }
        if (req.getShopId() != null) {
            Shop shop = shopRepo.findById(req.getShopId())
                    .orElseThrow(() -> new RuntimeException("Shop not found"));
            p.setShop(shop);
        }

        productRepo.save(p);
        return toDto(p);
    }

    // DELETE
    @Transactional
    public void delete(Long id) {
        if (!productRepo.existsById(id)) throw new RuntimeException("Product not found");
        productRepo.deleteById(id);
    }

    // GET ONE
    public ProductResponse getById(Long id) {
        Product p = productRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));
        return toDto(p);
    }

    // LIST + FILTER (keyword/categoryId/shopId)
    public Page<ProductResponse> getAll(Pageable pageable, String keyword, Long categoryId, Long shopId) {
        Page<Product> page;
        boolean hasKw = keyword != null && !keyword.isBlank();
        boolean hasCat = categoryId != null;
        boolean hasShop = shopId != null;

        if (hasCat && hasShop) {
            page = productRepo.findByCategory_IdAndShop_Id(categoryId, shopId, pageable);
        } else if (hasCat) {
            page = productRepo.findByCategory_Id(categoryId, pageable);
        } else if (hasShop) {
            page = productRepo.findByShop_Id(shopId, pageable);
        } else if (hasKw) {
            page = productRepo.findByNameContainingIgnoreCase(keyword.trim(), pageable);
        } else {
            page = productRepo.findAll(pageable);
        }
        return page.map(this::toDto);
    }

    // MAPPER — khớp với ProductResponse hiện có (chỉ trả Shop)
    private ProductResponse toDto(Product product) {
        ProductResponse dto = new ProductResponse();
        dto.setId(product.getId());
        dto.setName(product.getName());
        dto.setDescription(product.getDescription());
        dto.setPrice(product.getPrice());

        if (product.getShop() != null) {
            ShopResponse shopDto = new ShopResponse();
            shopDto.setId(product.getShop().getId());
            shopDto.setName(product.getShop().getName());
            // Nếu Shop có description/owner: set thêm nếu cần
            // shopDto.setDescription(product.getShop().getDescription());
            // if (product.getShop().getOwner()!=null) { ... }
            dto.setShop(shopDto);
        }
        return dto;
    }

    // helpers
    private static void requireNonBlank(String s, String msg) {
        if (s == null || s.isBlank()) throw new RuntimeException(msg);
    }
    private static void requireNonNull(Object o, String msg) {
        if (o == null) throw new RuntimeException(msg);
    }
    private static void requireNonNegative(BigDecimal n, String msg) {
        if (n.signum() < 0) throw new RuntimeException(msg);
    }
}
