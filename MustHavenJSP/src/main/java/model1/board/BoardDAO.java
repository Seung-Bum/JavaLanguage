package model1.board;

import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.servlet.ServletContext;

import common.JDBConnect;

public class BoardDAO extends JDBConnect{
	public BoardDAO(ServletContext application) {
		super(application);
	}
	
	// �˻� ���ǿ� �´� �Խù��� ������ ��ȯ�մϴ�.
	public int selectCount(Map<String, Object> map) {
		int totalCount = 0;
		
		// �Խù� ���� ������ ������ �ۼ�
		String query = "SELECT COUNT(*) FROM board";
		if (map.get("searchWord") != null) {
			query += "WHERE" + map.get("searchField") + " "
					+ "LIKE '%" +map.get("searchWord") + "%'";
		}
		
		try {
			stmt = con.createStatement(); // ������ ����
			rs = stmt.executeQuery(query);
			rs.next();
			totalCount = rs.getInt(1); // ù��° �÷��� ������
		}
		catch (Exception e){
			System.out.println("�Խù� ���� ���ϴ� �� ���� �߻�");
			e.printStackTrace();
		}
		
		return totalCount;
	}
	
	
	// �˻� ���ǿ� �´� �Խù� ����� ��ȯ�մϴ�.
	public List<BoardDTO> selectList(Map<String, Object> map) {
		
		List<BoardDTO> bbs = new Vector<BoardDTO>(); // �Խù� ��� ��ȸ ����� ���� ����
		// Vector - ArrayList�� ����� ������ ȯ�濡���� �������� ������ ArrayList�� ���Ͽ� �߰�, �˻�, ���� ���� ������
		
		String query = "SELECT * FROM board";
		if(map.get("searchWord") != null) {
			query += "WHERE" + map.get("searchField") + " "
					+ " LIKE '%" + map.get("searchWord") + "%' ";
		}
		query += "ORDER BY num DESC";
		
		try {
			stmt = con.createStatement(); // ���� ����
			rs = stmt.executeQuery(query); // ����, ��� ��ȸ����� rs�� ����.
			
			while (rs.next()) {// ��ȸ ����� ���������� �ݺ��Ѵ�.
				
				BoardDTO dto = new BoardDTO();
				
				// �������� ������� ���ڷ� �Ѱ���� ���ڷ� �Ѱ���� ��¥�� �Ѱ������ ���� get���� ����
				dto.setNum(rs.getString("num"));
				dto.setTitle(rs.getString("title"));
				dto.setContent(rs.getString("content"));
				dto.setPostdate(rs.getDate("postdate"));
				dto.setId(rs.getString("id"));
				dto.setVisitcount(rs.getString("visitcount"));
				
				bbs.add(dto); // �����Ͽ� ����
			}
		}
		catch (Exception e){
			System.out.println("�Խù� ��ȸ �� ���� �߻�");
			e.printStackTrace();
		}
		
		return bbs;
	}
	
}