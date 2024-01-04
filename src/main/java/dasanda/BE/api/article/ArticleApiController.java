package dasanda.BE.api.article;

import dasanda.BE.domain.Article;
import dasanda.BE.domain.Member;
import dasanda.BE.domain.item.Item;
import dasanda.BE.dto.article.ArticleCreationDto;
import dasanda.BE.dto.article.ArticleDto;
import dasanda.BE.exception.article.UnauthorizedException;
import dasanda.BE.service.article.ArticleService;
import dasanda.BE.service.auth.JwtService;
import dasanda.BE.service.item.ItemService;
import dasanda.BE.service.member.MemberService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequiredArgsConstructor
public class ArticleApiController {

    private final ArticleService articleService;
    private final MemberService memberService;
    private final ItemService itemService;
    private final JwtService jwtService;

    // 후기 작성
    @PostMapping("/api/{memberId}/{itemId}/article")
    public ResponseEntity<Object> saveArticle(
            @PathVariable("memberId") Long memberId,
            @PathVariable("itemId") Long itemId,
            @RequestBody @Valid ArticleCreationDto articleCreationDto){

        Member member = memberService.findById(memberId);
        Item item = itemService.findItemById(itemId);

        Article article = Article.builder()
                .title(articleCreationDto.getTitle())
                .content(articleCreationDto.getContent())
                .score(articleCreationDto.getScore())
                .member(member)
                .item(item)
                .build();

        Article saveArticle = articleService.createArticle(article);

        ArticleDto articleDto = ArticleDto.builder()
                .title(saveArticle.getTitle())
                .content(saveArticle.getContent())
                .score(saveArticle.getScore())
                .memberId(saveArticle.getMember().getId())
                .itemId(saveArticle.getItem().getId())
                .build();

        return ResponseEntity.ok(articleDto);
    }

    // 후기 수정
    @PutMapping("/api/article/{articleId}")
    public ResponseEntity<Object> updateArticle(
            @RequestHeader("Authorization") String accessToken,
            @PathVariable("articleId") Long articleId,
            @RequestBody @Valid ArticleCreationDto articleCreationDto
    ){

        Long checkMemberId = jwtService.verifyMember(accessToken.substring(7));

        try{
            Article article = articleService.updateArticle(checkMemberId, articleId, articleCreationDto);

            ArticleDto updatedArticle = ArticleDto.builder()
                    .title(article.getTitle())
                    .content(article.getContent())
                    .score(article.getScore())
                    .memberId(article.getMember().getId())
                    .itemId(article.getItem().getId())
                    .build();

            return ResponseEntity.ok().body(updatedArticle);
        } catch (UnauthorizedException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(Map.of("message", "this user doesn't have permission"));
        }
    }

    @DeleteMapping("/api/article/{articleId}")
    public ResponseEntity<Object> deleteArticle(
            @RequestHeader("Authorization") String accessToken,
            @PathVariable("articleId") Long articleId
    ){

        Long checkMemberId = jwtService.verifyMember(accessToken.substring(7));

        try{
            articleService.deleteArticle(checkMemberId, articleId);

            return ResponseEntity.ok().body(Map.of("message", "delete success"));
        } catch (UnauthorizedException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(Map.of("message", "this user doesn't have permission"));
        }
    }
}
