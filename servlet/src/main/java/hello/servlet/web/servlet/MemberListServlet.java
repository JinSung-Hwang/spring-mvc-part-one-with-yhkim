package hello.servlet.web.servlet;

import hello.servlet.domain.member.Member;
import hello.servlet.domain.member.MemberRepository;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "memberListServlet", urlPatterns = "/servlet/members")
public class MemberListServlet extends HttpServlet {
  private MemberRepository memberRepository = MemberRepository.getInstance();


  @Override
  protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    List<Member> members = memberRepository.findAll();

    response.setContentType("text/html");
    response.setCharacterEncoding("utf-8");

    // html은 정적이라서 동적으로 element를 추가하기는 어렵다.
    // 그래서 servlet으로 동적으로 element를 추가하도록 만들었다.
    // 하지만 자바 코드 안에 html을 넣으니까 유지보수가 어렵다.
    // 그래서 HTML안에 자바 코드를 넣는 것을 템플릿 엔진이라는 기술이 나왔고 그 예시로 JSP, thymeleaf 등이 있다.
    PrintWriter w = response.getWriter();
    w.write("<html>");
    w.write("<head>");
    w.write("    <meta charset=\"UTF-8\">");
    w.write("    <title>Title</title>");
    w.write("</head>");
    w.write("<body>");
    w.write("<a href=\"/index.html\">메인</a>");
    w.write("<table>");
    w.write("    <thead>");
    w.write("    <th>id</th>");
    w.write("    <th>username</th>");
    w.write("    <th>age</th>");
    w.write("    </thead>");
    w.write("    <tbody>");

    for (Member member : members) { // 동적으로 html tag 생성
      w.write("    <tr>");
      w.write("        <td>" + member.getId() + "</td>");
      w.write("        <td>" + member.getUsername() + "</td>");
      w.write("        <td>" + member.getAge() + "</td>");
      w.write("    </tr>");
    }

    w.write("    </tbody>");
    w.write("</table>");
    w.write("</body>");
    w.write("</html>");

  }
}
