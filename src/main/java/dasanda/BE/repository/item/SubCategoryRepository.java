package dasanda.BE.repository.item;

import dasanda.BE.domain.item.SubCategory;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class SubCategoryRepository {

    private final EntityManager em;

    public List<SubCategory> findAll(){
        return em.createQuery("select sc form SubCategory sc", SubCategory.class)
                .getResultList();
    }

    public List<Long> findByMajorId(Long categoryId){
        return em.createQuery("select s.id form SubCategory s where s.majorId = :categoryId;", Long.class)
                .setParameter("categoryId", categoryId)
                .getResultList();
    }

}
