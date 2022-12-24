<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import='java.util.Collection' %>
<%@ page import='java.text.SimpleDateFormat' %>
<%
// 응답 헤더에 추가할 값 준비
SimpleDateFormat s = new SimpleDateFormat("yyyy-mm-dd");
long add_date = s.parse(request.getParameter("add_date")).getTime();
int add_int = Integer.parseInt(request.getParameter("add_int"));
String add_str = request.getParameter("add_str");

// 응답 헤더에 값 추가
response.addDateHeader("myBirthday", add_date); // date
response.addIntHeader("myNumber", add_int); // int
response.addIntHeader("myNumber", 1004); // int
response.addHeader("myName", add_str); // str
response.setHeader("myName", "안중근"); // str
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>내장 객체 - response</title>
</head>
<body>
	<h2>응답 헤더 정보 출력하기</h2>
	<%
	Collection<String> headerNames = response.getHeaderNames(); // 모든 HeaderNames 가져온다.
	for (String hName : headerNames) {							// 같은 이름은 첫번째 값을 가져온다.
		String hValue = response.getHeader(hName);
	%>
		<li><%= hName %> : <%= hValue %></li>
	<%
	}
	%>
	
	<h2>myNumber만 출력하기</h2>
	<%
	Collection<String> myNumber = response.getHeaders("myNumber"); // 특정 HeaderName을 가져온다.
	for (String myNum : myNumber) {
	%>
		<li>myNumber : <%= myNum %></li>
	<%
	}
	%>
</body>
</html>