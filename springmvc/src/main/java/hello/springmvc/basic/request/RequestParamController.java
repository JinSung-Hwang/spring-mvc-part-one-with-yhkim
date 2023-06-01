package hello.springmvc.basic.request;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@Slf4j
@Controller
public class RequestParamController {

  // 클라이언트에서 서버로 요청 데이터를 전달할 때는 주로 다음 3가지 방법을 사용한다.

  // 1. GET - 쿼리 파라미터
  // /url?username=hello&age=20
  // 메시지 바디 없이, URL의 쿼리파라미터에 데이터를 포함해서 전달
  // 예) 검색, 필터, 페이징등에서 많이 사용하는 방식

  // 2. POST - HTML Form
  // 해더가 content-type: application/x-www.form-urlencoded 로 설정되고
  // 메세지 바디 형식이 쿼리 파라미터형식과 동일하지만 ?는 없는 형태로 전달 된다. 예) username=hello&age=20
  // 예) 회원 가입, 상품 주문, HTML Form 사용

  // 3. HTTP message body에 데이터를 직접 담아서 요청
  // HTTP API에서 주로 사용, JSON, XML, TEXT
  // 데이터는 주로 JSON사용
  // POST, PUT, PATCH method에서도 마찬가지로 사용가능

  // ------------------------------------

  // 1번의 GET 쿼리 파라미터 전송 방식이든, 2번의 POST HTML Form 전송 방식이든 둘다 형식이 같으므로 구분없이 조회할 수 있다.
  // 이것을 간단히 "요청 파라미터(request parameter)조회" 라고 한다.

  // 이제 스프링으로 요청 파라미터를 조회하는 방법을 단계적으로 알아보자.

  @RequestMapping("/request-param-v1")
  public void requestParamV1(HttpServletRequest request, HttpServletResponse response) throws IOException {
    String username = request.getParameter("username");
    int age = Integer.parseInt(request.getParameter("age"));
    log.info("v1 username={}, age={}", username, age);

    response.getWriter().write("OK");
  }

  @ResponseBody // 원래 String을 반환하면 view를 찾는데 이 어노테이션은 바로 문자를 body에 넣어서 반환한다. @RestController와 같은 효과
  @RequestMapping("/request-param-v2")
  public String requestParamV2(
          @RequestParam("username") String memberName,
          @RequestParam("age") int memberAge
  ) {
    log.info("v2 username={}, age={}", memberName, memberAge);

    return "OK";
  }

  @ResponseBody
  @RequestMapping("/request-param-v3")
  public String requestParamV3(
          @RequestParam String username, // 애노테이션 안에 내용을 생략할 수 있다. 단 변수명이랑 queryString, paramPath 변수명이랑 같아야한다.
          @RequestParam int age
  ) {
    log.info("v3 username={}, age={}", username, age);

    return "OK";
  }

  @ResponseBody
  @RequestMapping("/request-param-v4")
  public String requestParamV4(
          String username, // String, int, Integer등 단순 타입이면 애노테이션 자체도 생략할 수 있다. 단 변수명이랑 queryString, paramPath 변수명이랑 같아야한다.
          int age
  ) {
    log.info("v4 username={}, age={}", username, age);

    return "OK";
  }

  @ResponseBody
  @RequestMapping("/request-param-required")
  public String requestParamRequired(
          @RequestParam(required = true) String username, // required는 값이 필수인지 아닌지를 체크하는 옵션, required가 true가 기본값이다. 없으면 400 error발생함
          @RequestParam(required = false) Integer age // int는 null이 들어갈 수 없다. 따라서 Integer를 사용해야 required=false를 사용 할 수 있다.
  ) {
    log.info("required username={}, age={}", username, age);

    return "OK";
  }

  @ResponseBody
  @RequestMapping("/request-param-default")
  public String requestParamDefault(
          @RequestParam(required = true, defaultValue = "guest") String username, // "" 빈문자열도 "guest"라는 defaultValue가 들어온다.
          @RequestParam(required = false, defaultValue = "-1") int age // defaultValue가 있기때문에 int를 사용할 수 있다. 결국 defaultValue가 있으면 required는 사용할 필요가 없어진다.
  ) {
    log.info("default username={}, age={}", username, age);

    return "OK";
  }

  @ResponseBody
  @RequestMapping("/request-param-map")
  public String requestParamMap(@RequestParam Map<String, Object> paramMap) {
    log.info("map username={}, age={}", paramMap.get("username"), paramMap.get("age"));

    return "OK";
  }

}
