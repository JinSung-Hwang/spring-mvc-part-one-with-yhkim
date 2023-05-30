package hello.springmvc.basic;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// logger 사용시 장점
// 쓰레드 정보, 클래스 이름 등 부가 정보 출력가능
// 로그 레벨에 따라 로그 출력 가능, staging별 log출력가능
// 콘솔뿐만 아니라 파일이나 네트워크 등 로그를 별도에 위치에 남길 수 있다.
//    파일로 남길때는 일별, 특정 용량에 따라 로그 분리 가능
//    성능도 System.out보다 좋다. 100배 차이남


@Slf4j // 13번 또는 14번 라인을 대신 작성해준다. 메서드에서 바로 log 인스턴스 사용 가능
@RestController // @Controller의 반환값이 String이면 뷰를 찾고 뷰가 렌더링 되는데 @RestController는 반환값이 아니라 Http 메시지 바디에 바로 입력한다.
public class LogTestController {

//  private final Logger log = LoggerFactory.getLogger(getClass()); // 14번 라인처럼 쓸 수도 있음
//  private final Logger log = LoggerFactory.getLogger(LogTestController.class);

  @RequestMapping("/log-test")
  public String logTest() {
    String name = "String";

    System.out.println("name = " + name); // println 안에있는 + 연산 기호를 모두 수행한 후에 출력을 진행함
//  log.trace("trace log= " + name); // 이렇게 + 연산기호를 사용하면 안됨 ={} 기호를 사용해야한다.
//  properties 세팅으로 출력을 안할지라도 +기호가 있으면 + 연산을 모두 실행하기떄문에 비효율적이다.


    log.trace("info log={}", name); // 로컬 단계에서 모든 로그를 찍는 레벨
    log.debug("info log={}", name); // 개발 단계에서 찍는 로그
    log.info("info log={}", name); // 운영 레벨, 비지니스적으로 필요한 레벨로 찍는 로그
    // 출력::::  `2023-05-31 01:54:42.294  INFO 63114 --- [nio-8080-exec-1] hello.springmvc.basic.LogTestController  : info log=String`
    // 설명::::   시간,                 로그레벨, 프로세스 번호,               클래스 이름
    // 설명2:::  application.properties에서 log level에 따라서 출력을 할지 말지 정할 수 있음
    log.warn("info log={}", name); // 경고, 위험 표시 로그
    log.error("info log={}", name); // 에러 로그

    return "OK";
  }

}
