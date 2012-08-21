package mashup;

import java.sql.SQLException;
import java.util.*;

public class Group {
	
	private static String url = "jdbc:mysql://localhost:3306/rest?"; 
	private static String user = "root";
	private static String password = "0000"; 
	private static String table = "groups";
	
	private static HashMap<String,Integer> groups;
	
	static {
		
		groups = new HashMap<String,Integer>();
		
		JDBCDatabase database = null;
		
		try {
			database = new JDBCDatabase( url, user, password );
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		List< List<String> > lists = null;
		
		try {
			lists = database.selectFrom( table, "*" );
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		for( List<String> list : lists ) {
			groups.put( list.get(0), 
					    Integer.valueOf(list.get(1)) );
		}/**/
	}
	
	public static boolean inSameGroup( String service1, 
			                           String service2 ) {
		
		return groups.get(service1).equals(  
				 groups.get(service2)
		          );
	} 
	
	public static void main(String[] args) {
		
	}
}
