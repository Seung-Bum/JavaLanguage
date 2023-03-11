package model2.mvcboard;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/mvcboard/view.do") // web.xml�� ���� ���� �ֳ����̼����� ��û��� ������ ����
public class ViewController extends HttpServlet { // �Խù� ���� �ɸ� ��ũ�� ���� ȣ�� �ȴ�.
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp)
        throws ServletException, IOException {
        // �Խù� �ҷ�����
        MVCBoardDAO dao = new MVCBoardDAO();
        String idx = req.getParameter("idx"); // ��û�� �������� ��
        dao.updateVisitCount(idx);  // �ش� �Խù� ��ȸ�� 1 ����
        MVCBoardDTO dto = dao.selectView(idx);
        dao.close();

        // �ٹٲ� ó��
        dto.setContent(dto.getContent().replaceAll("\r\n", "<br/>")); // �۳��뿡 �ִ� ������ HTML������ �ν����� �ʱ� ������ �±��������� ����

        // �Խù�(dto) ���� �� ��� ������
        req.setAttribute("dto", dto); // DTO ��ü�� request������ ����
        req.getRequestDispatcher("/14MVCBoard/View.jsp").forward(req, resp); // ��� ������ �Ѵ�.
    }
}
