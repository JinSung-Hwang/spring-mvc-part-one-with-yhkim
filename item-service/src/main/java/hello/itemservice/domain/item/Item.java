package hello.itemservice.domain.item;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;


// @Data
// domain 모델에서 @Data를 사용하는것은 위험하다 .
// {@code @Getter @Setter @RequiredArgsConstructor @ToString @EqualsAndHashCode}
// data에는 위의 어노테이션이 모두 포함되어있는데 이것들로인해서 예측하지 못하게 동작 할 수 있다. 그래서 @data를 쓰지 않는 것을 권장한다. 하지만 잘 알고 쓰면 문제되지 않는다.
// domain에서는 @Getter, @Setter만 사용하기를 권장한다. (나는 개인적으로 @Setter도 쓰는것을 좋아하지 않는다.)

@Getter
@Setter
public class Item {
  private Long id;
  private String itemName;
  private Integer price;
  private Integer quantity;

  public Item() {
  }

  public Item(String itemName, Integer price, Integer quantity) {
    this.itemName = itemName;
    this.price = price;
    this.quantity = quantity;
  }

}

