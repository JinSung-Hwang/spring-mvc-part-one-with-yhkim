package hello.springmvc.basic.request;

import com.fasterxml.jackson.databind.ObjectMapper;
import hello.springmvc.basic.HelloData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * input
 *       {"username": "hello", "age": 20}
 *       content-type: application/json
 */
@Slf4j
@Controller
public class RequestBodyJsonController {

  private ObjectMapper objectMapper = new ObjectMapper();

  @PostMapping("/request-body-json-v1")
  public void requestBodyJsonV1(HttpServletRequest request, HttpServletResponse response) throws IOException {
    ServletInputStream inputStream = request.getInputStream();
    String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);

    log.info("messageBody ={}", messageBody);
    HelloData helloData = objectMapper.readValue(messageBody, HelloData.class);
    log.info("username={}, age={}", helloData.getUsername(), helloData.getAge());

    response.getWriter().write("OK");
  }

  @ResponseBody
  @PostMapping("/request-body-json-v2")
  public String requestBodyJsonV2(HttpServletRequest request, HttpServletResponse response) throws IOException {
    ServletInputStream inputStream = request.getInputStream();
    String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);

    log.info("messageBody ={}", messageBody);
    HelloData helloData = objectMapper.readValue(messageBody, HelloData.class);
    log.info("username={}, age={}", helloData.getUsername(), helloData.getAge());

    return "OK";
  }


  @ResponseBody
  @PostMapping("/request-body-json-v3")
  public String requestBodyJsonV3(@RequestBody HelloData helloData) throws IOException { // @RequestBody에 직접 만든 객체를 지정할 수 있다. objectMapper를 사용하지 않아도 된다.
    // @RequestBody를 사용하면 HttpMessageConverter가 MappingJacson2HttpMessageConverter를 호출해서 ObjectMapper가 할일을 대신해주고 파라미터에 원하는 객체를 넣어준다.
    // @RequestBody는 생략하면 @ModelAttribute가 되기때문에 생략할 수 없다.
    // @RequestBody는 객체의 String 변수에 값이 안들어왔으면 ""(빈문자열)을 넣어주고 int 변수에 값이 안들어왔으면 0을 넣어준다.

    log.info("username={}, age={}", helloData.getUsername(), helloData.getAge());

    return "OK";
  }

  @ResponseBody
  @PostMapping("/request-body-json-v4")
  public String requestBodyJsonV4(HttpEntity<HelloData> httpEntity) throws IOException { // HttpEntity에 제네릭타입으로 HelloData를 넣어기때문에 httpEntity.getBody하면 바로 객체가 나온다.
    HelloData data = httpEntity.getBody();
    log.info("username={}, age={}", data.getUsername(), data.getAge());

    return "OK";
  }

  @ResponseBody // @ReponseBody를 사용하면 해당 객체를 HTTP 메시지 바디에 직접 넣어줄 수 있다. 물론 이경우에도 HttpEntity를 사용해도 된다.
  @PostMapping("/request-body-json-v5")
  public HelloData requestBodyJsonV5(@RequestBody HelloData helloData) throws IOException {

    log.info("username={}, age={}", helloData.getUsername(), helloData.getAge());

    return helloData;

    // @RequestBody 요청
    // JSON요청 -> HTTP 메시지 컨버터 -> 객체

    // @ReponseBody 응답
    // 객체 -> HTTP 메시지 컨버터 -> JSON 응답


  }


}
