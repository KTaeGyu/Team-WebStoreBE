package dasanda.BE.domain.item;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Entity
@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SubCategory {

    @Id
    @GeneratedValue
    @Column(name = "sub_category_id")
    private Long id;

    @NotBlank
    private String name;

    @ManyToOne(targetEntity = MajorCategory.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "major_id")
    private MajorCategory majorCategory;

    @Builder
    protected SubCategory(String name, MajorCategory majorCategory){
        this.name = name;
        this.majorCategory = majorCategory;
    }
}
