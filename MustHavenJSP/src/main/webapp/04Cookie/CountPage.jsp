<%@ page import="utils.CookieManager"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
// 각 페이지를 들어갈때 마다 cookie 생성? 각각 다른 페이지 이름으로
String countMode = CookieManager.readCookie(request, "count");

if (!countMode.equals("")) { 
	if(countMode.equals("0")) {
		int cNum = Integer.parseInt(countMode) + 1;
		
		// 한번 본 게시물이기 때문에 다시 카운트할 필요가 없어 off로 설정
		CookieManager.makeCookie(response, "count", Integer.toString(cNum), 60*60*24*7);
	}
}

System.out.print(countMode);

%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>쿠키를 이용한 조회수 관리</title>
</head>
<body>
<h2>게시글 페이지</h2>
    <table border="1">
	<th>게시글</th>
	<tr><!-- 첫번째 줄 시작 -->
	    <td>호호호호호호호호호호호호호호호호호호호</td>
	</tr><!-- 첫번째 줄 끝 -->
    </table>
<h4>조회수 : <%= countMode%></h4>
</body>
</html>