<%@ page import="common.Person"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
// 페이지에서 공유할 내용을 설정
pageContext.setAttribute("pageInteger", 1000); // 기본타입
pageContext.setAttribute("PageString", "페이지 영역의 문자열");
pageContext.setAttribute("pagePerson", new Person("한석봉", 99));
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Page 영역</title>
</head>
<body>
	<h2>page 영영의 속성값 읽기</h2>
	<%
	int pInteger = (Integer)(pageContext.getAttribute("pageInteger"));
	String pString = pageContext.getAttribute("PageString").toString();
	Person pPerson = (Person)(pageContext.getAttribute("pagePerson"));
	%>
	<ul>
		<li>Integer 객체 : <%= pInteger %></li>
		<li>String  객체 : <%= pString %></li>
		<li>Person  객체 : <%= pPerson.getName() %>, <%= pPerson.getAge() %></li>
	</ul>
	
	<h2>include된 파일에서 page 영역 읽어오기</h2>
	<!-- 포함된 페이지는 타입관련 주석을 한번더 쓸 필요가 없음, 결국 하나의 페이지로 간주 -->
	<%@ include file = "PageInclude.jsp" %>
	
	<h2>페이지 이동후 page 영역 읽어오기</h2>
	<a href="PageLocation.jsp">PageLocation.jsp 바로가기</a>
	<!-- 다른 페이지에서는 pageContext 설정을 확인할 수 없다. -->
</body>
</html>