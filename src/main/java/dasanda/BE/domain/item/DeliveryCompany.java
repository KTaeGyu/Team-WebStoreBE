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
public class DeliveryCompany {

    @Id
    @GeneratedValue
    @Column(name = "delivery_company_id")
    private Long id;

    @NotBlank
    private String name;

    private String contact;

    @JsonBackReference
    @OneToMany(mappedBy = "deliveryCompany")
    private List<ItemDeliveryCompany> itemDeliveryCompanies = new ArrayList<>();

    @Builder
    protected DeliveryCompany(String name, String contact, List<ItemDeliveryCompany> itemDeliveryCompanies){
        this.name = name;
        this.contact = contact;
        this.itemDeliveryCompanies = itemDeliveryCompanies;

    }

}
