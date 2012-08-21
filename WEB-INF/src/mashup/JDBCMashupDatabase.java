package mashup;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class JDBCMashupDatabase extends JDBCDatabase {

	public JDBCMashupDatabase( String url, String user, String password )
			throws ClassNotFoundException, SQLException {
		
		super(url, user, password);
		
	}

	public List<Mashup> getMashups( String table ) throws SQLException {
		
		List<Mashup> mashups = new ArrayList<Mashup>();
	
		List< List<String> > lists = selectFrom( table, "*" );
		
		for( List<String> strings : lists ) {
			
			int id = Integer.parseInt( strings.get(0) );
			mashups.add( new Mashup( id, strings.get(1) ) );
		}
		
		return mashups;
	}
	
	public List<SimpleMashup> getSimpleMashups( String table ) throws SQLException {
		
		List<SimpleMashup> mashups = new ArrayList<SimpleMashup>();
	
		List< List<String> > lists = selectFrom( table, "*" );
		
		for( List<String> strings : lists ) {
			
			int id = Integer.parseInt( strings.get(0) );
			
			mashups.add( new SimpleMashup( id, strings.get(1) ) );
		}
		
		return mashups;
	}
	
	/**
	 * @param args
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		//System.out.println( "A)([BC]".replaceAll("[\\]\\[\\(\\)]", "") );
		
		/*
		JDBCMashupDatabase database = new JDBCMashupDatabase( "jdbc:mysql://localhost:3306/services?", 
                                                              "root", "123456" );

		// 印出 database 的內容(Mashup)
		List< Mashup > mashups = database.getMashups( "services" );
		
		for( Mashup mashup : mashups ) {
			
			System.out.println( "id: " + mashup.getId() + ", content: " + mashup.getContent() );
		}
		*/
	}

}
