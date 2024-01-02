package dasanda.BE.api.item;

import dasanda.BE.domain.item.MajorCategory;
import dasanda.BE.domain.item.SubCategory;
import dasanda.BE.service.item.MajorCategoryService;
import dasanda.BE.service.item.SubCategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CategoryApiController {

    private final MajorCategoryService majorCategoryService;
    private final SubCategoryService subCategoryService;

    @GetMapping("/api/categories/major")
    public ResponseEntity<Object> findMajorCategoryList(){
        List<MajorCategory> majorCategoryList = majorCategoryService.findMajorCategories();
        return ResponseEntity.ok().body(majorCategoryList);
    }

    @GetMapping("/api/categories/sub")
    public ResponseEntity<Object> findSubCategoryList(){
        List<SubCategory> subCategoryList = subCategoryService.findSubCategories();
        return ResponseEntity.ok().body(subCategoryList);
    }

    @GetMapping("/api/categories/sub/{majorId}")
    public ResponseEntity<Object> findSubCategoryListByMajorId(@PathVariable("majorId") Long majorId){
        List<SubCategory> subCategoryList = subCategoryService.findSubCategoriesByMajorId(majorId);
        return ResponseEntity.ok().body(subCategoryList);
    }

}
