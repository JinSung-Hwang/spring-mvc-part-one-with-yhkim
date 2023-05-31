package hello.springmvc.basic.MappingController;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
public class MappingController {

  private Logger log = LoggerFactory.getLogger(getClass());

  @RequestMapping(value = {"/hello-basic", "/hello-go"}, method = RequestMethod.GET) // 배열 형식으로 여러개의 EndPoint지정 가능
  public String helloBasicV1() {
    log.info("helloBasic");
    return "OK";
  }

  // 편리한 축약 애노테이션
  // @GetMapping, @PostMapping, @PutMapping
  @GetMapping(value = "/mapping-get-v2")
  public String helloBasicV2() {
    log.info("helloBasic");
    return "OK";
  }

  // PathVariable 사용 (path parameter 받기)
  // 변수명이 같으면 생략 가능
  @GetMapping("/mapping/{userId}")
  public String mappingPath(@PathVariable("userId") String userId) { // 애노테이션안에 userId는 {url의 pathParameter}와 변수명이 같다면 생략가능, 단 애노테이션까지 생략은 불가능
    log.info("mappingPath userId ={}", userId);
    return "OK";
  }

  // 다중 PathVariable 사용 (다중 path parameter 받기)
  // 변수명이 같으면 생략 가능
  @GetMapping("/mapping/{userId}/orders/{orderId}")
  public String mappingPath(@PathVariable("userId") String userId, @PathVariable("orderId") Long orderId) {
    log.info("mappingPath userId ={}, orderId={}", userId, orderId);
    return "OK";
  }

  @GetMapping(value = "/mapping-params", params = "mode=debug") // queryString에 mode=debug가 있어야 실행됨 없으면 404에러 반환
  public String mappingParam() {
    log.info("mappingParam");
    return "OK";
  }

  // 특정 헤더로 추가 매핑
  @GetMapping(value = "/mapping-header", headers = "mode=debug") // header에 mode=debug가 API가 실행됨 없으면 404에러 반환
  public String mappingHeader() {
    log.info("mappingHeader");
    return "OK";
  }

//  @PostMapping(value = "/mapping-consume", consumes = MediaType.APPLICATION_JSON_VALUE)
  @PostMapping(value = "/mapping-consume", consumes = "application/json") // comsumes은 서버에서 받을 타입을 명시함
  public String mappingConsumes() {
    log.info("mappingConsumes");
    return "OK";
  }

  @PostMapping(value = "/mapping-consume", produces = "application/json") // produce는 클라이언트에서 받을 타입을 명시함
  public String mappingProduces() {
    log.info("mappingProduces");
    return "OK";
  }

}
