package dasanda.BE.domain.item;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

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

    @Builder
    protected Brand(String name){
        this.name = name;
    }
}
