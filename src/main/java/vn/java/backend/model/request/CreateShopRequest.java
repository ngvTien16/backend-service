package vn.java.backend.model.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class CreateShopRequest {
    @NotBlank
    private String name;
    private String description;
}
