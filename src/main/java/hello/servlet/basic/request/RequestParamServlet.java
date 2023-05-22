package hello.servlet.basic.request;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

// servlet에서 get방식이며 쿼리파라미터로 데이터 받기 또는 html form의 post방식으로 받기 모두 getParamterValues형식으로 받을 수 있다.
/**
 * 1. 파라미터 전송 기능
 * http://localhost:8080/request-param?username=hello&age=20
 */
@WebServlet(name = "requestParamServlet", urlPatterns = "/request-param")
public class RequestParamServlet extends HttpServlet {

  // reqeust.getParameter는 post의 form형태이든, get의 쿼리스트링이든 상관없이 parameter를 받을 수 있다.
  // client입장에서는 get으로 보내는것과 post로 보내는것에 차이가 있지만
  // server입장에서는 content-type: application/x-www-form-urlencoded의 형식이든 get의 queryString이든 차이가 없다.
  // content-type은 http body형식을 정하는 것이다.

  @Override
  protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    System.out.println("전체 파라미터 조회 - start");
    request.getParameterNames().asIterator().forEachRemaining(paramName -> System.out.println("paramName" + paramName + "=" + request.getParameter(paramName)));
    System.out.println("전체 파라미터 조회 - ent");
    System.out.println();

    System.out.println("단일 파라미터 조회 - start");
    String userName = request.getParameter("username");
    String age = request.getParameter("age");
    System.out.println("username =" + userName);
    System.out.println("age =" + age);
    System.out.println("단일 파라미터 조회 - end");
    System.out.println();

    System.out.println("이름이 같은 복수 파라미터 조회");
    String[] usernames = request.getParameterValues("username");
    System.out.println("string[] usernames" + usernames);
    for (String name : usernames) {
      System.out.println("username" + name);
    }

    response.getWriter().write("OK");

  }
}
