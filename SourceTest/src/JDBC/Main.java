package JDBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class Main {

	public static void main(String[] args) {
		
		try {
			// 드라이버 로딩
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			// DB연결
			Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:xe","hr","hr");
			
			// 쿼리준비
			PreparedStatement psmt=conn.prepareStatement("SELECT * FROM DEPARTMENT");
			
			// 쿼리실행
			ResultSet rs = psmt.executeQuery();
			
			// 쿼리 실행 결과 저장
			List<DepartmentDto> empList = new ArrayList<>();			
			while(rs.next()) {
				DepartmentDto emp = new DepartmentDto();
				emp.setDepartment_id(rs.getInt("DEPARTMENT_id"));
				emp.setDepartment_name(rs.getString("DEPARTMENT_name"));
				empList.add(emp);
			}
			
			// 연결 닫기
			if(rs!=null) rs.close();
			if(psmt!=null) psmt.close();
			if(conn!=null) conn.close();
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

	}

}
