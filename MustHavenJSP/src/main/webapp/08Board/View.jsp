<%@ page import="model1.board.BoardDAO"%>
<%@ page import="model1.board.BoardDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
String num = request.getParameter("num");  // 일련번호 받기 

BoardDAO dao = new BoardDAO(application);  // DAO 생성 
dao.updateVisitCount(num);                 // 조회수 증가 
BoardDTO dto = dao.selectView(num);        // 게시물 가져오기 
dao.close();                               // DB 연결 해제
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원제 게시판</title>
<script>
function deletePost() {
    var confirmed = confirm("정말로 삭제하겠습니까?"); // 확인창 생성, 확인/취소 나오고 확인시 True 취소시 False
    if (confirmed) {
        var form = document.writeFrm;       // 이름(name)이 "writeFrm"인 폼 선택
        form.method = "post";               // 전송 방식 
        form.action = "DeleteProcess.jsp";  // 전송 경로
        form.submit();                      // 폼값 전송
    }
}
</script>
</head>
<body>
<jsp:include page="../Common/Link.jsp" />
<h2>회원제 게시판 - 상세 보기(View)</h2>
<form name="writeFrm">
    <input type="hidden" name="num" value="<%= num %>" />  <!-- 공통 링크, hidden 으로 num을 같이 넘긴다. -->

    <table border="1" width="90%">
        <tr>
            <td>번호</td>
            <td><%= dto.getNum() %></td>
            <td>작성자</td>
            <td><%= dto.getName() %></td>
        </tr>
        <tr>
            <td>작성일</td>
            <td><%= dto.getPostdate() %></td>
            <td>조회수</td>
            <td><%= dto.getVisitcount() %></td>
        </tr>
        <tr>
            <td>제목</td>
            <td colspan="3"><%= dto.getTitle() %></td>
        </tr>
        <tr>
            <td>내용</td>
            <td colspan="3" height="100">
            <%= dto.getContent().replace("\r\n", "<br/>") %></td>
        </tr>
        <tr>
            <td colspan="4" align="center">
            <%
            if (session.getAttribute("UserId") != null								// 모두 게시글을 볼수는 있으나 수정 삭제는 글쓴이만 할 수 있게함
                && session.getAttribute("UserId").toString().equals(dto.getId())) { // 로그인 되어있는 UserId와 글쓴이 UserId가 일치해야 수정/삭제 가능
            %>
                <button type="button"
                        onclick="location.href='Edit.jsp?num=<%= dto.getNum() %>';">
                    수정하기</button>
                <button type="button" onclick="deletePost();">삭제하기</button> 
            <%
            }
            %>
                <button type="button" onclick="location.href='List.jsp';">
                    목록 보기
                </button>
            </td>
        </tr>
    </table>
</form>
</body>
</html>
