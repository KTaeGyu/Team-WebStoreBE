package dasanda.BE.domain.item;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    private Long price;

    @JsonIgnore
    @ManyToOne(targetEntity=Brand.class, fetch=FetchType.LAZY)
    @JoinColumn(name="brand_id")
    private Brand brand;

    @JsonIgnore
    @ManyToOne(targetEntity = SubCategory.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private SubCategory category;

    @Builder
    protected Item(String name, Long price, Brand brand, SubCategory category){
        this.name = name;
        this.price = price;
        this.brand = brand;
        this.category = category;
    }

}
