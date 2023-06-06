package hello.itemservice.web.basic;

import hello.itemservice.domain.item.Item;
import hello.itemservice.domain.item.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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

  @GetMapping("/{itemId}")
  public String item(@PathVariable long itemId, Model model) {
    Item item = itemRepository.findById(itemId);
    model.addAttribute("item", item);
    return "basic/item";
  }

  @GetMapping("/add")
  public String addForm() {
    return "basic/addForm";
  }

//  @PostMapping("/add") // GetMapping의 /add 경로에는 화면을 보여주고 PostMapping의 /add 경로에서는 실제 save API가 호출되도록함
  public String saveV1(
          @RequestParam String itemName,
          @RequestParam int price,
          @RequestParam Integer quantity,
          Model model) {

    Item item = new Item();
    item.setItemName(itemName);
    item.setPrice(price);
    item.setQuantity(quantity);

    itemRepository.save(item);

    model.addAttribute("item", item);

    return "basic/item";
  }

//  @ModelAttribute가 하는 일 2가지
//  1. 요청 파라미터 처리
//      @ModelAttribute는 Item객체를 생성하고, 요청 파라미터의 값을 프로퍼티 접근법(setXXX)으로 입력해준다.
//  2. Model 추가
//      바로 Model에 @ModelAttribute로 지정한 객체를 자동으로 넣어준다. 72번 라인 참고
//  @PostMapping("/add")
  public String saveV2(@ModelAttribute("Item") Item item) {

    itemRepository.save(item);
//    model.addAttribute("item", item); // 자동 추가, 생략 가능

    return "basic/item";
  }

//  @PostMapping("/add")
  public String saveV3(@ModelAttribute Item item) {

    itemRepository.save(item);
//    model.addAttribute("item", item); // @ModelAttribute에 값을 안넣으면 @ModelAttribute 다음에 작성하는 객체의 첫글자를 소문자로 바꿔서 model.addAttribute에 넣어준다.

    return "basic/item";
  }

//  @PostMapping("/add")
  public String saveV4(Item item) { // @ModelAttribute 생략 가능

    itemRepository.save(item);
//    model.addAttribute("item", item);

    return "basic/item"; // 브라우저에서 상품 등록후 새로고침하면 계속해서 상품이 생성되는 문제가 있었다.
    // 새로고침하면 이전에 했던 행동을 반복해주기 때문이다.
  }

//  @PostMapping("/add")
  public String saveV5(Item item) { // @ModelAttribute 생략 가능

    itemRepository.save(item);

    return "redirect:/basic/items/" + item.getId();
    // v4의 문제를 해결하기 위해서 Post Redirect Get - PRG 패턴을 이용한다.
  }

  @PostMapping("/add")
  public String saveV6(Item item, RedirectAttributes redirectAttributes) { // @ModelAttribute 생략 가능
    Item savedItem = itemRepository.save(item);
    redirectAttributes.addAttribute("itemId", savedItem.getId()); // <---- redirect에 치환될 파라미터랑 동일하게 넣으면 치환되어서 값이 들어간다.
    redirectAttributes.addAttribute("status", true); // <---- 만약 redirect에 치환될 파라미터가 없으면 queryString으로 넘어간다.
    return "redirect:/basic/items/{itemId}"; // 기존 v5의 + item.getId(); 방식의 url표현방식은 encoding 문제가 있을수 있어서 위험하다. 하여 redirectAttributes.addAttribute 방식을 이용해야한다.
  }

  @GetMapping("/{itemId}/edit")
  public String editForm(@PathVariable Long itemId, Model model) {
    Item item = itemRepository.findById(itemId);
    model.addAttribute("item", item);
    return "basic/editForm";
  }

  @PostMapping("/{itemId}/edit")
  public String edit(@PathVariable Long itemId, @ModelAttribute Item item) {
    itemRepository.updateItem(itemId, item);
    return "redirect:/basic/items/{itemId}"; // 리다이렉트 시킴
  }

  @PostConstruct
  public void init() {
    itemRepository.save(new Item("itemA", 100000, 10));
    itemRepository.save(new Item("itemB", 200000, 20));
  }

}
