package model2.mvcboard;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/mvcboard/view.do") // web.xml에 내용 없이 애너테이션으로 요청명과 서블릿을 매핑
public class ViewController extends HttpServlet { // 게시물 제목에 걸린 링크를 통해 호출 된다.
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp)
        throws ServletException, IOException {
        // 게시물 불러오기
        MVCBoardDAO dao = new MVCBoardDAO();
        String idx = req.getParameter("idx"); // 요청시 가져오는 값
        dao.updateVisitCount(idx);  // 해당 게시물 조회수 1 증가
        MVCBoardDTO dto = dao.selectView(idx);
        dao.close();

        // 줄바꿈 처리
        dto.setContent(dto.getContent().replaceAll("\r\n", "<br/>")); // 글내용에 있는 개행을 HTML에서는 인식하지 않기 때문에 태그형식으로 변경

        // 게시물(dto) 저장 후 뷰로 포워드
        req.setAttribute("dto", dto); // DTO 객체를 request영역에 저장
        req.getRequestDispatcher("/14MVCBoard/View.jsp").forward(req, resp); // 뷰로 포워드 한다.
    }
}
