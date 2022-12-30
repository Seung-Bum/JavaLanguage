<%@ page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
ArrayList<String> lists = new ArrayList<String>();
lists.add("리스트");
lists.add("컬렉션");
session.setAttribute("lists", lists);
%>       
<html>
<head><title>session 영역</title></head>
<body>
    <h2>페이지 이동 후 session 영역의 속성 읽기</h2>
    <a href="SessionLocation.jsp">SessionLocation.jsp 바로가기</a>
    <!-- 세션영역이 이동된 페이지에서도 공유가 되는지 확인 -->
    <!-- 브라우저가 닫힐때 세션 공유도 끝남 -->
</body>
</html>