<%@ page import="hello.servlet.domain.member.Member" %><%--
  Created by IntelliJ IDEA.
  User: jinsunghwang
  Date: 2023/05/22
  Time: 5:51 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Title</title>
</head>
<body>
성공 save-result
<ul>

  <li>id=<%=((Member)request.getAttribute("member")).getId()%></li>
  <li>username=<%=((Member)request.getAttribute("member")).getUsername()%></li>
<%--  <li>age=<%=((Member)request.getAttribute("member")).getAge()%></li>--%>
  <li>age=${member.age}</li> <%-- 위 코드를 모두 작성하려면 힘들기 떄문에 JSP에서는 setter접근법이라는 표현식을 제공한다. --%>
</ul>
<a href="/index.html">메인</a>
</body>
</html>
