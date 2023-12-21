package dasanda.BE.repository.item;


import dasanda.BE.domain.item.Item;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class ItemRepository {

    private final EntityManager em;

    // 아이템 저장
    public void save(Item item) {
        em.persist(item);
    };

    // 전체 아이템 조회
    public List<Item> findAll(){
        return em.createQuery("select i from Item i", Item.class)
                .getResultList();
    };

    // 단일 아이템 조회
    public Item findOne(Long itemId){
        return em.find(Item.class, itemId);
    };

    // 대분류 카테고리 상품 조회
    public List<Item> findByCategoryId(List<Long> categoryIdList){
        if (categoryIdList == null || categoryIdList.isEmpty()) {
            return Collections.emptyList();
        }
        return em.createQuery("select i from Item i where i.categoryId in :categoryIdList", Item.class)
                .setParameter("categoryIdList", categoryIdList)
                .getResultList();
    }

    // 세부 카테고리 상품 조회
    public List<Item> findByCategoryId(Long categoryId){
        return em.createQuery("select i from Item where i.categoryId = :categoryId", Item.class)
                .setParameter("categoryId", categoryId)
                .getResultList();
    }

}
