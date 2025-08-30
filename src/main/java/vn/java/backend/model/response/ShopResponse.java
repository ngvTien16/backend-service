package vn.java.backend.model.response;

import lombok.*;


@Getter
@Setter
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ShopResponse {
    private Long id ;
    private String name;
    private String description;
    private Long ownerId;
    private String ownerUsername;
}
