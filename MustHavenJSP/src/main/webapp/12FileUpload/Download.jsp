<%@ page import="utils.JSFunction"%>
<%@ page import="java.io.FileNotFoundException"%>
<%@ page import="java.io.FileInputStream"%>
<%@ page import="java.io.File"%>
<%@ page import="java.io.OutputStream"%>
<%@ page import="java.io.InputStream"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
String saveDirectory = application.getRealPath("/Uploads"); // 업로드 폴더의 물리적경로를 얻어옴
String saveFilename = request.getParameter("sName"); // 다운로드 버튼을 눌렀을때 호출되어 보내지는 파라미터, 저장할 때 파일 이름
String originalFilename = request.getParameter("oName"); // 다운로드 버튼을 눌렀을때 호출되어 보내지는 파라미터, 원본 파일 이름

try {
    // 파일을 찾아 입력 스트림 생성
    File file = new File(saveDirectory, saveFilename);  
    InputStream inStream = new FileInputStream(file);
    
    // 한글 파일명 깨짐 방지
    String client = request.getHeader("User-Agent"); // 요청을 보낸 웹 브라우저의 종류를 알 수 있다.
    if (client.indexOf("WOW64") == -1) { 
    	// 웹브라우저의 한글 처리방식이 다르다, WOW64는 Windows on Windows 64 bit라는 뜻임
    	// 인터넷 익스플로러, 국내에서는 아직도 많이 사용되고 있어서 처리해줄 필요가 있다고함
        originalFilename = new String(originalFilename.getBytes("UTF-8"), "ISO-8859-1");
    }
    else {
        originalFilename = new String(originalFilename.getBytes("KSC5601"), "ISO-8859-1");
    }
   
    // 파일 다운로드용 응답 헤더 설정 
    response.reset();
    response.setContentType("application/octet-stream");
    response.setHeader("Content-Disposition", 
                       "attachment; filename=\"" + originalFilename + "\""); // 저장 할때는 한글 처리때문에 영문숫자로 저장, 다운로드시에는 원본파일명 가져와서 응답
    response.setHeader("Content-Length", "" + file.length() );
    
    // 출력 스트림 초기화
    out.clear();  
    
    // response 내장 객체로부터 새로운 출력 스트림 생성
    OutputStream outStream = response.getOutputStream();  

    // 출력 스트림에 파일 내용 출력
    byte b[] = new byte[(int)file.length()];
    int readBuffer = 0;    
    while ( (readBuffer = inStream.read(b)) > 0 ) {
        outStream.write(b, 0, readBuffer);
    }

    // 입/출력 스트림 닫음
    inStream.close(); 
    outStream.close();
}
catch (FileNotFoundException e) {
    JSFunction.alertBack("파일을 찾을 수 없습니다.", out);
}
catch (Exception e) {
    JSFunction.alertBack("예외가 발생하였습니다.", out);
}
%>