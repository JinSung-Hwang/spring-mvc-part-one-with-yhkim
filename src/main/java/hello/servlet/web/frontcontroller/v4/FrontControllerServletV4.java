package hello.servlet.web.frontcontroller.v4;

import hello.servlet.web.frontcontroller.ModelView;
import hello.servlet.web.frontcontroller.MyView;
import hello.servlet.web.frontcontroller.v4.controller.MemberFormControllerV4;
import hello.servlet.web.frontcontroller.v4.controller.MemberListControllerV4;
import hello.servlet.web.frontcontroller.v4.controller.MemberSaveControllerV4;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "frontControllerServletV4", urlPatterns = "/front-controller/v4/*") // *를 하면 하위 요청을 모두 여기를 받는다.
public class FrontControllerServletV4 extends HttpServlet {

  private Map<String, ControllerV4> controllerMap = new HashMap<>();

  public FrontControllerServletV4() {
    controllerMap.put("/front-controller/v4/members/new-form", new MemberFormControllerV4());
    controllerMap.put("/front-controller/v4/members/save", new MemberSaveControllerV4());
    controllerMap.put("/front-controller/v4/members", new MemberListControllerV4());
  }

  @Override
  protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    System.out.println("FrontControllerServletV4 service");

    String requestURI = request.getRequestURI();
    System.out.println(requestURI);
    ControllerV4 controller = controllerMap.get(requestURI); // 다형성을 사용함
    if (controller == null) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return ;
    }

    Map<String, String> paraMap = createParamMap(request);
    Map<String, Object> modelMap = new HashMap<>();
    String viewName = controller.process(paraMap, modelMap);
    MyView view = viewResolver(viewName);
    view.render(modelMap, request, response);
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

