package dasanda.BE.api.item;

import dasanda.BE.domain.item.Brand;
import dasanda.BE.service.item.BrandService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class BrandApiController {

    private final BrandService brandService;

    @GetMapping("/api/brands/")
    public ResponseEntity<Object> findBrandList(){
        List<Brand> brandList = brandService.findBrands();
        return ResponseEntity.ok().body(brandList);
    }

}
