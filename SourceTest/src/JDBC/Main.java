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
			// ����̹� �ε�
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			// DB����
			Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:xe","hr","hr");
			
			// �����غ�
			PreparedStatement psmt=conn.prepareStatement("SELECT * FROM DEPARTMENT");
			
			// ��������
			ResultSet rs = psmt.executeQuery();
			
			// ���� ���� ��� ����
			List<DepartmentDto> empList = new ArrayList<>();			
			while(rs.next()) {
				DepartmentDto emp = new DepartmentDto();
				emp.setDepartment_id(rs.getInt("DEPARTMENT_id"));
				emp.setDepartment_name(rs.getString("DEPARTMENT_name"));
				empList.add(emp);
			}
			
			// ���� �ݱ�
			if(rs!=null) rs.close();
			if(psmt!=null) psmt.close();
			if(conn!=null) conn.close();
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

	}

}
