package dasanda.BE.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import dasanda.BE.domain.item.Item;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

@Entity
@Getter @Setter
@NoArgsConstructor
public class Article {

    @Id
    @GeneratedValue
    @Column(name = "article_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonBackReference
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonBackReference
    @JoinColumn(name = "item_id")
    private Item item;

    @NotBlank
    private String title;

    @NotBlank
    private String content;

    private Float score;

    @Builder
    public Article(String title, String content, Float score ,Member member, Item item){
        this.title = title;
        this.content = content;
        this.score = score;
        setMember(member);
        setItem(item);
    }

    public void setMember(Member member) {
        this.member = member;
        member.getArticles().add(this);
    }

    public void setItem(Item item){
        this.item = item;
        item.getArticles().add(this);
    }
}
