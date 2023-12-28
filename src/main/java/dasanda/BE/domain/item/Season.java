package dasanda.BE.domain.item;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Season {

    @Id
    @GeneratedValue
    @Column(name = "season_id")
    private Long id;

    @NotBlank
    private Integer target_year;

    @NotBlank
    private String season;

    @JsonBackReference
    @OneToMany(mappedBy = "season")
    private List<ItemSeason> itemSeasons = new ArrayList<>();

    @Builder
    protected Season(Integer target_year, String season, List<ItemSeason> itemSeasons){
        this.target_year = target_year;
        this.season = season;
        this.itemSeasons = itemSeasons;

    }

}
