package fileupload;

import java.util.List;
import java.util.Vector;

import common.DBConnPool;

public class MyfileDAO extends DBConnPool {
	// 새로운 게시물을 입력합니다.
    public int insertFile(MyfileDTO dto) {
        int applyResult = 0;
        try {
            String query = "INSERT INTO myfile ( "
                + " idx, name, title, cate, ofile, sfile) "
                + " VALUES ( "
                + " seq_board_num.nextval, ?, ?, ?, ?, ?)"; 
            
            // con => common.DBConnPool;
            psmt = con.prepareStatement(query);
            psmt.setString(1, dto.getName()); // 물음표에 들어갈 첫번째 값
            psmt.setString(2, dto.getTitle());// 물음표에 들어갈 두번째 값
            psmt.setString(3, dto.getCate()); // 물음표에 들어갈 세번째 값
            psmt.setString(4, dto.getOfile());// ..
            psmt.setString(5, dto.getSfile());
        
            applyResult = psmt.executeUpdate(); //  실행
            // INSERT / DELETE / UPDATE 관련 구문에서는 반영된 레코드의 건수를 반환합니다. 반환값이 1이면 성공 0이면 실패
            // CREATE / DROP 관련 구문에서는 -1 을 반환합니다.
        }
        catch (Exception e) {
            System.out.println("INSERT 증 예외 발생");
            e.printStackTrace();
        }        
        return applyResult;
    }

    // �뙆�씪 紐⑸줉�쓣 諛섑솚�빀�땲�떎.
    public List<MyfileDTO> myFileList() {
        List<MyfileDTO> fileList = new Vector<MyfileDTO>();

        // 荑쇰━臾� �옉�꽦
        String query = "SELECT * FROM myfile ORDER BY idx DESC";
        try {
            psmt = con.prepareStatement(query);  // 荑쇰━ 以�鍮�
            rs = psmt.executeQuery();  // 荑쇰━ �떎�뻾

            while (rs.next()) {  // 紐⑸줉 �븞�쓽 �뙆�씪 �닔留뚰겮 諛섎났
                // DTO�뿉 ���옣
                MyfileDTO dto = new MyfileDTO();
                dto.setIdx(rs.getString(1));
                dto.setName(rs.getString(2));
                dto.setTitle(rs.getString(3));
                dto.setCate(rs.getString(4));
                dto.setOfile(rs.getString(5));
                dto.setSfile(rs.getString(6));
                dto.setPostdate(rs.getString(7));
                
                fileList.add(dto);  // 紐⑸줉�뿉 異붽�
            }
        }
        catch (Exception e) {
            System.out.println("SELECT �떆 �삁�쇅 諛쒖깮");
            e.printStackTrace();
        }        
        
        return fileList;  // 紐⑸줉 諛섑솚
    }
}