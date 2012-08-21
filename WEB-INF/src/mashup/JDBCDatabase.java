package mashup;

import java.sql.*;
import java.util.*;

public class JDBCDatabase {

	static private String driver = "com.mysql.jdbc.Driver";
	
	private Statement statement;
	
	public JDBCDatabase( String url, String user, String password ) throws ClassNotFoundException, SQLException 
	{
		Class.forName(driver); 
		Connection connection = DriverManager.getConnection(url, user, password); 
        statement = connection.createStatement();
	}
	
	public List< List<String> > selectFrom( String table, String column ) throws SQLException {
		
		ResultSet result = statement.executeQuery( "SELECT " + column + " FROM " + table );
		int columnCount = result.getMetaData().getColumnCount();
		
		List< List<String> > lists = new ArrayList< List<String> >();
		
		while( result.next() ) {
			
			List<String> strings = new ArrayList<String>(); 
			for( int i = 0; i < columnCount; ++i ) {
				strings.add( result.getString(i+1) );
			}
			lists.add( strings );
		}
		
		return lists;
	}
	
	/**
	 * @param args
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		JDBCDatabase database = new JDBCDatabase( "jdbc:mysql://localhost:3306/services?", 
				                                  "root", "123456" );
		
		// 印出 database 的內容
		List< List<String> > lists = database.selectFrom( "services", "*" );
		
		for(  List<String> strings : lists ) {
			
			for( String string : strings ) {
				System.out.print( string + " " );
			}
			
			System.out.println();
		}
	}

}
