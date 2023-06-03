package hello.springmvc.basic.response;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


// 응답
// 1. 정적 리소스
//    html, css, js를 정적 리소스를 전달함
// 2. 뷰 템플릿 사용
//    웹 드라우저에 동적인 html을 제공할 때는 뷰 템플릿을 사용한다.
// 3. HTTP 메시지 사용
//    HTTP API를 제공하는 경우네는 HTML이 아니라 데이터를 전달해야하니 HTTP메시지 바디에 JSON 같은 형식으로 데이터를 실어 보낸다.

// 정적 리소스
// 스프링 부트는 다음 클래스패스의 디렉토리에 있는 정적 리소스를 제공한다.
// `/static`, `/public`, `/resources`, `/META-INF/resources`

// `/src/main/resources`는 리소스를 보관하는 곳이고, 또 클래스패스의 시작 경로이다.
// 따라서 아래의 디렉토리 들에 리소스를 넣어두면 스프링 부트가 정적 리소스로 서비스를 제공한다.

// 정적 리소스 경로 서비스 예시
//   `src/main/resources/static`
//     다음 경로에 파일이 들어있으면
//    `src/main/resources/static/basic/hello-form.html`
//    웹 브라우저에서는 다음과 같이 실행하면 된다.
//    `http://localhost:8080/basic/hello-form.html`
//    정적 리소스는 해당 파일을 변경 없이, 추가적인 연산 없이 그래도 서비스 하는 것이다.

// 뷰 템플릿
// 뷰 템플릿을 거쳐서 HTML이 생성되고, 뷰가 응답을 만들어서 전달한다.
// 일반적으로 HTML을 동적으로 생성하는 용도로 사용하지만, 다른 것들도 가능하다. 뷰 템플릿이 만들 수 있는 것이라면 뭐든지 가능하다.

// 스프링 부트는 기본 뷰 템플릿 경로를 제공한다.
// `src/main/resources/templates`

// 뷰 템플릿 생성
// `src/main/resources/templates/response/hello.html`


@Controller
public class ResponseViewController {


  @RequestMapping("/response-view-v1")
  public ModelAndView ReponseViewV1() {
    ModelAndView mav = new ModelAndView("response/hello")
            .addObject("data", "hello!");

    return mav;
  }

  @RequestMapping("/response-view-v2")
  public String ReponseViewV2(Model model) {
    model.addAttribute("data", "hello!");
    return "response/hello"; // 뷰의 논리적 이름
  }

  @RequestMapping("/response/hello")
  public void ReponseViewV3(Model model) { // void인 경우에 url과 뷰의 이름(response/hello) 과 같으면 url을 참고해서 논리적 뷰를 실행시킨다. !!! 명시성이 떨어져서 추천하지 않는 방식임 v2를 추천함
    model.addAttribute("data", "hello!");
  }



}
