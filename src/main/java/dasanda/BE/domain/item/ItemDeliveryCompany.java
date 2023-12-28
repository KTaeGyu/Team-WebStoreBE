package dasanda.BE.domain.item;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ItemDeliveryCompany {

    @Id
    @GeneratedValue
    @Column(name = "item_delivery_company_id")
    private Long id;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "item_id")
    private Item item;

    @JsonManagedReference
    @ManyToOne
    @JoinColumn(name = "delivery_company_id")
    private DeliveryCompany deliveryCompany;

    @Builder
    protected ItemDeliveryCompany(Item item, DeliveryCompany deliveryCompany){
        this.item = item;
        this.deliveryCompany = deliveryCompany;

    }

}
