package dasanda.BE.domain.item;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Item {

    @Id @GeneratedValue
    @Column(name = "item_id")
    private Long id;

    @NotBlank
    private String name;

    @NotBlank
    @Column(name = "image_path")
    private String imagePath;

    @NotNull
    private Long price;

    @NotNull
    @Column(name = "discount_rate")
    private Integer discountRate;

    @NotBlank
    private String gender;

    @Column(name = "shipping_date")
    private Integer shippingDate;

    @JsonManagedReference
    @ManyToOne
    @JoinColumn(name="brand_id")
    private Brand brand;

    @JsonManagedReference
    @ManyToOne
    @JoinColumn(name = "category_id")
    private SubCategory subCategory;

    @JsonManagedReference
    @OneToMany(mappedBy = "item")
    private List<ItemClothSize> itemClothSizes = new ArrayList<>();

    @JsonManagedReference
    @OneToMany(mappedBy = "item")
    private List<ItemDeliveryCompany> itemDeliveryCompanies = new ArrayList<>();

    @JsonManagedReference
    @OneToMany(mappedBy = "item")
    private List<ItemSeason> itemSeasons = new ArrayList<>();

    @Builder
    protected Item(String name, String imagePath, Long price, Integer discountRate, String gender,
                   Integer shippingDate, Brand brand, SubCategory subCategory, List<ItemClothSize> itemClothSizes,
                   List<ItemDeliveryCompany> itemDeliveryCompanies, List<ItemSeason> itemSeasons){
        this.name = name;
        this.imagePath = imagePath;
        this.price = price;
        this.discountRate = discountRate;
        this.gender = gender;
        this.shippingDate = shippingDate;
        this.brand = brand;
        this.subCategory = subCategory;
        this.itemClothSizes = itemClothSizes;
        this.itemDeliveryCompanies = itemDeliveryCompanies;
        this.itemSeasons = itemSeasons;

    }

}
