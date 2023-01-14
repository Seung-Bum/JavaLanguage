<%@ page import="model1.board.BoardDAO"%>
<%@ page import="model1.board.BoardDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="./IsLoggedIn.jsp"%>
<%
// 수정 내용 얻기
String num = request.getParameter("num"); 
String title = request.getParameter("title");
String content = request.getParameter("content");

// DTO에 저장
BoardDTO dto = new BoardDTO();
dto.setNum(num);
dto.setTitle(title);
dto.setContent(content); 

// DB에 반영
BoardDAO dao = new BoardDAO(application); 
int affected = dao.updateEdit(dto);  // 몇개 행이 성공 했는지 개수
dao.close();

// 성공/실패 처리
if (affected == 1) { 
    // 성공 시 상세 보기 페이지로 이동
    response.sendRedirect("View.jsp?num=" + dto.getNum()); 
} 
else {
    // 실패 시 이전 페이지로 이동, 수정하기 페이지, 사실 오류가 발생할거라면 BoardDAO에서 발생할거임
    JSFunction.alertBack("수정하기에 실패하였습니다.", out);
}
%>
