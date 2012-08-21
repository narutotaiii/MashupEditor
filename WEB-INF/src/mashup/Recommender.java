package mashup;

import java.sql.SQLException;
import java.util.*;

public class Recommender {

	private Graph graph = null;
	private boolean visited[] = null;
	private ArrayList<VisitInfo> visitInfos = new ArrayList<VisitInfo>();
	private HashMap<String, Integer> serviceIndices = new HashMap<String, Integer>();
	
	
	
	public Recommender( String user, String password, String table ) throws ClassNotFoundException, SQLException {
		graph = new Graph( user, password, table );
		
		Set<String> services = graph.getServices();
		Iterator service = services.iterator();
		int index = 0;
		while( service.hasNext() ) {
			serviceIndices.put( (String)service.next(), new Integer(index++) );
		}
	}
	
	public void visitGraph( String source, int weight, int initStep, int endStep ) {
		
		if( initStep == endStep ) return; 
		
		int sourceIndex = serviceIndices.get(source);
		
		AdjacencyList adjacencyList = graph.getAdjacencyList(source);
		for( AdjacencyNode node : adjacencyList.getNodes() ) {
			
			String destination = node.destination;
			if( visited[ serviceIndices.get(destination) ] ) continue;
			
			visited[ sourceIndex ] = true;
			
			visitInfos.add( new VisitInfo( destination, weight+node.weight, initStep+1) );
			visitGraph( destination, weight+node.weight, initStep+1, endStep );
			
			visited[ sourceIndex ] = false;
		}
	}
	
	public Set<String> recommend( String name, int n ) {
		
		visited = new boolean[ serviceIndices.size() ];
		visitInfos.clear();
		//尋訪graph(service:終點,weight,起始步數,最後步數)
		visitGraph( name, 0, 0, 2 );
		
		for( VisitInfo info : visitInfos ) {
			info.weight /= info.step;
		}
		
		int[] recommendScores = new int[ serviceIndices.size() ];
		for( VisitInfo info : visitInfos ) {
			int score = info.weight;
			int destIndex = serviceIndices.get(info.destination);
			if( recommendScores[ destIndex ] < score )
				recommendScores[ destIndex ] = score;
		}
		
		PriorityQueue<RecommendCandidate> candidates = new PriorityQueue<RecommendCandidate>();  
		Set<String> services = graph.getServices();
		Iterator it = services.iterator();
		while( it.hasNext() ) {
			String service = (String)it.next();
			int serviceIndex = serviceIndices.get(service);
			
			if( recommendScores[ serviceIndex ] == 0 ) 
				continue;
			
			candidates.add( new RecommendCandidate( service, recommendScores[serviceIndex] ) );
		}
		
		
		Set<String> recommendServices = new TreeSet<String>();
		for( ; 0 < n && 0 < candidates.size(); --n ) {
			RecommendCandidate candidate = candidates.poll();
			recommendServices.add( candidate.service );
		}
		
		return recommendServices;
	}

	/**
	 * @param args
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Recommender recommender = null;
		try {
			recommender = new Recommender( "root", "123456", "services" );
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Set<String> services = recommender.recommend( "C", 2 );
		Iterator it = services.iterator();
		while( it.hasNext() ) {
			System.out.println( (String)it.next() );
		}
	}
}
