package dasanda.BE.api.item;

import dasanda.BE.domain.item.Item;
import dasanda.BE.dto.item.ItemCreationDto;
import dasanda.BE.service.item.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class ItemApiController {

    private final ItemService itemService;

    // 할인 상품 목록 조회
    @GetMapping("/api/items")
    public ResponseEntity<Object> findItemList(){
        List<Item> itemList = itemService.findItems();
        return ResponseEntity.ok().body(itemList);
    }

    // 단일 상품 조회
    @GetMapping("/api/items/{itemId}")
    public ResponseEntity<Object> findItem(@PathVariable("itemId") Long itemId){
        Item findItem = itemService.findItemById(itemId);
        return ResponseEntity.ok().body(findItem);
    }

    // 대분류 카테고리 상품 조회
    @GetMapping("/api/items/category/major/{categoryId}")
    public ResponseEntity<List<Item>> findItemByMajorCategoryId(@PathVariable("categoryId") Long categoryId){
        List<Item> itemList = itemService.findItemByMajorCategoryId(categoryId);
        return ResponseEntity.ok().body(itemList);
    }

    // 세부 카테고리 상품 조회
    @GetMapping("/api/items/category/sub/{categoryId}")
    public ResponseEntity<List<Item>> findItemBySubCategoryId(@PathVariable("categoryId") Long categoryId){
        List<Item> itemList = itemService.findItemBySubCategoryId(categoryId);
        return ResponseEntity.ok().body(itemList);
    }

}
