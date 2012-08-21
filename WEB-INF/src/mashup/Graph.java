package mashup;

import java.sql.*;
import java.util.*;
import java.util.Map.Entry;
import java.io.*;
import org.jdom.*;
import org.jdom.output.*;
import org.jdom.input.*;
import org.jdom.xpath.XPath;

public class Graph {

	 static private String url = "jdbc:mysql://localhost:3306/rest?useUnicode=true&amp;zeroDateTimeBehavior=convertToNull&amp;autoReconnect=true&amp;autoReconnectForPools=true";
	
	 private HashMap< String, AdjacencyList > adjacencyMatrix = new HashMap< String, AdjacencyList >();
	 
	 public Graph( String user, String password, String table ) throws ClassNotFoundException, SQLException {
	    
		JDBCMashupDatabase database = new JDBCMashupDatabase( url, user, password );
		List<Mashup> mashups = database.getMashups(table);
		
		List<ServicePair> totalPairs = new ArrayList<ServicePair>();
		for( Mashup mashup : mashups ) {
			List<ServicePair> pairs = ServicePairProducer.produceFrom(mashup);
			totalPairs.addAll( pairs );
		}
		
        Map<ServicePair, Integer> weights = new TreeMap<ServicePair, Integer>();
        for( ServicePair pair : totalPairs ) {
            if( !weights.containsKey(pair) ) {
            	weights.put(pair, new Integer(1) );
            } else {
            	Integer weight = weights.get(pair);
            	weights.remove(pair);
            	weights.put(pair, new Integer(weight + 1) );
            } 
        }
        // remove redundant mashup pair strings
        Set<ServicePair> uniquePairs = new TreeSet<ServicePair>();
        for( ServicePair pair : totalPairs ){
        	uniquePairs.add(pair);
        }
        
        // split pair strings into real class instances
        Iterator it = uniquePairs.iterator();
        while( it.hasNext() ) {
        	ServicePair pair = (ServicePair)it.next();
        	
        	String source = getSource( pair ),
        	       destination = getDestination( pair );
        	
        	if( !adjacencyMatrix.containsKey( source ) ) {
        		adjacencyMatrix.put( source, new AdjacencyList() );
        	} else if( !adjacencyMatrix.containsKey( destination ) ) {
        		adjacencyMatrix.put( destination, new AdjacencyList() );
        	}
        	
        	List<AdjacencyNode> adjacencyList = adjacencyMatrix.get(source).getNodes();
    		adjacencyList.add( new AdjacencyNode(destination,weights.get(pair)) );
        }
	 }
	 
	 private static String getSource( ServicePair pair ) {
		 return pair.getSource();
	 } 
	 
	 private static String getDestination( ServicePair pair ) {
		 return pair.getDestination();
	 }
	
	 public Set<String> getServices() {
		 return adjacencyMatrix.keySet();
	 }
	 
	 public AdjacencyList getAdjacencyList( String name ) {
		 return adjacencyMatrix.get( name );
	 }
	 
	 public static void main( String[] args ) throws ClassNotFoundException, SQLException {
		 
		 Graph graph = new Graph( "root", "123456", "services" );
		 
		 for( Entry<String, AdjacencyList> entry : graph.adjacencyMatrix.entrySet() ){
			 System.out.print( entry.getKey() + " : " );
			 for( AdjacencyNode node : entry.getValue().getNodes() ) {
				 System.out.print( " -> (" + node.destination + "," + node.weight + ")" );
			 }
			 System.out.println();
		 }
	 }
}
