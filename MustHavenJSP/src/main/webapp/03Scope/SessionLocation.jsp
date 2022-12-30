<%@ page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>
<head><title>session 영역</title></head>
<body>
    <h2>페이지 이동 후 session 영역의 속성 읽기</h2>
    <%
    ArrayList<String> lists = (ArrayList<String>)session.getAttribute("lists"); 
    for (String str : lists)
        out.print(str + "<br/>");
    %>
    <!-- SessionMain.jsp에서 Session에 string 2개 입력해 놓음
    	 브라우저를 껐다가 http://localhost:8081/MustHavenJSP/03Scope/SessionLocation.jsp에 바로 들어가면
    	 내부서버오류 500을 발생시킨다.
    	 브라우저를 완전히 닫을때 Session 객체도 같이 소멸되기 때문
     -->  
</body>
</html>
