package kr.co.query;

import java.sql.SQLException;

public class CommonQuery {
	
	public static String selectQuery(String title) throws Exception
	{
		
		StringBuffer query = new StringBuffer();
			
		try {
			query.append("SELECT *     					\n");
			query.append("FROM 	 SONGS 				 	\n");
			query.append("WHERE  TITLE = " + title + "  \n");
		} 
		catch(Exception e)
		{	
			throw e;
		}
		
		return query.toString();
	}
	
}
