package vn.java.backend.model.response;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
public class CategoryResponse {
        private Long id ;
        private String name ;
        private String description;
}
