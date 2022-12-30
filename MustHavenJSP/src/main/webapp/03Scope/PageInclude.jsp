<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="common.Person" %>

<h4>Include 페이지</h4>
<%
int pInteger2 = (Integer)(pageContext.getAttribute("pageInteger"));
//String pString2 = pageContext.getAttribute("PageString").toString();
Person pPerson2 = (Person)(pageContext.getAttribute("pagePerson"));
%>
	<ul>
		<li>Integer 객체 : <%= pInteger2 %></li>
		<!-- 기본타입 래퍼 클래스일 경우 별도의 형변환 없이 출력가능 -->
		<li>String  객체 : <%= pageContext.getAttribute("PageString") %></li>
		<li>Person  객체 : <%= pPerson2.getName() %>, <%= pPerson2.getAge() %></li>
	</ul>
