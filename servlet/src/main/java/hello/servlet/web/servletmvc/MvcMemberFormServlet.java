package hello.servlet.web.servletmvc;

import com.sun.net.httpserver.HttpsServer;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

// WEB-INF/views 밑에 있는 파일은 컨트롤러 통해서만 호출할 수 있다. 이것은 springMVC의 규칙으로 정의된것이다.

@WebServlet(name = "mvcMemberFormServlet", urlPatterns = "/servlet-mvc/members/new-form")
public class MvcMemberFormServlet extends HttpServlet {

  @Override
  protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    System.out.println("@@@@@@22");
    String viewPath = "/WEB-INF/views/new-form.jsp";
    RequestDispatcher dispatcher = request.getRequestDispatcher(viewPath);
    dispatcher.forward(request, response); // 다른 서블릿이나 JSP로 이동할 수 있는 기능이다. 서버가 viewPath를 위치를 다시 호출한다. (client로 이동하지 않음)
  }
}
