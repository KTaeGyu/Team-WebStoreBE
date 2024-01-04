package dasanda.BE.dto.article;

import dasanda.BE.domain.Member;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;
import lombok.Getter;

@Getter
public class ArticleDto {

    @NotBlank
    private String title;

    @NotBlank
    private String content;

    private Float score;

    private Long memberId;

    private Long itemId;

    @Builder
    protected ArticleDto(String title, String content, Float score, Long memberId, Long itemId){
        this.title = title;
        this.content = content;
        this.score = score;
        this.memberId = memberId;
        this.itemId = itemId;
    }
}
