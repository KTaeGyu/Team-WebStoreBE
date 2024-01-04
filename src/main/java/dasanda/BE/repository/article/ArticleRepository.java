package dasanda.BE.repository.article;

import dasanda.BE.domain.Article;
import dasanda.BE.dto.article.ArticleCreationDto;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class ArticleRepository {

    private final EntityManager em;

    // 후기 작성
    public void save(Article article){
        em.persist(article);
    }

    // 후기 조회
    public Article findOne(Long articleId){

        return em.find(Article.class, articleId);
    }

    // 후기 수정
    public void update(Long articleId, ArticleCreationDto articleCreationDto){

        Article article = em.find(Article.class, articleId);
        article.setTitle(articleCreationDto.getTitle());
        article.setContent(articleCreationDto.getContent());
        article.setScore(articleCreationDto.getScore());
    }

    // 후기 삭제
    public void delete(Long articleId){

        Article article = em.find(Article.class, articleId);
        em.remove(article);
    }

}
