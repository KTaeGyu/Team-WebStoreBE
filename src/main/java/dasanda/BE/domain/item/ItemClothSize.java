package dasanda.BE.domain.item;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ItemClothSize {

    @Id
    @GeneratedValue
    @Column(name = "item_cloth_size_id")
    private Long id;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "item_id")
    private Item item;

    @JsonManagedReference
    @ManyToOne
    @JoinColumn(name = "cloth_size_id")
    private ClothSize clothSize;

    @Builder
    protected ItemClothSize(Item item, ClothSize clothSize){
        this.item = item;
        this.clothSize = clothSize;

    }

}
