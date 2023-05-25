package hello.servlet.web.frontcontroller.v5;

import hello.servlet.web.frontcontroller.ModelView;
import hello.servlet.web.frontcontroller.MyView;
import hello.servlet.web.frontcontroller.v3.ControllerV3;
import hello.servlet.web.frontcontroller.v3.controller.MemberFormControllerV3;
import hello.servlet.web.frontcontroller.v3.controller.MemberListControllerV3;
import hello.servlet.web.frontcontroller.v3.controller.MemberSaveControllerV3;
import hello.servlet.web.frontcontroller.v4.ControllerV4;
import hello.servlet.web.frontcontroller.v5.adapter.ControllerV3HandlerAdapter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(name =" FrontControllerServletV5", urlPatterns = "/front-controller/v5/*")
public class FrontControllerServletV5 extends HttpServlet {

  private final Map<String, Object> handlerMappingMap = new HashMap<>(); // 기존에는 map에 버전에 따라서 넣었는데 이제는 여러 버전을 사용하기 위해서 object를 넣는다.
  private final List<MyHandlerAdapter> handlerAdapters = new ArrayList<>();

  public FrontControllerServletV5() {
    initHandlerMappingMap();

    initHandlerAdapters();
  }

  private void initHandlerAdapters() {
    handlerAdapters.add(new ControllerV3HandlerAdapter());
  }

  private void initHandlerMappingMap() {
    handlerMappingMap.put("/front-controller/v5/v3/members/new-form", new MemberFormControllerV3());
    handlerMappingMap.put("/front-controller/v5/v3/members/save", new MemberSaveControllerV3());
    handlerMappingMap.put("/front-controller/v5/v3/members", new MemberListControllerV3());
  }

  protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    Object handler = getObject(request);

    if (handler == null) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return ;
    }

    MyHandlerAdapter adapter = getHandlerAdapter(handler);
    ModelView mv = adapter.handle(request, response, handler);
    String viewName = mv.getViewName();

    MyView view = viewResolver(viewName);

    view.render(mv.getModel(), request, response);

  }

  private MyHandlerAdapter getHandlerAdapter(Object handler) {
    for (MyHandlerAdapter adapter : handlerAdapters) {
      if (adapter.supports(handler)) {
        return adapter;
      }
    }
    throw new IllegalArgumentException("handler adapter를 찾을 수 없습니다.");
  }

  private Object getObject(HttpServletRequest request) {
    String requestURI = request.getRequestURI();
    Object controller = handlerMappingMap.get(requestURI);
    return controller;
  }

  private MyView viewResolver(String viewName) {
    return new MyView("/WEB-INF/views/" + viewName + ".jsp");
  }

}
