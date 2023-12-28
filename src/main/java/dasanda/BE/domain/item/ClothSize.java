package dasanda.BE.domain.item;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ClothSize {

    @Id
    @GeneratedValue
    @Column(name = "cloth_size_id")
    private Long id;

    @NotBlank
    private String type;

    @NotNull
    @Column(name = "cloth_size")
    private Integer clothSize;

    @JsonBackReference
    @OneToMany(mappedBy = "clothSize")
    private List<ItemClothSize> itemClothSizes = new ArrayList<>();

    @Builder
    protected ClothSize(String type, Integer clothSize, List<ItemClothSize> itemClothSizes){
        this.type = type;
        this.clothSize = clothSize;
        this.itemClothSizes = itemClothSizes;

    }

}
