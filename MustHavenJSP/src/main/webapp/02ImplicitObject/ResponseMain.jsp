<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>���� ��ü - response</title>
</head>
<body>
	<h2>1. �α��� ��</h2>
	<%
	String loginErr = request.getParameter("loginErr");
	if ( loginErr != null ) out.print("�α��� ����");
	%>
	<form action="./ResponseLogin.jsp" method="post">
		���̵� : <input type="text" name="user_id" /></br>
		�н����� : <input type="text" name="user_pwd" /></br>
		<input type="submit" value="���� ��� ���� & ���" />
	</form>
</body>
</html>