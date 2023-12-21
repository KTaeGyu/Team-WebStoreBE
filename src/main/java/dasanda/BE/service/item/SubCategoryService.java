package dasanda.BE.service.item;

import dasanda.BE.domain.item.SubCategory;
import dasanda.BE.repository.item.SubCategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class SubCategoryService {

    private final SubCategoryRepository subCategoryRepository;

    public List<SubCategory> findSubCategories(){ return subCategoryRepository.findAll(); };
}
