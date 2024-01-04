package dasanda.BE.dto.article;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;
import lombok.Getter;

@Getter
public class ArticleCreationDto {

    private String title;

    private String content;

    private Float score;

    @Builder
    protected ArticleCreationDto(String title, String content, Float score){
        this.title = title;
        this.content = content;
        this.score = score;
    }
}
