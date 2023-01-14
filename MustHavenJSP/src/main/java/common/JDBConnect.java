package common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletContext;

public class JDBConnect {
    public Connection con;
    public Statement stmt;  
    public PreparedStatement psmt;  
    public ResultSet rs;

    // 기본생성자
    public JDBConnect() {
        try {
            // JDBC 드라이버 로드
        	// 클래스명을 통해 직접 객체 생성
            Class.forName("oracle.jdbc.OracleDriver");

            // DB에 연결
            // Release 11.2.0.2.0 Production
            String url = "jdbc:oracle:thin:@localhost:1522:xe";  
            String id = "musthave";
            String pwd = "1111"; 
            con = DriverManager.getConnection(url, id, pwd); 

            System.out.println("DB 연결 성공 - 기본 생성자");
        }
        catch (Exception e) {            
            e.printStackTrace();
        }
    }

    // web.xml 설정
    public JDBConnect(String driver, String url, String id, String pwd) {
        try {
            // JDBC 드라이버 로드
            Class.forName(driver);  
            
            // DB에 연결
            con = DriverManager.getConnection(url, id, pwd);
            
            System.out.println("DB 연결 성공 - web.xml 설정(인수 생성자1)");
        }
        catch (Exception e) {            
            e.printStackTrace();
        }
    }

    // 컨텍스트 초기화 매개변수를 생성자에서 직접 가져올 수 있도록 정의(web.xml)
    public JDBConnect(ServletContext application) {
        try {
            // JDBC 드라이버 로드
            String driver = application.getInitParameter("OracleDriver"); 
            Class.forName(driver); 

            // DB에 연결
            String url = application.getInitParameter("OracleURL"); 
            String id = application.getInitParameter("OracleId");
            String pwd = application.getInitParameter("OraclePwd");
            con = DriverManager.getConnection(url, id, pwd);

            System.out.println("DB 연결 성공 - 컨텍스트 초기화 매개변수 자동입력"); 
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 연결 해제(자원 반납)
    public void close() { 
        try {            
            if (rs != null) rs.close(); 
            if (stmt != null) stmt.close();
            if (psmt != null) psmt.close();
            if (con != null) con.close(); 

            System.out.println("JDBC 자원 해제");
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}