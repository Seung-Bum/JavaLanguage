<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>내장 객체 - response</title>
</head>
<body>
	<h2>1. 로그인 폼</h2>
	<%
	String loginErr = request.getParameter("loginErr");
	if ( loginErr != null ) out.print("로그인 실패");
	%>
	<form action="./ResponseLogin.jsp" method="post">
		아이디 : <input type="text" name="user_id" /></br>
		패스워드 : <input type="text" name="user_pwd" /></br>
		<input type="submit" value="응답 헤더 설정 & 출력" />
	</form>
</body>
</html>