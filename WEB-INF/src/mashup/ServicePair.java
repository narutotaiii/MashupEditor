package mashup;

public class ServicePair implements Comparable {
	
	private ServicePairCategory category;
	private String source;
	private String destination;
	
	public ServicePair( ServicePairCategory category, String source, String destination ) {
		this.category = category;
		this.source = source;
		this.destination = destination;
	}
	
	public String getSource(){
		return source;
	}
	
	public String getDestination() {
		return destination;
	}
	
	public ServicePairCategory getCategory() {
		return category;
	}

	@Override
	public int compareTo(Object arg0) {
		
		ServicePair other = (ServicePair)arg0;
		
		int result = source.compareTo(other.source);
		
		if( result == 0 )
			return destination.compareTo(other.destination);
		else 
			return result;
	}
}
