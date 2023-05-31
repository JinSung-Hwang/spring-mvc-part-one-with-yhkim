package hello.springmvc.basic.MappingController;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/mapping/users")
public class MappingClassController {

  @GetMapping
  public String user() {
    return "get users";
  }

  @PostMapping
  public String addUser() {
    return "post users";
  }

  @GetMapping("/{userId}")
  public String findUser(@PathVariable String userId) {
    return "get users" + userId;
  }

  @PatchMapping("/{userId}")
  public String updateUser(@PathVariable String userId) {
    return "update users" + userId;
  }

  @DeleteMapping("/{userId}")
  public String deleteUser(@PathVariable String userId) {
    return "delete users" + userId;
  }

}
