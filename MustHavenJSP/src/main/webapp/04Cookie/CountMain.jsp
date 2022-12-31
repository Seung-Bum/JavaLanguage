<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="utils.CookieManager"%>
<%

// 접속시 각각 게시물의 count는 0이다.
// 지금은 하나만 하는데 나중에는 각게시물 마다로? 해야할듯
CookieManager.makeCookie(response, "count", "0", 60*60*24*7);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>쿠키를 이용한 조회수 관리</title>
</head>
<body>
<h2>게시글 조회목록 페이지</h2>
    <table border="1">
	<th>제목</th>
	<th>날짜</th>
	<tr><!-- 첫번째 줄 시작 -->
	    <td><a id="countBtn" href="./CountPage.jsp">안녕하세요</a></td>
	    <td>2022-12-30</td>
	</tr><!-- 첫번째 줄 끝 -->
	<tr><!-- 두번째 줄 시작 -->
	    <td>안뇽하세요</td>
	    <td>2022-12-30</td>
	</tr><!-- 두번째 줄 끝 -->
    </table>
</body>
</html>