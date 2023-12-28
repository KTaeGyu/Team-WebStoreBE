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
public class MajorCategory {

    @Id
    @GeneratedValue
    @Column(name = "major_category_id")
    private Long id;

    @NotBlank
    @Column(unique = true)
    private String name;

    @JsonBackReference
    @OneToMany(mappedBy = "majorCategory")
    private List<SubCategory> subCategories;

    @Builder
    protected MajorCategory(String name, List<SubCategory> subCategories){
        this.name = name;
        this.subCategories = subCategories;

    }
}
