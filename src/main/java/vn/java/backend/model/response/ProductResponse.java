package vn.java.backend.model.response;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;


@Getter
@Setter
@Data
public class ProductResponse {
    private Long id;
    private String name ;
    private String description;
    private BigDecimal price ;
    private ShopResponse shop;
}
