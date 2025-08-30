package vn.java.backend.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import vn.java.backend.model.entity.Product;
import vn.java.backend.model.entity.Shop;
import vn.java.backend.model.response.ProductResponse;
import vn.java.backend.model.response.ShopResponse;
import vn.java.backend.repository.ProductRepository;




@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    //phan trang ...
    public Page<ProductResponse> getAllProducts(Pageable pageable, String keyword){
        Page<Product> productPage;
        if(keyword != null && !keyword.isEmpty()){
            productPage = productRepository.findByNameContainingIgnoreCase(keyword,pageable);
        }
        else {
            productPage = productRepository.findAll(pageable);
        }
        return productPage.map(this::convertToDto);
    }
    public ProductResponse getProductById(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Không tìm thấy sản phẩm với ID: " + id));
        return convertToDto(product);
    }
    private ProductResponse convertToDto(@org.jetbrains.annotations.NotNull Product product) {
        ProductResponse dto = new ProductResponse();
        dto.setId(product.getId());
        dto.setName(product.getName());
        dto.setDescription(product.getDescription());
        dto.setPrice(product.getPrice());

        if (product.getShop() != null) {

            ShopResponse shopDto = new ShopResponse();
            shopDto.setId(product.getShop().getId());
            shopDto.setName(product.getShop().getName());
            dto.setShop(shopDto);
        }
        return dto;
    }

}
