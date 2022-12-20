<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%!
	public int add(int num1, int num2){
		return num1 + num2;
	}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>스크립트 요소</title>
</head>
<body>
<!-- 스크립틀릿 안에는 메서드 선언이 안된다.
JSP 파일이 서블릿으로 변환되는 과정에서 _jspService() 메서드가 만들어지는데
ScriptElement.jsp 실행시 .java파일로 변환후 .class 파일로 컴파일 되고
이때 스크립틀릿 안에 작성한 내용은 .java 파일안의 _jspService() 안으로 들어가기 때문에
자바의 문법상 메서드안에 메서드 선언이 불가 하기 때문이다. -->
<%
	int result = add(10, 20);
%>
덧셈결과 1 : <%= result %> <br/>
덧셈결과 2 : <%= add(30, 40) %>
</body>
</html>