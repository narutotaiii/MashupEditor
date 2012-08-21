package mashup;

public class SimilarityScore {
	
	static double get( String service1,
			           String service2 ) {
		
		if( service1.equals(service2) )
			return -1;
		
		if( Group.inSameGroup(service1, service2) )
			return 0.5;
		
		return 0.0;
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
	}

}
