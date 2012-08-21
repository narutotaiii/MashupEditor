package mashup;

import java.sql.SQLException;
import java.util.*;

public class OverallRecommend {
	
	static String url = "jdbc:mysql://localhost:3306/services?"; 
	static String user = "root";
	static String password = "123456"; 
	static String table = "services";
	static int serviceNameLength = 1;
	
	
	private List<SimpleMashup> mashups;

	public OverallRecommend() throws SQLException, ClassNotFoundException {
		JDBCMashupDatabase database = new JDBCMashupDatabase( url, user, password );
		mashups = database.getSimpleMashups( table );
	}
	
	public List<WeightedSimpleMashup> recommend( String recommendMashup, int n ) {
		
		List<String> users = new SimpleMashup( -1, recommendMashup ).getServices(serviceNameLength);
		Collections.sort( users );
		/// 為了方便取幾個 所以使用Queue
		PriorityQueue< WeightedSimpleMashup > queue = 
			  new PriorityQueue<WeightedSimpleMashup>();
		for( SimpleMashup mashup : mashups ) {
			
			List<String> targets = mashup.getServices(serviceNameLength);
			Collections.sort( targets );	
			
			List<String> same = new ArrayList<String>();
			List<String> userDifferent = new ArrayList<String>();
			List<String> targetDifferent = new ArrayList<String>();
			
			int userIndex = 0, targetIndex = 0;	
			while( userIndex < users.size() && targetIndex < targets.size() ) {
				int result = users.get(userIndex).compareTo( 
						         targets.get(targetIndex) 
						         );
				if( 0 < result ) {
					targetDifferent.add( targets.get(targetIndex++) );
				} else if( result < 0 ) {
					userDifferent.add( users.get(userIndex++) );
				} else {
					same.add( users.get(userIndex) );
					targetIndex++; userIndex++;
				}
			}
			
			userDifferent.addAll( users.subList( userIndex, users.size()) );
			targetDifferent.addAll( targets.subList( targetIndex, targets.size()) );
			
			double finalScore = 0.0;
			for( String user : userDifferent ) {
				double score = 0.0;
				for( String target : targetDifferent ) {
					double newScore = SimilarityScore.get(user, target);
					score = ( score < newScore ? newScore : score );
				}
				finalScore = finalScore + score;
			}
			finalScore = finalScore + (double)same.size();
			
			queue.add( new WeightedSimpleMashup(finalScore/users.size(), mashup) );
		}
				
		List<WeightedSimpleMashup> recommendMashups = new ArrayList<WeightedSimpleMashup>();
		while( 0 < n && 0 < queue.size() ) {
			WeightedSimpleMashup wMashup = queue.poll();
			SimpleMashup mashup = wMashup.getMashup();
			
			List<String> targets = mashup.getServices(serviceNameLength);
			
			Collections.sort( targets );
			if( users.equals(targets) ) continue;
			
			recommendMashups.add( wMashup );
			//System.out.println( wMashup.getMashup().getContent() );
			//System.out.println( wMashup.getWeight());
			--n;
		}
		//System.out.println( "size:" + recommendMashups.size() );
		return recommendMashups;
	}
	
	public static void main( String[] args ) throws SQLException, ClassNotFoundException{
		
		OverallRecommend recommender = new OverallRecommend();
		
		List<WeightedSimpleMashup> mashups = recommender.recommend("ABC", 2);
		
		for( WeightedSimpleMashup mashup : mashups ) {
			System.out.println( mashup.getMashup().getContent() + "/" + mashup.getWeight() );
		}
		
	}
}
