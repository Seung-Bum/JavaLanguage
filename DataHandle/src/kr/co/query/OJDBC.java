package kr.co.query;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class OJDBC {
	
	private static Connection con;
	private static Statement stmt;
	private static ResultSet rs; //조회한 결과들을 ResultSet에 rs에 저장한다.
	
	public OJDBC() {
		try {
			String OracleUrl = "jdbc:oracle:thin:@localhost:1521:XE";
			String OracleUser = "system";
			String OraclePasswd = "1234";

			con = DriverManager.getConnection(OracleUrl, OracleUser, OraclePasswd);
			System.out.println("DB연결 성공");

			stmt = con.createStatement();
			System.out.println("Statement객체 생성 성공");
			
		} catch (SQLException e) {
			System.out.println("DB연결 실패하거나, SQL문이 틀렸습니다.");
			System.out.print("사유 : " + e.getMessage());
		}
	}
	
	public static ResultSet excute(String stringQuery) throws Exception
	{
		
		rs = stmt.executeQuery(stringQuery); 
		
		rs.close();
		stmt.close();
		con.close();
		
		return rs;
	}
	
}
