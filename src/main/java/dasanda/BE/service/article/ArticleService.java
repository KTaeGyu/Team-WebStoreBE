package dasanda.BE.service.article;

import dasanda.BE.domain.Article;
import dasanda.BE.domain.Member;
import dasanda.BE.dto.article.ArticleCreationDto;
import dasanda.BE.exception.article.UnauthorizedException;
import dasanda.BE.repository.article.ArticleRepository;
import dasanda.BE.repository.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ArticleService {

    private final ArticleRepository articleRepository;
    private final MemberRepository memberRepository;

    // 후기 작성
    @Transactional
    public Article createArticle(Article article){

        articleRepository.save(article);
        return article;
    }

    // 후기 수정
    @Transactional
    public Article updateArticle(Long memberId, Long articleId, ArticleCreationDto articleCreationDto){

        Article article = articleRepository.findOne(articleId);

        if (memberId != article.getMember().getId())  {
            throw new UnauthorizedException();
        }

        articleRepository.update(articleId, articleCreationDto);

        return article;
    }

    // 후기 삭제
    @Transactional
    public void deleteArticle(Long memberId, Long articleId){

        Article article = articleRepository.findOne(articleId);

        if (memberId != article.getMember().getId())  {
            throw new UnauthorizedException();
        }

        articleRepository.delete(articleId);

        return;
    }
}
