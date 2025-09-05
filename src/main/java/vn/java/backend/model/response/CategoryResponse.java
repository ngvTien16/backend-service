package vn.java.backend.model.response;

import lombok.*;

@Getter
@Setter
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CategoryResponse {
        private Long id ;
        private String name ;
        private String description;
}
