package dasanda.BE.service.item;

import dasanda.BE.domain.item.Brand;
import dasanda.BE.repository.item.BrandRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class BrandService {

    private final BrandRepository brandRepository;

    public List<Brand> findBrands() { return brandRepository.findAll(); };

}
