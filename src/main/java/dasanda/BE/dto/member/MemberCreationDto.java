package dasanda.BE.dto.member;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;

@Getter
public class MemberCreationDto {

    @NotBlank
    private String email;

    @NotBlank
    private String nickname;

    @NotBlank
    private String phone;

    @NotBlank
    private String password;

    @NotBlank
    private String city;

    @NotBlank
    private String street;

    @NotBlank
    private String zipcode;

    @Builder
    protected MemberCreationDto(String email, String nickname, String phone, String password,
                                String city, String street, String zipcode){
        this.email = email;
        this.nickname = nickname;
        this.phone = phone;
        this.password = password;
        this.city = city;
        this.street = street;
        this.zipcode = zipcode;
    }
}
