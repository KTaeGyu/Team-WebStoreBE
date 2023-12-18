package dasanda.BE.service.item;

import dasanda.BE.domain.Item;
import dasanda.BE.repository.item.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;
    // 아이템 추가
    public Item add(Item item){
      itemRepository.save(item);
      return item;
    };

    // 전체 아이템 조회
    public List<Item> findItems() {
        return itemRepository.findAll();
    };

    // 단일 아이템 조회
    public Item findItemById(Long itemId) {
        return itemRepository.findOne(itemId);
    };

}
