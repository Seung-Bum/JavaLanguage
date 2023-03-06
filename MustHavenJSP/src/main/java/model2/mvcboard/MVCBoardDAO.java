package model2.mvcboard;

import java.util.List;
import java.util.Map;
import java.util.Vector;
import common.DBConnPool;

public class MVCBoardDAO extends DBConnPool {// 커넥션풀 상속
    public MVCBoardDAO() { 	
        super();
    }

    // 검색 조건에 맞는 게시물의 개수를 반환합니다.
    public int selectCount(Map<String, Object> map) {

        int totalCount = 0;
        String query = "SELECT COUNT(*) FROM mvcboard";
        
        // 검색 조건이 있다면 where 절로 추가
        if (map.get("searchWord") != null) {
            query += " WHERE " + map.get("searchField") + " "
                   + " LIKE '%" + map.get("searchWord") + "%'";
        }
        try {
            stmt = con.createStatement();
            rs = stmt.executeQuery(query);
            rs.next();
            totalCount = rs.getInt(1);
        }
        catch (Exception e) {
            System.out.println("게시물 카운트 중 예외 발생");
            e.printStackTrace();
        }

        return totalCount;
    }

    // 검색 조건에 맞는 게시물 목록을 반환합니다.(페이징 기능 지원).
    public List<MVCBoardDTO> selectListPage(Map<String,Object> map) {
        List<MVCBoardDTO> board = new Vector<MVCBoardDTO>();           
        String query = " "
                     + "SELECT * FROM ( "
                     + "    SELECT Tb.*, ROWNUM rNum FROM ( "
                     + "        SELECT * FROM mvcboard ";

        if (map.get("searchWord") != null)
        {
            query += " WHERE " + map.get("searchField") // 제목, 내용 옵션
                   + " LIKE '%" + map.get("searchWord") + "%' ";
        }

	        query += "        ORDER BY idx DESC "
	               + "    ) Tb "
	               + " ) "
	               + " WHERE rNum BETWEEN ? AND ?"; // 게시물 구간은 인파라미터로

        try {
            psmt = con.prepareStatement(query); // 동적 쿼리문 생성
            psmt.setString(1, map.get("start").toString()); // 인파라미터 설정
            psmt.setString(2, map.get("end").toString());
            rs = psmt.executeQuery();
            
            // 게시물 목록 내용하나씩 꺼내서 dto에 담는다.
            while (rs.next()) {
                MVCBoardDTO dto = new MVCBoardDTO();
                
                dto.setIdx(rs.getString(1));
                dto.setName(rs.getString(2));
                dto.setTitle(rs.getString(3));
                dto.setContent(rs.getString(4));
                dto.setPostdate(rs.getDate(5));
                dto.setOfile(rs.getString(6));
                dto.setSfile(rs.getString(7));
                dto.setDowncount(rs.getInt(8));
                dto.setPass(rs.getString(9));
                dto.setVisitcount(rs.getInt(10));

                board.add(dto); // 반환된 게시물 목록을 List 컬렉션에 추가
            }
        }
        catch (Exception e) {
            System.out.println("게시물 목록 반환중 예외발생");
            e.printStackTrace();
        }
        return board; // 목록반환
    }

    // 게시글 데이터를 받아 DB에 추가합니다.(파일 업로드 지원).
    public int insertWrite(MVCBoardDTO dto) {
        int result = 0;
        try {
            String query = "INSERT INTO mvcboard ( "
                         + " idx, name, title, content, ofile, sfile, pass) "
                         + " VALUES ( "
                         + " seq_board_num.NEXTVAL,?,?,?,?,?,?)"; // 인파라미터 설정
            psmt = con.prepareStatement(query);
            psmt.setString(1, dto.getName());  // 첫번째 인파라미터
            psmt.setString(2, dto.getTitle());
            psmt.setString(3, dto.getContent());
            psmt.setString(4, dto.getOfile());
            psmt.setString(5, dto.getSfile());
            psmt.setString(6, dto.getPass());
            result = psmt.executeUpdate();
        }
        catch (Exception e) {
            System.out.println("게시물 입력중 예외 발생");
            e.printStackTrace();
        }
        return result;
    }

    // 雅뚯눘堉깍쭪占� 占쎌뵬占쎌졃甕곕뜇�깈占쎈퓠 占쎈퉸占쎈뼣占쎈릭占쎈뮉 野껊슣�뻻�눧�눘�뱽 DTO占쎈퓠 占쎈뼖占쎈툡 獄쏆꼹�넎占쎈�占쎈빍占쎈뼄.
    public MVCBoardDTO selectView(String idx) {
        MVCBoardDTO dto = new MVCBoardDTO();  // DTO 揶쏆빘猿� 占쎄문占쎄쉐
        String query = "SELECT * FROM mvcboard WHERE idx=?";  // �뜎�눖�봺�눧占� 占쎈�ο옙逾녺뵳占� 餓ο옙�뜮占�
        try {
            psmt = con.prepareStatement(query);  // �뜎�눖�봺�눧占� 餓ο옙�뜮占�
            psmt.setString(1, idx);  // 占쎌뵥占쎈솁占쎌뵬沃섎챸苑� 占쎄퐬占쎌젟
            rs = psmt.executeQuery();  // �뜎�눖�봺�눧占� 占쎈뼄占쎈뻬

            if (rs.next()) {  // 野껉퀗�궢�몴占� DTO 揶쏆빘猿쒙옙肉� 占쏙옙占쎌삢
                dto.setIdx(rs.getString(1));
                dto.setName(rs.getString(2));
                dto.setTitle(rs.getString(3));
                dto.setContent(rs.getString(4));
                dto.setPostdate(rs.getDate(5));
                dto.setOfile(rs.getString(6));
                dto.setSfile(rs.getString(7));
                dto.setDowncount(rs.getInt(8));
                dto.setPass(rs.getString(9));
                dto.setVisitcount(rs.getInt(10));
            }
        }
        catch (Exception e) {
            System.out.println("野껊슣�뻻�눧占� 占쎄맒占쎄쉭癰귣떯由� 餓ο옙 占쎌굙占쎌뇚 獄쏆뮇源�");
            e.printStackTrace();
        }
        return dto;  // 野껉퀗�궢 獄쏆꼹�넎
    }

    // 雅뚯눘堉깍쭪占� 占쎌뵬占쎌졃甕곕뜇�깈占쎈퓠 占쎈퉸占쎈뼣占쎈릭占쎈뮉 野껊슣�뻻�눧�눘�벥 鈺곌퀬�돳占쎈땾�몴占� 1 筌앹빓占쏙옙�뻻占쎄땁占쎈빍占쎈뼄.
    public void updateVisitCount(String idx) {
        String query = "UPDATE mvcboard SET "
                     + " visitcount=visitcount+1 "
                     + " WHERE idx=?"; 
        try {
            psmt = con.prepareStatement(query);
            psmt.setString(1, idx);
            psmt.executeQuery();
        }
        catch (Exception e) {
            System.out.println("野껊슣�뻻�눧占� 鈺곌퀬�돳占쎈땾 筌앹빓占� 餓ο옙 占쎌굙占쎌뇚 獄쏆뮇源�");
            e.printStackTrace();
        }
    }

    // 占쎈뼄占쎌뒲嚥≪뮆諭� 占쎌뒒占쎈땾�몴占� 1 筌앹빓占쏙옙�뻻占쎄땁占쎈빍占쎈뼄.
    public void downCountPlus(String idx) {
        String sql = "UPDATE mvcboard SET "
                + " downcount=downcount+1 "
                + " WHERE idx=? "; 
        try {
            psmt = con.prepareStatement(sql);
            psmt.setString(1, idx);
            psmt.executeUpdate();
        }
        catch (Exception e) {}
    }
    // 占쎌뿯占쎌젾占쎈립 �뜮袁⑨옙甕곕뜇�깈揶쏉옙 筌욑옙占쎌젟占쎈립 占쎌뵬占쎌졃甕곕뜇�깈占쎌벥 野껊슣�뻻�눧�눘�벥 �뜮袁⑨옙甕곕뜇�깈占쏙옙 占쎌뵬燁살꼹釉�占쎈뮉筌욑옙 占쎌넇占쎌뵥占쎈�占쎈빍占쎈뼄.
    public boolean confirmPassword(String pass, String idx) {
        boolean isCorr = true;
        try {
            String sql = "SELECT COUNT(*) FROM mvcboard WHERE pass=? AND idx=?";
            psmt = con.prepareStatement(sql);
            psmt.setString(1, pass);
            psmt.setString(2, idx);
            rs = psmt.executeQuery();
            rs.next();
            if (rs.getInt(1) == 0) {
                isCorr = false;
            }
        }
        catch (Exception e) {
            isCorr = false;
            e.printStackTrace();
        }
        return isCorr;
    }

    // 筌욑옙占쎌젟占쎈립 占쎌뵬占쎌졃甕곕뜇�깈占쎌벥 野껊슣�뻻�눧�눘�뱽 占쎄텣占쎌젫占쎈�占쎈빍占쎈뼄.
    public int deletePost(String idx) {
        int result = 0;
        try {
            String query = "DELETE FROM mvcboard WHERE idx=?";
            psmt = con.prepareStatement(query);
            psmt.setString(1, idx);
            result = psmt.executeUpdate();
        }
        catch (Exception e) {
            System.out.println("野껊슣�뻻�눧占� 占쎄텣占쎌젫 餓ο옙 占쎌굙占쎌뇚 獄쏆뮇源�");
            e.printStackTrace();
        }
        return result;
    }

    // 野껊슣�뻻疫뀐옙 占쎈쑓占쎌뵠占쎄숲�몴占� 獄쏆룇釉� DB占쎈퓠 占쏙옙占쎌삢占쎈┷占쎈선 占쎌뿳占쎈쐲 占쎄땀占쎌뒠占쎌뱽 揶쏄퉮�뻿占쎈�占쎈빍占쎈뼄(占쎈솁占쎌뵬 占쎈씜嚥≪뮆諭� 筌욑옙占쎌뜚).
    public int updatePost(MVCBoardDTO dto) {
        int result = 0;
        try {
            // �뜎�눖�봺�눧占� 占쎈�ο옙逾녺뵳占� 餓ο옙�뜮占�
            String query = "UPDATE mvcboard"
                         + " SET title=?, name=?, content=?, ofile=?, sfile=? "
                         + " WHERE idx=? and pass=?";

            // �뜎�눖�봺�눧占� 餓ο옙�뜮占�
            psmt = con.prepareStatement(query);
            psmt.setString(1, dto.getTitle());
            psmt.setString(2, dto.getName());
            psmt.setString(3, dto.getContent());
            psmt.setString(4, dto.getOfile());
            psmt.setString(5, dto.getSfile());
            psmt.setString(6, dto.getIdx());
            psmt.setString(7, dto.getPass());

            // �뜎�눖�봺�눧占� 占쎈뼄占쎈뻬
            result = psmt.executeUpdate();
        }
        catch (Exception e) {
            System.out.println("野껊슣�뻻�눧占� 占쎈땾占쎌젟 餓ο옙 占쎌굙占쎌뇚 獄쏆뮇源�");
            e.printStackTrace();
        }
        return result;
    }
}
