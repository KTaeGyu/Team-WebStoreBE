package dasanda.BE.domain.item;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ItemSeason {

    @Id
    @GeneratedValue
    @Column(name = "item_season_id")
    private Long id;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "item_id")
    private Item item;

    @JsonManagedReference
    @ManyToOne
    @JoinColumn(name = "season_id")
    private Season season;

    @Builder
    protected ItemSeason(Item item, Season season){
        this.item = item;
        this.season = season;

    }

}
