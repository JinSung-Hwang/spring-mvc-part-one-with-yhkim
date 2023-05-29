<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="hello.servlet.domain.member.Member" %>
<%@ page import="hello.servlet.domain.member.MemberRepository" %>

<%--기존 서블릿코드가 HTML을 동적으로 생성하지만 자바코드안에서 HTML코드가 있어 HTML 다루기 불편했다.--%>
<%--하여 JSP를 이용하여 HTML과 비지니스코드를 분리함 하지만 비지니스코드가 HTML과 같이 있어 아직도 불편함--%>

<% // JSP 코드가 들어가는 영역
  // request, response는 바로 사용 가능
  MemberRepository memberRepository = MemberRepository.getInstance();

  String username = request.getParameter("username");
  int age = Integer.parseInt(request.getParameter("age"));

  Member member = new Member(username, age);
  memberRepository.save(member);
%>

<html>
<head>
    <title>Title</title>
</head>
<body>
성공
<ul>
  <li>id=<%=member.getId()%></li>
  <li>username=<%=member.getUsername()%></li>
  <li>age=<%=member.getAge()%></li>
</ul>
<a href="/index.html">메인</a>
</body>
</html>
