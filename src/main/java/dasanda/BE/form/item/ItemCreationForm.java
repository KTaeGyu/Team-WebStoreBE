package dasanda.BE.form.item;

import jakarta.validation.constraints.NotBlank;

public class ItemCreationForm {
    @NotBlank
    private String name;

    @NotBlank
    private String brand;

    @NotBlank
    private Long price;

    public String getName() {
        return name;
    }

    public String getBrand() {
        return brand;
    }

    public Long getPrice() {
        return price;
    }
}
