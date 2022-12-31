<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%
// PopupMain에서 ajax 요청을 했을때
String chkVal = request.getParameter("inactiveToday");// get 요청으로 보낸 파라미터

if (chkVal != null && chkVal.equals("1")) {
    Cookie cookie = new Cookie("PopupClose", "off");  // 쿠키 생성 
    cookie.setPath(request.getContextPath());  // 경로 설정 / 쿠키의 적용 범위 설정 모든 웹 애플리케이션에 적용
    cookie.setMaxAge(60*60*24);  // 유지 기간 설정
    response.addCookie(cookie);  // 응답 객체에 추가 
    out.println("쿠키 : 하루 동안 열지 않음");  // 실제로 응답되는 메시지
}
%>