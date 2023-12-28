package dasanda.BE.domain.item;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.List;

@Entity
@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Brand {

    @Id
    @GeneratedValue
    @Column(name = "brand_id")
    private Long id;

    @NotBlank
    private String name;

    @JsonBackReference
    @OneToMany(mappedBy = "brand")
    private List<Item> items;

    @Builder
    protected Brand(String name, List<Item> items){
        this.name = name;
        this.items = items;

    }
}
