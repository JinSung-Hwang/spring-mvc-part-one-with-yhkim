package hello.itemservice.domain.item;


import static org.assertj.core.api.Assertions.*;

import java.util.List;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;

class ItemRepositoryTest {

  static ItemRepository itemRepository = new ItemRepository();

  @AfterAll
  static void afterAll() {
    itemRepository.clareStore();
  }

  @Test
  void save() {
    // given
    Item item = new Item("향수", 10000, 10);

    // when
    Item savedItem = itemRepository.save(item);
    Item foundItem = itemRepository.findById(item.getId());

    // then
    assertThat(foundItem).isEqualTo(savedItem);
  }

  @Test
  void findAll() {
    // given
    Item item1 = new Item("향수1", 10000, 10);
    Item item2 = new Item("향수2", 20000, 20);

    itemRepository.save(item1);
    itemRepository.save(item2);

    // when
    List<Item> items = itemRepository.findAll();

    // then
    assertThat(items.size()).isEqualTo(2);
    assertThat(items).contains(item1, item2);
  }

  @Test
  void updateItem() {
    // given
    Item item = new Item("향수", 10000, 10);
    Long itemId = item.getId();

    itemRepository.save(item);
    // when
    Item updateParam = new Item("향수2", 20000, 20);
    itemRepository.updateItem(itemId, updateParam);

    Item founedItem = itemRepository.findById(itemId);
    // then
    assertThat(founedItem.getItemName()).isEqualTo(updateParam.getItemName());
    assertThat(founedItem.getPrice()).isEqualTo(updateParam.getPrice());
    assertThat(founedItem.getQuantity()).isEqualTo(updateParam.getPrice());
  }

}