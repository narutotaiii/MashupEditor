package mashup;

import java.util.*;

public class Combination {
	
	private CombinationCategory category;
	private String content;
	
	public Combination( CombinationCategory category, String content ) {
		this.category = category;
		this.content = content; 
	}
	
	public CombinationCategory getCategory() {
		return category;
	} 
	
	public String getContent() {
		return content;
	}
	
	public boolean hasService( String service ) {
		
		List<String> services = getServices( service.length() );
		
		return services.indexOf( service ) != -1;
	}
	
	public List<String> getServices( int serviceNameLength ) {
		
		List<String> services = new ArrayList<String>();
		
		for( int current = 0; current < content.length(); current += serviceNameLength ){
			services.add( content.substring(current, current + serviceNameLength) );
		}
		
		return services;
	}
}
