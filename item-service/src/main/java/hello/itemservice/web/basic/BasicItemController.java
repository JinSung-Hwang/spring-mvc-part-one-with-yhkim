package hello.itemservice.web.basic;

import hello.itemservice.domain.item.Item;
import hello.itemservice.domain.item.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.PostConstruct;
import java.util.List;

@Controller
@RequestMapping("/basic/items")
@RequiredArgsConstructor
public class BasicItemController {

  private final ItemRepository itemRepository;

//  @Autowired // 스프링에 생성자가 하나 있으면 @Autowired를 생략할 수 있다.
//  public BasicItemController(ItemRepository itemRepository) { // RequiredArgsConstructor를 controller에 만들면 생성자를 사용하지 않아도 된다.
//    this.itemRepository = itemRepository;
//  }

  @GetMapping
  public String items(Model model) {
    List<Item> items = itemRepository.findAll();
    model.addAttribute("items", items);
    return "basic/items";
  }

  @PostConstruct
  public void init() {
    itemRepository.save(new Item("itemA", 100000, 10));
    itemRepository.save(new Item("itemB", 200000, 20));
  }



}
