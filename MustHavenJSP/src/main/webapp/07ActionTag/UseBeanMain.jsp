<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>액션 태그 - UseBean</title>
</head>
<body>
    <h2>useBean 액션 태그</h2>
    <h3>자바빈즈 생성하기</h3>
    <jsp:useBean id="person" class="common.Person" scope="request" />

    <h3>setProperty 액션 태그로 자바빈즈 속성 지정하기</h3>
    <jsp:setProperty name="person" property="name" value="임꺽정" /> 
    <jsp:setProperty name="person" property="age" value="41" />
    
    <jsp:useBean id="person1" class="common.Person" scope="request" />
    <jsp:setProperty name="person1" property="name" value="홍길동" /> 
    <jsp:setProperty name="person1" property="age" value="45" /> 

    <h3>getProperty 액션 태그로 자바빈즈 속성 읽기</h3>
    <ul>
        <li>이름 : <jsp:getProperty name="person" property="name" /></li> 
        <li>나이 : <jsp:getProperty name="person" property="age" /></li> 
        <li>이름 : <jsp:getProperty name="person1" property="name" /></li> 
        <li>나이 : <jsp:getProperty name="person1" property="age" /></li> 
    </ul>
    <!-- 
   		위 액션태그를 자바코드로 바꾸면 다음과 같다.
   		기존에 만들어둔 코드가 없을 때만 새롭게 객체를 생성하여 request 영역에 저장
   		Person person = (Person)request.getAttribute("Person"); // request 영역에서 가져옴
    	if (person == null) {
    		person = new Person();
    		request.setAttribute("person", person);
    	}
     -->
</body>
</html>