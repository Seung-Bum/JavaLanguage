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

    // 파일목록을 반환합니다.
    public List<MyfileDTO> myFileList() {
        List<MyfileDTO> fileList = new Vector<MyfileDTO>();

        // 쿼리문작성
        String query = "SELECT * FROM myfile ORDER BY idx DESC";
        try {
            psmt = con.prepareStatement(query);  // 쿼리준비
            rs = psmt.executeQuery();  // 쿼리실행

            while (rs.next()) {  // 목록 안의 파일 수만큼 반복
                // DTO에 저장

                MyfileDTO dto = new MyfileDTO();
                dto.setIdx(rs.getString(1)); // 쿼리의 결과값에 제일 첫번째 컬럼 ..
                dto.setName(rs.getString(2));
                dto.setTitle(rs.getString(3));
                dto.setCate(rs.getString(4));
                dto.setOfile(rs.getString(5));
                dto.setSfile(rs.getString(6));
                dto.setPostdate(rs.getString(7));
                
                fileList.add(dto);  // 목록에 추가
            }
        }        
        catch (Exception e) {
            System.out.println("SELECT 시 예외 발생");
            e.printStackTrace();
        }        
        
        return fileList;  // 목록 반환
    }
}