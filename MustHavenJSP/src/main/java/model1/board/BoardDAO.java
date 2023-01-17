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
		
        String query = "SELECT * FROM board "; 
        if (map.get("searchWord") != null) { // 사용자가 검색한 내용이 있으면 아래로 내려감
            query += " WHERE " + map.get("searchField") + " "
                   + " LIKE '%" + map.get("searchWord") + "%' ";
        }
        query += " ORDER BY num DESC "; 
		
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
	
	// 검색 조건에 맞는 게시물 목록을 반환합니다.(페이징 기능 지원)
	public List<BoardDTO> selectListPage(Map<String, Object> map) {
	        List<BoardDTO> bbs = new Vector<BoardDTO>();  // 결과(게시물 목록)를 담을 변수
	        
	        // 쿼리문 템플릿
	        String query = " SELECT * FROM ( "
	                     + "    SELECT Tb.*, ROWNUM rNum FROM ( "
	                     + "        SELECT * FROM board ";

	        // 검색 조건 추가
	        if (map.get("searchWord") != null) {// 검색이 있는가?
	            query += " WHERE " + map.get("searchField") // 제목, 내용
	                   + " LIKE '%" + map.get("searchWord") + "%' ";
	        }
	        
	        query += "      ORDER BY num DESC "
	               + "     ) Tb "
	               + " ) "
	               + " WHERE rNum BETWEEN ? AND ?"; 

	        try {
	            // 쿼리문 완성
	            psmt = con.prepareStatement(query);
	            psmt.setString(1, map.get("start").toString()); // 페이지 번호시작
	            psmt.setString(2, map.get("end").toString());	// 페이지 번호끝
	            
	            // 쿼리문 실행
	            rs = psmt.executeQuery();
	            
	            while (rs.next()) { // 위 쿼리에서 가져온 10행의 데이터
	                // 한 행(게시물 하나)의 데이터를 DTO에 저장
	                BoardDTO dto = new BoardDTO();
	                dto.setNum(rs.getString("num"));
	                dto.setTitle(rs.getString("title"));
	                dto.setContent(rs.getString("content"));
	                dto.setPostdate(rs.getDate("postdate"));
	                dto.setId(rs.getString("id"));
	                dto.setVisitcount(rs.getString("visitcount"));

	                // 반환할 결과 목록에 게시물 추가 (rs가 끝날때 까지 추가)
	                bbs.add(dto);
	            }
	        } 
	        catch (Exception e) {
	            System.out.println("게시물 조회 중 예외 발생");
	            e.printStackTrace();
	        }
	        
	        // 목록 반환
	        return bbs;
	}
	
	public int insertWrite(BoardDTO dto) {
		int result = 0;
		
		try {
			// INSERT 쿼리문 작성
			String query = "INSERT INTO board ("
						 + "num,title,content,id,visitcount)"
						 + "VALUES ("
						 + "seq_board_num.NEXTVAL, ?, ?, ?, 0)"; // NEXTVAL 해당 시퀀스의 값을 증가 시킴
			
			psmt = con.prepareStatement(query); // extends JDBConnect 가 되어있어서 con 사용가능 생성자로 연결해놓음
			psmt.setString(1, dto.getTitle());	// psmt의 setString으로 insert할 dto 값인 Title이 첫번째 ?(인파라미터)에 입력된다.
			psmt.setString(2, dto.getContent());// psmt의 setString으로 insert할 dto 값인 Content가 두번째 ?에 입력된다.
			psmt.setString(3, dto.getId());		// psmt의 setString으로 insert할 dto 값인 Id가 세번째 ?에 입력된다.
			
			result = psmt.executeUpdate(); // ★★INSERT 쿼리를 성공한 행의 개수를 리턴한다.
		} 
		catch (Exception e){
			System.out.println("게시물 입력 중 예외 발생");
			e.printStackTrace();
		}
		
		return result;
	}
	
	public BoardDTO selectView(String num) {
		BoardDTO dto = new BoardDTO();

		// 쿼리문 준비
		String query = "SELECT B.*, M.name "
					 + " FROM member M INNER JOIN board B "
					 + " ON M.id = B.id "
					 + " WHERE num=?";
		
		try {
			psmt = con.prepareStatement(query); // extends JDBConnect DB연결
			psmt.setString(1, num); // 인파라미터를 일련번호로 설정
			rs = psmt.executeQuery(); // 쿼리실행
			
			// 결과처리
			if (rs.next()) {
				dto.setNum(rs.getString(1));
				dto.setTitle(rs.getString(2));
				dto.setContent(rs.getString("content"));
				dto.setPostdate(rs.getDate("postdate"));
				dto.setId(rs.getString("id"));
				dto.setVisitcount(rs.getString(6));
				dto.setName(rs.getString("name"));				
			}
		} 
		catch (Exception e) {
			System.out.println("게시물 상세보기 중 예외 발생");
			e.printStackTrace();
		}
		
		return dto;
	}
	
	// 지정한 게수물의 조회수를 1 증가시킵니다.
	public void updateVisitCount(String num) {
		// 쿼리문 준비
		String query = "UPDATE board "
					 + "SET visitcount=visitcount + 1"
					 + "WHERE num = ?";
		
		try {
			psmt = con.prepareStatement(query); // DB연결 query 입력 psmt 생성
			psmt.setString(1, num);	// 인파라미터를 일련번호로 설
			psmt.executeQuery(); // 쿼리 실행
		} 
		catch (Exception e) {
			System.out.println("게시물 조회수 증가 중 예외 발생");
			e.printStackTrace();
		}
	}
	
	// 지정한 게시물을 수정합니다.
	public int updateEdit(BoardDTO dto) {
		int result = 0;

		try {
			// 쿼리문 템플릿 (띄어쓰기 제대로 안하면 SQL 오류발생함)
			String query = "UPDATE board "
						 + "SET title=?, content=?"
						 + "WHERE num=?";
			// 쿼리문 완성
			psmt = con.prepareStatement(query);
			psmt.setString(1, dto.getTitle());
			psmt.setString(2, dto.getContent());
			psmt.setString(3, dto.getNum());
			
			// 쿼리문 실행
			result = psmt.executeUpdate();
		} 
		catch (Exception e) {
			System.out.println("게시물 수정 중 예외 발생");
			e.printStackTrace();
		}
		
		return result;
	}
	
	// 지정한 게시물을 삭제합니다.
	public int deletePost(BoardDTO dto) {
		int result = 0;
		
		try {
			// 쿼리문 템플릿
			String query = "DELETE FROM board WHERE num = ?";
			
			// 쿼리문 완성
			psmt = con.prepareStatement(query);
			psmt.setString(1, dto.getNum()); //인파라미터 설정 (?)
			
			// 쿼리문 실행
			result = psmt.executeUpdate();
			
		} 
		catch (Exception e) {
			System.out.println("게시물 삭제 중 예외 발생");
			e.printStackTrace();
		}
		
		return result;
	}
}
