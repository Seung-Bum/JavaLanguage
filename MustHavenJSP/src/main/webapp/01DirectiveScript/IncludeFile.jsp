<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.text.SimpleDateFormat"%>
<%@ page import="java.util.Date"%>
<%@ page import="java.time.LocalDateTime"%>
<%@ page import="java.time.LocalDate"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Include File</title>
</head>
<body>
<%
	LocalDate today = LocalDate.now(); // 오늘날짜
	LocalDateTime tomorrow = LocalDateTime.now().plusDays(1); // 내일날짜
%>
</body>
</html>