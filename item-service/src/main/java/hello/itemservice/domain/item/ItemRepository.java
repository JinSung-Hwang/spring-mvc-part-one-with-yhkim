package hello.itemservice.domain.item;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.springframework.stereotype.Repository;

@Repository
public class ItemRepository {

  private static final Map<Long, Item> store = new ConcurrentHashMap<>(); // 동시성을 위한 concurrentHashMap, 실무에서 그냥 hashMap을 사용하면 안된다.
  private static Long sequence = 0L;

  // save
  public Item save(Item item) {
    item.setId(++sequence);
    store.put(item.getId(), item);
    return item;
  }

  // find
  public Item findById(Long itemId) {
    return store.get(itemId);
  }

  // findAll
  public List<Item> findAll() {
    return new ArrayList(store.values());
  }

  // update
  public void updateItem(Long itemId, Item updateItem) {
    Item foundItem = store.get(itemId);
    foundItem.setItemName(updateItem.getItemName());
    foundItem.setPrice(updateItem.getPrice());
    foundItem.setQuantity(updateItem.getQuantity());
  }

  // clear
  public void clareStore() {
    store.clear();
  }

}
