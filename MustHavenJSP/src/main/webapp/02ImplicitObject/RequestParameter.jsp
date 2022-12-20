<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>내장객체 - Request</title>
</head>
<body>
<%
request.setCharacterEncoding("UTF-8"); // 한글이 깨질 경우 인코딩
String id = request.getParameter("id");
String sex = request.getParameter("sex");
String[] favo = request.getParameterValues("favo"); // 값이 2개 이상인 경우
String favoStr = "";
if (favo != null) {
	for (int i = 0; i < favo.length; i++){
		favoStr += favo[i] + " ";
	}
}
String intro = request.getParameter("intro").replace("\r\n","</br>");
// 방어코드로 특수문자 입력관련 replace 함수도 필요
%>
<ul>
	<li>아이디 : <%= id %></li>
	<li>성별 : <%= sex %></li>
	<li>관심사항 : <%= favoStr %></li>
	<li>자기소개 : <%= intro %></li>
</ul>
</body>
</html>