<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>���� ��ü - response</title>
</head>
<body>
	<%
	String id = request.getParameter("user_id");
	String pwd = request.getParameter("user_pwd");
	if (id.equalsIgnoreCase("must") && pwd.equalsIgnoreCase("1234")) { // ��ҹ��� ���о��� ���ں�
		response.sendRedirect("ResponseWelcome.jsp");
	} else {
		request.getRequestDispatcher("ResponseMain.jsp?loginErr=1")
			.forward(request, response);
	}
	%>

</body>
</html>