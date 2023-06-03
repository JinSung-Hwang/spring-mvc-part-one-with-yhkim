package hello.springmvc.basic.request;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.Writer;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;


// HTTP 요청 메시지 - 단순 텍스트를 받는 방식
@Slf4j
@Controller
public class RequestBodySpringController {

  // HTTP 요청 메시지는 httpServletRquest의 InputStream을 통해서 데이터를 받을 수 있다.
  @PostMapping("/request-body-string-v1")
  public void requestBodyString(HttpServletRequest request, HttpServletResponse response) throws IOException {
    ServletInputStream inputStream = request.getInputStream();
    String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);

    log.info("messgageBody={}", messageBody);

    response.getWriter().write(("OK"));
  }

  // 서블릿 req, res 객체가 아니라 InputStream(Reader), OutputStream(Writer) 를 직접 받을 수 있다.
  @PostMapping("/request-body-string-v2")
  public void requestBodyStringV2(InputStream inputStream, Writer responseWriter) throws IOException {
    String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);

    log.info("messgageBody={}", messageBody);

    responseWriter.write(("OK"));
  }


  // HttpEntity: Http Header, body정보를 편리하게 조회
  // HttpEntity를 상속받은 HttpRequest와 HttpReponse를 따로 사용할 수 있고 각 상속 받은 클래스는 더 많은 기능을 지원함
  @PostMapping("/request-body-string-v3")
  public HttpEntity<String> requestBodyStringV3(
          HttpEntity<String> httpEntity
//          RequestEntity<String> requestEntity,
//          RequestEntity<String> responseEntity
  ) throws IOException { //

    String body = httpEntity.getBody();
//    httpEntity.getHeaders() 해더 정보도 편하게 조회 가능

    log.info("messgageBody={}", body);

//    return new HttpEntity<>("OK"):
    return new ResponseEntity<>("OK", HttpStatus.CREATED);
  }

  @ResponseBody // view를 반환하지 않고 반환되는 값을 stream을 통해 write해준다.
  @PostMapping("/request-body-string-v4")
  public String requestBodyStringV4(
          @RequestBody String messageBody
          // HttpEntity를 받지 않고 바로 @RequestBody 어노테이션을 통해서 body를 받을 수 있다.
          // @RequestBody는 기존에 서블릿 객체를 받고 stream으로 디코딩과정을 모두 처리해준다.
          // @RequestBody는 QueryString과 PathParam과는 전혀 관계가 없다.
  ) throws IOException {
    log.info("messgageBody={}", messageBody);

    return "OK";
  }



}
