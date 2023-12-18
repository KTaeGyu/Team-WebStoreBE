package dasanda.BE.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Entity
@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Item {

    @Id @GeneratedValue
    @Column(name = "item_id")
    private Long id;

    @NotBlank
    private String name;

    @NotBlank
    private String brand;

    @NotBlank
    private Long price;

    @Builder
    protected Item(String name, String brand, Long price){
        this.name = name;
        this.brand = brand;
        this.price = price;
    }

}
