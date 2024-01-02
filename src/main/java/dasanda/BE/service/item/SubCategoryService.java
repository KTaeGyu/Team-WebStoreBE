package dasanda.BE.service.item;

import dasanda.BE.domain.item.MajorCategory;
import dasanda.BE.domain.item.SubCategory;
import dasanda.BE.repository.item.MajorCategoryRepository;
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
    private  final MajorCategoryRepository majorCategoryRepository;

    public List<SubCategory> findSubCategories(){ return subCategoryRepository.findAll(); };

    public List<SubCategory> findSubCategoriesByMajorId(Long majorId){
        MajorCategory majorCategory = majorCategoryRepository.findOne(majorId);
        return subCategoryRepository.findByMajorCategory(majorCategory);
    }
}
