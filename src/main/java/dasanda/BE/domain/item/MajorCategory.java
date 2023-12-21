package dasanda.BE.domain.item;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Entity
@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MajorCategory {

    @Id
    @GeneratedValue
    @Column(name = "major_category_id")
    private Long id;

    @NotBlank
    @Column(unique = true)
    private String name;

    @Builder
    protected MajorCategory(String name){
        this.name = name;
    }
}
