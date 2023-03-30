<%@ page import="model2.mvcboard.LogTest"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>
<head><title>LogPrintTest</title></head>
<body>
    <h2>log4j Test</h2>
    <%
    MyfileDAO dao = new MyfileDAO();
    List<MyfileDTO> fileLists = dao.myFileList();
    dao.close();
    %>   
</body>
</html>