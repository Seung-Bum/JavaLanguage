package kr.co.query;

import java.sql.ResultSet;
import java.util.Iterator;

public class Main {

	public static void main(String[] args) throws Exception {
		
		ResultSet rows;
		String selectSong = "bad";
		String query = "";
		
		OJDBC ojdbc = new OJDBC();
		CommonQuery commonquery = new CommonQuery();
		
		query = commonquery.selectQuery(selectSong);
		rows = ojdbc.excute(query);
		
		if (rows.getRow() < 1)
		{
			System.out.println("값이 없습니다.");
		}
		else
		{
			for (int i = 0; i < rows.getRow(); i++) {
				rows.next();
				System.out.print(rows.getString(2)); // getString(2)는 컬럼의 2번째 값을 String형으로 가져온다.
			}
		}
		

	}
}
