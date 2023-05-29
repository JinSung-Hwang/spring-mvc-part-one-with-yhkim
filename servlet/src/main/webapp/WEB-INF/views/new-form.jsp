<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <meta charset="UTF-8">
  <title>Title</title>
</head>
<body>
<%-- action에 /save나 /members/save 이런식으로 작성하면 절대경로가 되지만 save 하나만 작성하면 상대결로가 된다.--%>
<!-- 상대경로 사용, [현재 URL이 속한 계층 경로 + /save] -->
<%--상대경로는 /가 없이 시작하는것을 상대 경로라고함--%>
<form action="save" method="post">
  username: <input type="text" name="username" /> age: <input type="text" name="age" /> <button type="submit">전송</button>
</form>
</body>
</html>