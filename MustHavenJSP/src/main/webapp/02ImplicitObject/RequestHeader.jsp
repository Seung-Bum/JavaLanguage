<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.Enumeration" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>내장객체 - Request</title>
</head>
<body>
	<h2>3. 요청 헤더 정보 출력하기</h2>
	<%
		Enumeration headers = request.getHeaderNames(); // 모든 요청헤더 반환
		while (headers.hasMoreElements()){ // 출력할 요청 헤더 확인
			String headerName = (String)headers.nextElement();
			String headerValue = request.getHeader(headerName);
			out.print("헤더명 : " + headerName + "헤더값 : " + headerValue + "</br>");
		}
	%>
	<p> 이파일을 직접 실행하면 referer 정보는 출력되지 않습니다.</p>
</body>
</html>