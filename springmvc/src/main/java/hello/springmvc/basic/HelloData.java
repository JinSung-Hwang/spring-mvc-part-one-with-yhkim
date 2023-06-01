package hello.springmvc.basic;

import lombok.Data;

@Data // @Getter, @Setter, @toString, @EqualsAndHashCode, @RequiredArgsConstructor 를 모두 다만들어준다.
public class HelloData {
  private String username;
  private int age;
}
