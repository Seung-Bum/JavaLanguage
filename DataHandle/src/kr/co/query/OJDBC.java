package kr.co.query;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class OJDBC {
	
	private static Connection con;
	private static Statement stmt;
	private static ResultSet rs; //��ȸ�� ������� ResultSet�� rs�� �����Ѵ�.
	
	public OJDBC() {
		try {
			String OracleUrl = "jdbc:oracle:thin:@localhost:1521:XE";
			String OracleUser = "system";
			String OraclePasswd = "1234";

			con = DriverManager.getConnection(OracleUrl, OracleUser, OraclePasswd);
			System.out.println("DB���� ����");

			stmt = con.createStatement();
			System.out.println("Statement��ü ���� ����");
			
		} catch (SQLException e) {
			System.out.println("DB���� �����ϰų�, SQL���� Ʋ�Ƚ��ϴ�.");
			System.out.print("���� : " + e.getMessage());
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
