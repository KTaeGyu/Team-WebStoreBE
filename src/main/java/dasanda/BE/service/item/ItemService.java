package dasanda.BE.service.item;

import dasanda.BE.domain.item.Item;
import dasanda.BE.repository.item.ItemRepository;
import dasanda.BE.repository.item.SubCategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;
    private final SubCategoryRepository subCategoryRepository;

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

    // 대분류 카테고리 상품 조회
    public List<Item> findItemByMajorCategoryId(Long categoryId){
        List<Long> categoryIdList = subCategoryRepository.findByMajorId(categoryId);
        return itemRepository.findByCategoryId(categoryIdList);
    }

    // 세부 카테고리 상품 조회
    public List<Item> findItemBySubCategoryId(Long categoryId){
        return itemRepository.findByCategoryId(categoryId);
    }

}
