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
	
	// 검색 조건에 맞는 게시물의 개수를 반환합니다.
	public int selectCount(Map<String, Object> map) {
		int totalCount = 0;
		
		// 게시물 수를 얻어오는 쿼리문 작성
		String query = "SELECT COUNT(*) FROM board";
		if (map.get("searchWord") != null) {
			query += "WHERE" + map.get("searchField") + " "
					+ "LIKE '%" +map.get("searchWord") + "%'";
		}
		
		try {
			stmt = con.createStatement(); // 쿼리문 생성
			rs = stmt.executeQuery(query);
			rs.next();
			totalCount = rs.getInt(1); // 첫번째 컬럼을 가져옴
		}
		catch (Exception e){
			System.out.println("게시물 수를 구하는 중 예외 발생");
			e.printStackTrace();
		}
		
		return totalCount;
	}
	
	
	// 검색 조건에 맞는 게시물 목록을 반환합니다.
	public List<BoardDTO> selectList(Map<String, Object> map) {
		
		List<BoardDTO> bbs = new Vector<BoardDTO>(); // 게시물 목록 조회 결과를 담을 변수
		// Vector - ArrayList와 비슷함 스레드 환경에서의 안정성은 높지만 ArrayList와 비교하여 추가, 검색, 삭제 성능 떨어짐
		
		String query = "SELECT * FROM board";
		if(map.get("searchWord") != null) {
			query += "WHERE" + map.get("searchField") + " "
					+ " LIKE '%" + map.get("searchWord") + "%' ";
		}
		query += "ORDER BY num DESC";
		
		try {
			stmt = con.createStatement(); // 쿼리 생성
			rs = stmt.executeQuery(query); // 실행, 모든 조회결과가 rs에 담긴다.
			
			while (rs.next()) {// 조회 결과가 없을때까지 반복한다.
				
				BoardDTO dto = new BoardDTO();
				
				// 쿼리에서 결과값을 숫자로 넘겼는지 문자로 넘겼는지 날짜로 넘겼는지에 따라서 get형태 결정
				dto.setNum(rs.getString("num"));
				dto.setTitle(rs.getString("title"));
				dto.setContent(rs.getString("content"));
				dto.setPostdate(rs.getDate("postdate"));
				dto.setId(rs.getString("id"));
				dto.setVisitcount(rs.getString("visitcount"));
				
				bbs.add(dto); // 결과목록에 저장
			}
		}
		catch (Exception e){
			System.out.println("게시물 조회 중 예외 발생");
			e.printStackTrace();
		}
		
		return bbs;
	}
	
}
