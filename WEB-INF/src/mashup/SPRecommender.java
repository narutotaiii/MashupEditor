package mashup;

import java.sql.SQLException;
import java.util.*;

public class SPRecommender {

	static String url = "jdbc:mysql://localhost:3306/services?"; 
	static String user = "root";
	static String password = "123456"; 
	static String table = "services";
	
	private List<Mashup> mashups;
	private CombinationCategory category;
	
	public SPRecommender( CombinationCategory category ) throws ClassNotFoundException, SQLException {
		JDBCMashupDatabase database = new JDBCMashupDatabase( url, user, password );
		mashups = database.getMashups( table );
		this.category = category;
	}
	
	private class ServiceRankEntry implements Comparable {

		public String service;
		public int rank;
		
		public ServiceRankEntry( String service, int rank ) {
			this.service = service;
			this.rank = rank;
		}
		
		@Override
		public int compareTo(Object arg0) {
			ServiceRankEntry entry = (ServiceRankEntry)arg0;
			return entry.rank - rank;
		}
	}
	
	public List<String> recommend( String service, int n ) {
		
		List<Combination> totalCombinations = new ArrayList<Combination>();
		
		for( Mashup mashup : mashups ) {
			List<Combination> combinations = mashup.getCombinations( category );
			
			totalCombinations.addAll( combinations );
			
	//		印出所有selection內容
			for( Combination c : combinations ) {
				System.out.println( c.getContent() );
			}
			
		}
		
		List<Combination> options = new ArrayList<Combination>();
		
		Map<String, Integer> serviceCounts = new HashMap<String, Integer>();
		for( Combination combination : totalCombinations ) {
			
			List<String> services = combination.getServices( service.length() );
	
			int found = services.indexOf(service);
			
			if( found == -1 ) continue;
			
			options.add( combination );
			
			for( int i = 0; i < services.size(); ++i ) {
				if( found == i ) continue;
				
				if( !serviceCounts.containsKey( services.get(i) ) ) {
					serviceCounts.put( services.get(i), new Integer(1) );
	            } else {
	            	Integer count = serviceCounts.get( services.get(i) );
	            	serviceCounts.remove( services.get(i) );
	            	serviceCounts.put( services.get(i), new Integer(count + 1) );
	            }
			}
		}		
		
		PriorityQueue<ServiceRankEntry> rankEntries = new PriorityQueue<ServiceRankEntry>();
		for( Map.Entry<String, Integer> entry : serviceCounts.entrySet() ) {
			rankEntries.add( new ServiceRankEntry(entry.getKey(), entry.getValue() ) );
		}
		
		List<String> services = new ArrayList<String>();
		for( ; 0 < n && 0 < rankEntries.size(); n-- ) {
			ServiceRankEntry rankEntry = rankEntries.poll();
			services.add( rankEntry.service );
		}
		
		return services;
	}
	
	/**
	 * @param args
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		SPRecommender recommender = new SPRecommender( CombinationCategory.Selection );
//		SPRecommender recommender = new SPRecommender( CombinationCategory.Parallel );
		
		List<String> services = recommender.recommend( "B", 2 );

		for( String service : services ) {
			System.out.println( service );
			

		}
		
	}

}
