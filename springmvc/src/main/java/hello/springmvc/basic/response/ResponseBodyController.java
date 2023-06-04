package hello.springmvc.basic.response;

import hello.springmvc.basic.HelloData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
//@RestController 각 메소드에 @ResponseBody 을 붙이는 방식과 같다. (참고 46Line)
@Controller
public class ResponseBodyController {

  // 단순 텍스트 v1, v2, v3
  @GetMapping("/response-body-string-v1")
  public void responseBodyV1(HttpServletResponse response) throws IOException {
    response.getWriter().write("OK");
  }

  @GetMapping("/response-body-string-v2")
  public ResponseEntity<String> responseBodyV2(HttpServletResponse response) throws IOException {
    return new ResponseEntity<>("OK", HttpStatus.OK);
  }

  @ResponseBody
  @GetMapping("/response-body-string-v3")
  public String responseBodyV3() {
    return "OK";
  }

  // JSON 응답 v1, v2
  @GetMapping("/response-body-json-v1")
  public ResponseEntity<HelloData> responseBodyJsonV1() {
    HelloData helloData = new HelloData();
    helloData.setUsername("userA");
    helloData.setAge(20);

    return new ResponseEntity<>(helloData, HttpStatus.OK);
  }

  @ResponseStatus(HttpStatus.OK)
  @ResponseBody
  @GetMapping("/response-body-json-v2")
  public HelloData responseBodyJsonV2() {
    HelloData helloData = new HelloData();
    helloData.setUsername("userA");
    helloData.setAge(20);

    return helloData; // v1에서는 ReponseEntity객체에 HttpStatus 도 같이 넘겨줬는데 이 방식에서는 불가능하다. 그래서 위에 @ResponseStatus라는 애노테이션을 작성해야한다.
  }

}
