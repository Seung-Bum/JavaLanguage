package model2.mvcboard;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;

import fileupload.FileUtil;
import utils.JSFunction;

public class WriteController extends HttpServlet {
	// 처음에 페이지 열때 요청 페이지
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.getRequestDispatcher("/14MVCBoard/Write.jsp").forward(req, resp); // /mvcboard/write.do로 요청 들어오면 처리해서 포워드
    }
    
    // 요청한 페이지 /14MVCBoard/Write.jsp 를 열고 여기서 폼요청하면 post로 처리 
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        // 1. 파일 업로드 처리 =============================
        // 업로드 디렉터리의 물리적 경로 확인
        String saveDirectory = req.getServletContext().getRealPath("/Uploads");

        // 초기화 매개변수로 설정한 첨부 파일 최대 용량 확인
        ServletContext application = getServletContext();
        int maxPostSize = Integer.parseInt(application.getInitParameter("maxPostSize")); // web.xml에서 설정함

        // 파일 업로드
        MultipartRequest mr = FileUtil.uploadFile(req, saveDirectory, maxPostSize);
        if (mr == null) {
            // 파일 업로드 실패
            JSFunction.alertLocation(resp, "첨부 파일이 제한 용량을 초과합니다.",
                                     "../mvcboard/write.do");  
            return;
        }

        // 2. 파일 업로드 외 처리 =============================
        // 폼값을 DTO에 저장
        // DB로 옮긴다.
        MVCBoardDTO dto = new MVCBoardDTO(); 
        dto.setName(mr.getParameter("name")); // mr은 MultipartRequest 객체
        dto.setTitle(mr.getParameter("title"));
        dto.setContent(mr.getParameter("content"));
        dto.setPass(mr.getParameter("pass"));

        // 원본 파일명과 저장된 파일 이름 설정
        String fileName = mr.getFilesystemName("ofile");
        if (fileName != null) {
            // 첨부 파일이 있을 경우 파일명 변경
            // 새로운 파일명 생성
            String now = new SimpleDateFormat("yyyyMMdd_HmsS").format(new Date());
            String ext = fileName.substring(fileName.lastIndexOf(".")); // 확장자 가져오기
            String newFileName = now + ext;

            // OS에 맞는 Separator를 동적으로 가져와 사용하도록 구현이 되어야함
            File oldFile = new File(saveDirectory + File.separator + fileName);
            File newFile = new File(saveDirectory + File.separator + newFileName);
            oldFile.renameTo(newFile);
            
            dto.setOfile(fileName);  // 원래 파일 이름
            dto.setSfile(newFileName);  // 서버에 저장된 파일 이름
        }

        // DAO를 통해 DB에 게시 내용 저장
        MVCBoardDAO dao = new MVCBoardDAO();
        int result = dao.insertWrite(dto);
        dao.close();

        // 성공 or 실패 ?
        if (result == 1) {  // 글쓰기 성공
            resp.sendRedirect("../mvcboard/list.do");
        }
        else {  // 글쓰기 실패
            resp.sendRedirect("../mvcboard/write.do");
        }
    }}