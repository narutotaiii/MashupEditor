package mashup;

import java.util.*;

public class AdjacencyList {

	private ArrayList<AdjacencyNode> nodes = new ArrayList<AdjacencyNode>();
	
	public void add( AdjacencyNode node ) {
		nodes.add( node );
	}
	
	public List<AdjacencyNode> getNodes(){
		return nodes;
	}
}
