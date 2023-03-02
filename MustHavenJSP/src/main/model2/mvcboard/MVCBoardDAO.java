package model2.mvcboard;

import java.util.List;
import java.util.Map;
import java.util.Vector;
import common.DBConnPool;

public class MVCBoardDAO extends DBConnPool {
    public MVCBoardDAO() {
        super();
    }

    // 검색조건에 맞는 게시물의 갯수를 반환.
    public int selectCount(Map<String, Object> map) {
        int totalCount = 0;
        String query = "SELECT COUNT(*) FROM mvcboard";
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
            System.out.println("寃뚯떆臾� 移댁슫�듃 以� �삁�쇅 諛쒖깮");
            e.printStackTrace();
        }

        return totalCount;
    }

    // 寃��깋 議곌굔�뿉 留욌뒗 寃뚯떆臾� 紐⑸줉�쓣 諛섑솚�빀�땲�떎(�럹�씠吏� 湲곕뒫 吏��썝).
    public List<MVCBoardDTO> selectListPage(Map<String,Object> map) {
        List<MVCBoardDTO> board = new Vector<MVCBoardDTO>();
        String query = " "
                     + "SELECT * FROM ( "
                     + "    SELECT Tb.*, ROWNUM rNum FROM ( "
                     + "        SELECT * FROM mvcboard ";

        if (map.get("searchWord") != null)
        {
            query += " WHERE " + map.get("searchField")
                   + " LIKE '%" + map.get("searchWord") + "%' ";
        }

        query += "        ORDER BY idx DESC "
               + "    ) Tb "
               + " ) "
               + " WHERE rNum BETWEEN ? AND ?";

        try {
            psmt = con.prepareStatement(query);
            psmt.setString(1, map.get("start").toString());
            psmt.setString(2, map.get("end").toString());
            rs = psmt.executeQuery();

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

                board.add(dto);
            }
        }
        catch (Exception e) {
            System.out.println("寃뚯떆臾� 議고쉶 以� �삁�쇅 諛쒖깮");
            e.printStackTrace();
        }
        return board;
    }

    // 寃뚯떆湲� �뜲�씠�꽣瑜� 諛쏆븘 DB�뿉 異붽��빀�땲�떎(�뙆�씪 �뾽濡쒕뱶 吏��썝).
    public int insertWrite(MVCBoardDTO dto) {
        int result = 0;
        try {
            String query = "INSERT INTO mvcboard ( "
                         + " idx, name, title, content, ofile, sfile, pass) "
                         + " VALUES ( "
                         + " seq_board_num.NEXTVAL,?,?,?,?,?,?)";
            psmt = con.prepareStatement(query);
            psmt.setString(1, dto.getName());
            psmt.setString(2, dto.getTitle());
            psmt.setString(3, dto.getContent());
            psmt.setString(4, dto.getOfile());
            psmt.setString(5, dto.getSfile());
            psmt.setString(6, dto.getPass());
            result = psmt.executeUpdate();
        }
        catch (Exception e) {
            System.out.println("寃뚯떆臾� �엯�젰 以� �삁�쇅 諛쒖깮");
            e.printStackTrace();
        }
        return result;
    }

    // 二쇱뼱吏� �씪�젴踰덊샇�뿉 �빐�떦�븯�뒗 寃뚯떆臾쇱쓣 DTO�뿉 �떞�븘 諛섑솚�빀�땲�떎.
    public MVCBoardDTO selectView(String idx) {
        MVCBoardDTO dto = new MVCBoardDTO();  // DTO 媛앹껜 �깮�꽦
        String query = "SELECT * FROM mvcboard WHERE idx=?";  // 荑쇰━臾� �뀥�뵆由� 以�鍮�
        try {
            psmt = con.prepareStatement(query);  // 荑쇰━臾� 以�鍮�
            psmt.setString(1, idx);  // �씤�뙆�씪誘명꽣 �꽕�젙
            rs = psmt.executeQuery();  // 荑쇰━臾� �떎�뻾

            if (rs.next()) {  // 寃곌낵瑜� DTO 媛앹껜�뿉 ���옣
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
            System.out.println("寃뚯떆臾� �긽�꽭蹂닿린 以� �삁�쇅 諛쒖깮");
            e.printStackTrace();
        }
        return dto;  // 寃곌낵 諛섑솚
    }

    // 二쇱뼱吏� �씪�젴踰덊샇�뿉 �빐�떦�븯�뒗 寃뚯떆臾쇱쓽 議고쉶�닔瑜� 1 利앷��떆�궢�땲�떎.
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
            System.out.println("寃뚯떆臾� 議고쉶�닔 利앷� 以� �삁�쇅 諛쒖깮");
            e.printStackTrace();
        }
    }

    // �떎�슫濡쒕뱶 �슏�닔瑜� 1 利앷��떆�궢�땲�떎.
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
    // �엯�젰�븳 鍮꾨�踰덊샇媛� 吏��젙�븳 �씪�젴踰덊샇�쓽 寃뚯떆臾쇱쓽 鍮꾨�踰덊샇�� �씪移섑븯�뒗吏� �솗�씤�빀�땲�떎.
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

    // 吏��젙�븳 �씪�젴踰덊샇�쓽 寃뚯떆臾쇱쓣 �궘�젣�빀�땲�떎.
    public int deletePost(String idx) {
        int result = 0;
        try {
            String query = "DELETE FROM mvcboard WHERE idx=?";
            psmt = con.prepareStatement(query);
            psmt.setString(1, idx);
            result = psmt.executeUpdate();
        }
        catch (Exception e) {
            System.out.println("寃뚯떆臾� �궘�젣 以� �삁�쇅 諛쒖깮");
            e.printStackTrace();
        }
        return result;
    }

    // 寃뚯떆湲� �뜲�씠�꽣瑜� 諛쏆븘 DB�뿉 ���옣�릺�뼱 �엳�뜕 �궡�슜�쓣 媛깆떊�빀�땲�떎(�뙆�씪 �뾽濡쒕뱶 吏��썝).
    public int updatePost(MVCBoardDTO dto) {
        int result = 0;
        try {
            // 荑쇰━臾� �뀥�뵆由� 以�鍮�
            String query = "UPDATE mvcboard"
                         + " SET title=?, name=?, content=?, ofile=?, sfile=? "
                         + " WHERE idx=? and pass=?";

            // 荑쇰━臾� 以�鍮�
            psmt = con.prepareStatement(query);
            psmt.setString(1, dto.getTitle());
            psmt.setString(2, dto.getName());
            psmt.setString(3, dto.getContent());
            psmt.setString(4, dto.getOfile());
            psmt.setString(5, dto.getSfile());
            psmt.setString(6, dto.getIdx());
            psmt.setString(7, dto.getPass());

            // 荑쇰━臾� �떎�뻾
            result = psmt.executeUpdate();
        }
        catch (Exception e) {
            System.out.println("寃뚯떆臾� �닔�젙 以� �삁�쇅 諛쒖깮");
            e.printStackTrace();
        }
        return result;
    }
}
