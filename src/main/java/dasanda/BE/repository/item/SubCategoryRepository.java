package dasanda.BE.repository.item;

import dasanda.BE.domain.item.MajorCategory;
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

    public SubCategory findOne(Long categoryId){ return em.find(SubCategory.class, categoryId); }

    public List<SubCategory> findByMajorCategory(MajorCategory majorCategory){
        return em.createQuery("select s from SubCategory s where s.majorCategory = :majorCategory", SubCategory.class)
                .setParameter("majorCategory", majorCategory)
                .getResultList();
    }

}
