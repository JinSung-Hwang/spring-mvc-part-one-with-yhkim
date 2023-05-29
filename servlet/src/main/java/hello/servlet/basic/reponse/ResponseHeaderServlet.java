package hello.servlet.basic.reponse;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;


// 응답 해더 설정 예제
@WebServlet(name = "responseHeaderServlet", urlPatterns = "/response-header")
public class ResponseHeaderServlet extends HttpServlet {

  @Override
  protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    response.setStatus(HttpServletResponse.SC_OK); // http status code 값을 이미 스프링에서 정의해둠

    response.setHeader("Content-Type", "text/plain;charset=utf-8");
    response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
    response.setHeader("Pragma", "no-cache");
    response.setHeader("my-header", "hello");

    // http reponse header 편의 메서드
    // 위에는 수동적으로 속성명까지 적는 방식이고
    // content메서드 안에서는 setContentType으로 속성명까지 메서드 명으로 설정되어있다. 오탈자를 줄일수 있을거같다.
//    content(response);
//    cookie(response);
    redirect(response);
    

    PrintWriter writer = response.getWriter();
    writer.println("OK"); // 응답 body에 단순 텍스트 보내는 방식
  }

  private void redirect(HttpServletResponse response) throws IOException {
    // Status Code: 302
    // Location: /basic/hello-form.html

//    response.setStatus(HttpServletResponse.SC_FOUND); // 302
//    response.setHeader("Location", "/basic/hello-form.html"); // 이렇게 직접 설정할 수 있지만
    response.sendRedirect("/basic/hello-form.html"); // 이렇게 편의 메서드를 사용하여 설정할 수 있음
  }

  private void cookie(HttpServletResponse response) {
    // Set-Cookie: myCookie=good; Max-Age=600;
    // response.setHeader("Set-Cookie", "myCookie=good; Max-Age=600"); // 여기처럼 직접 쿠키해더를 설정할 수 있지만 
    Cookie cookie = new Cookie("myCookie", "good"); // 여기처럼 편의로 만들어진 쿠키객체를 통해서 쿠키 해더를 설정할 수 있다.
    cookie.setMaxAge(600);
    response.addCookie(cookie);
  }

  private void content(HttpServletResponse response) {
    response.setContentType("text/plain");
    response.setCharacterEncoding("utf-8");
  }
}
