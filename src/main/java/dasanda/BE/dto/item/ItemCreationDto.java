package dasanda.BE.dto.item;

import dasanda.BE.domain.item.Brand;
import dasanda.BE.domain.item.SubCategory;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;

@Getter
public class ItemCreationDto {
    @NotBlank
    private String name;

    @NotBlank
    private Long price;

    @NotBlank
    private Brand brand;

    @NotBlank
    private SubCategory category;

    @Builder
    protected ItemCreationDto(String name, Long price, Brand brand, SubCategory category){
        this.name = name;
        this.brand = brand;
        this.price = price;
        this.category = category;
    }
}
