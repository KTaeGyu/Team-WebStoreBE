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

    @PostMapping("/api/items")
    public ResponseEntity<Object> saveItem(@RequestBody ItemCreationDto itemCreationDto) {
        try {
            Item item = Item.builder()
                    .name(itemCreationDto.getName())
                    .price(itemCreationDto.getPrice())
                    .brand(itemCreationDto.getBrand())
                    .category(itemCreationDto.getCategory())
                    .build();

            Item saveItem = itemService.add(item);

            ItemCreationDto creationDto = ItemCreationDto.builder()
                    .name(saveItem.getName())
                    .price(saveItem.getPrice())
                    .brand(saveItem.getBrand())
                    .category(saveItem.getCategory())
                    .build();

            return ResponseEntity.ok().body(creationDto);

        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(Map.of("message", "Item is already exist"));
        }

    };

    // 상품 전체 조회
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
    public ResponseEntity<Object> findItemByMajorCategoryId(@PathVariable("categoryId") Long categoryId){
        List<Item> itemList = itemService.findItemByMajorCategoryId(categoryId);
        return ResponseEntity.ok().body(itemList);
    }

    // 세부 카테고리 상품 조회
    @GetMapping("/api/items/category/sub/{categoryId}")
    public ResponseEntity<Object> findItemBySubCategoryId(@PathVariable("categoryId") Long categoryId){
        List<Item> itemList = itemService.findItemBySubCategoryId(categoryId);
        return ResponseEntity.ok().body(itemList);
    }

}
