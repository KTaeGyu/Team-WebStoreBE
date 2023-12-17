package dasanda.BE.dto.member;

import jakarta.validation.constraints.NotBlank;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MemberLoginDto {

    @NotBlank
    private String email;

    @NotBlank
    private String password;

    @Builder
    protected MemberLoginDto(String email, String password){
        this.email = email;
        this.password = password;
    }
}
