package model2.mvcboard;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fileupload.FileUtil;
import utils.JSFunction;

@WebServlet("/mvcboard/pass.do")
public class PassController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) // view.jsp에서 수정하기나 삭제하기를 누를 경우 비밀번호 확인필요
        throws ServletException, IOException {
        req.setAttribute("mode", req.getParameter("mode"));
        req.getRequestDispatcher("/14MVCBoard/Pass.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        // 留ㅺ컻蹂��닔 ���옣
        String idx = req.getParameter("idx");
        String mode = req.getParameter("mode");
        String pass = req.getParameter("pass");

        // 鍮꾨�踰덊샇 �솗�씤
        MVCBoardDAO dao = new MVCBoardDAO();
        boolean confirmed = dao.confirmPassword(pass, idx);
        dao.close();

        if (confirmed) {  // 鍮꾨�踰덊샇 �씪移�
            if (mode.equals("edit")) {  // �닔�젙 紐⑤뱶
                HttpSession session = req.getSession();
                session.setAttribute("pass", pass);
                resp.sendRedirect("../mvcboard/edit.do?idx=" + idx);
            }
            else if (mode.equals("delete")) {  // �궘�젣 紐⑤뱶
                dao = new MVCBoardDAO();
                MVCBoardDTO dto = dao.selectView(idx);
                int result = dao.deletePost(idx);  // 寃뚯떆臾� �궘�젣
                dao.close();
                if (result == 1) {  // 寃뚯떆臾� �궘�젣 �꽦怨� �떆 泥⑤��뙆�씪�룄 �궘�젣
                    String saveFileName = dto.getSfile();
                    FileUtil.deleteFile(req, "/Uploads", saveFileName);
                }
                JSFunction.alertLocation(resp, "�궘�젣�릺�뿀�뒿�땲�떎.", "../mvcboard/list.do");
            }
        }
        else {  // 鍮꾨�踰덊샇 遺덉씪移�
            JSFunction.alertBack(resp, "鍮꾨�踰덊샇 寃�利앹뿉 �떎�뙣�뻽�뒿�땲�떎.");
        }
    }
}
