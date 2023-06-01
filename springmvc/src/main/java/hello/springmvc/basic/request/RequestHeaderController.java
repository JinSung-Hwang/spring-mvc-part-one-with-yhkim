package hello.springmvc.basic.request;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpMethod;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;

@Slf4j
@RestController
public class RequestHeaderController {

  @RequestMapping("/headers")
  public String headers(
          HttpServletRequest request,
          HttpServletResponse response,
          HttpMethod httpMethod,
          Locale locale, // 가장 우선순위가 높은 locale이 지정됨
          @RequestHeader MultiValueMap<String, String> headerMap, // 모든 해더가 다 들어가 있음 // MultiValueMap은 같은 key값에 여러 value들이 들어 갈 수 있다. 같은 키값으로 들어가있으면 배열로 리턴함
          @RequestHeader("host") String host, // 특정 해더만 가져올 수 있음
          @CookieValue(value = "myCookie", required = false, defaultValue = "userA") String cookie
          ) {

    log.info("request={}", request);
    log.info("response={}", response);
    log.info("httpMethod={}", httpMethod);
    log.info("locale={}", locale);
    log.info("headerMap={}", headerMap);
    log.info("header host={}", host);
    log.info("myCookie={}", cookie);

    return "OK";
  }

}
