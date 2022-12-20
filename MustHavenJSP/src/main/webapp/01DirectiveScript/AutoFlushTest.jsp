<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    buffer="1kb" autoFlush="false"
    errorPage="IsErrorPage.jsp"%> 
    <!-- buffer 설정 
    	true 기본값 버퍼가 채워지면 자동으로 플러시
    	false 버퍼가 채워지면 예외 발생
    	errorPage 응용
    -->
<%@ page import="java.text.SimpleDateFormat"%>
<%@ page import="java.util.Date"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>page 지시어 - buffer, autoFlush 속성</title>
</head>
<body>
<%
	for(int i = 1; i<=100; i++) {
		out.println("asdfd12345");
	}
%>
</body>
</html>