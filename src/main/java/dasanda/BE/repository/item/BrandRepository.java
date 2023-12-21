package dasanda.BE.repository.item;

import dasanda.BE.domain.item.Brand;
import dasanda.BE.domain.item.Item;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class BrandRepository {

    private final EntityManager em;

    public List<Brand> findAll(){
        return em.createQuery("select b from Brand b", Brand.class)
                .getResultList();
    };

}
