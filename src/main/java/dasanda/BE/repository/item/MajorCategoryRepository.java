package dasanda.BE.repository.item;

import dasanda.BE.domain.item.MajorCategory;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class MajorCategoryRepository {

    private final EntityManager em;

    public List<MajorCategory> findAll(){
        return em.createQuery("select mc form MajorCategory mc", MajorCategory.class)
                .getResultList();
    }

    public MajorCategory findOne(Long majorCategoryId){
        return em.find(MajorCategory.class, majorCategoryId);
    }

}
