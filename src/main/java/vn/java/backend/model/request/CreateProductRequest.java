package vn.java.backend.model.request;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter @Setter
public class CreateProductRequest {
    private Long categoryId;
    private Long shopId;
    private String name;
    private String description;
    private BigDecimal price;
}
