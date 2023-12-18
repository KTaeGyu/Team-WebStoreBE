package dasanda.BE.api;

import dasanda.BE.domain.Item;
import dasanda.BE.form.item.ItemCreationForm;
import dasanda.BE.service.item.ItemService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class ItemApiController {

    private final ItemService itemService;

    @PostMapping("/api/items")
    public ResponseEntity<Object> saveItem(@RequestBody @Valid ItemCreationForm form) {
        try {
            Item item = Item.builder()
                    .name(form.getName())
                    .brand(form.getBrand())
                    .price(form.getPrice())
                    .build();

            Item saveItem = itemService.add(item);

            return ResponseEntity.ok().body(saveItem);

        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(Map.of("message", "Item is already exist"));
        }

    };

    @GetMapping("/api/items")
    public ResponseEntity<Object> findItemList(){
        List<Item> itemList = itemService.findItems();
        return ResponseEntity.ok().body(itemList);
    }

    @GetMapping("/api/items/{itemId}")
    public ResponseEntity<Object> findItem(Long ItemId){
        Item findItem = itemService.findItemById(ItemId);
        return ResponseEntity.ok().body(findItem);
    }
}
