<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
// RequestParmeter.jsp에서 인코딩하던 방식
//request.setCharacterEncoding("UTF-8");
String name = request.getParameter("name");  
String age = request.getParameter("age");
%>
<html>
<head><title>액션 태그 - UseBean</title></head>
<body>
    <h3>액션 태그로 폼값 한 번에 받기 / web.xml에서 일괄 인코딩 적용</h3>
    <jsp:useBean id="person" class="common.Person" />  
    <jsp:setProperty property="*" name="person" />  
    <ul>
        <li>이름 : <jsp:getProperty name="person" property="name" /></li>  
        <li>나이 : <jsp:getProperty name="person" property="age" /></li> 
        
     <h3>RequestParmeter.jsp에서 인코딩하던 방식</h3>
        <li>이름 : <%=name %></li>  
        <li>나이 : <%=age %></li> 
    </ul>
</body>
</html>