<%@ page contentType="text/html;charset=UTF-8" language="java" %> // JSP는 첫줄에 이것을 적어줘야한다. // 실행할때는 url 주소와 .jsp까지 모두 적어줘야한다.
<%@ page import="hello.servlet.domain.member.Member" %>
<%@ page import="java.util.List" %>
<%@ page import="hello.servlet.domain.member.MemberRepository" %>
<%--
  Created by IntelliJ IDEA.
  User: jinsunghwang
  Date: 2023/05/22
  Time: 4:40 PM
  To change this template use File | Settings | File Templates.
--%>

<%-- <% ~~ %> 는 자바 문법을 이용할 수 있고--%>
<%-- <%=%> ~~ %> 는 자바 문법을 출력할 수 있다.--%>
<%
  MemberRepository memberRepository = MemberRepository.getInstance();
  List<Member> members = memberRepository.findAll();


%>
<html>
<head>
  <meta charset="UTF-8">
  <title>Title</title>
</head>
<body>
<a href="/index.html">메인</a>
<table>
  <thead>
  <th>id</th>
  <th>username</th>
  <th>age</th>
  </thead>
  <tbody>
  <%
    for (Member member : members) {
      out.write("    <tr>"); // out은 예약어
      out.write("       <td>" + member.getId() + "</td>");
      out.write("       <td>" + member.getUsername() + "</td>");
      out.write("       <td>" + member.getAge() + "</td>");
      out.write("    </tr>");
    }
  %>
  </tbody>
</table>
</body>
</html>
