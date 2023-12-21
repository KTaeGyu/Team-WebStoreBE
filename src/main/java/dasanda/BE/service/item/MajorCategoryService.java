package dasanda.BE.service.item;

import dasanda.BE.domain.item.MajorCategory;
import dasanda.BE.repository.item.MajorCategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class MajorCategoryService {

    private final MajorCategoryRepository majorCategoryRepository;

    public List<MajorCategory> findMajorCategories() { return majorCategoryRepository.findAll(); };

}
