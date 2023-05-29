package hello.servlet.web.frontcontroller.v3;

import hello.servlet.web.frontcontroller.ModelView;
import hello.servlet.web.frontcontroller.MyView;
import hello.servlet.web.frontcontroller.v3.controller.MemberFormControllerV3;
import hello.servlet.web.frontcontroller.v3.controller.MemberListControllerV3;
import hello.servlet.web.frontcontroller.v3.controller.MemberSaveControllerV3;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "frontControllerServletV3", urlPatterns = "/front-controller/v3/*") // *를 하면 하위 요청을 모두 여기를 받는다.
public class FrontControllerServletV3 extends HttpServlet {

  private Map<String, ControllerV3> controllerMap = new HashMap<>();

  public FrontControllerServletV3() {
    controllerMap.put("/front-controller/v3/members/new-form", new MemberFormControllerV3());
    controllerMap.put("/front-controller/v3/members/save", new MemberSaveControllerV3());
    controllerMap.put("/front-controller/v3/members", new MemberListControllerV3());
  }

  @Override
  protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    System.out.println("FrontControllerServletV3 service");

    String requestURI = request.getRequestURI();
    System.out.println(requestURI);
    ControllerV3 controller = controllerMap.get(requestURI); // 다형성을 사용함
    if (controller == null) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return ;
    }

    Map<String, String> paraMap = createParamMap(request);
    ModelView mv = controller.process(paraMap);

    String viewName = mv.getViewName();// 논리이름 new-form
    MyView view = viewResolver(viewName);

    view.render(mv.getModel(), request, response);
  }

  private MyView viewResolver(String viewName) {
    return new MyView("/WEB-INF/views/" + viewName + ".jsp");
  }

  private Map<String, String> createParamMap(HttpServletRequest request) {
    Map<String, String> paraMap = new HashMap<>();
    request.getParameterNames().asIterator()
            .forEachRemaining(paramName -> paraMap.put(paramName, request.getParameter(paramName)));
    return paraMap;
  }

}

