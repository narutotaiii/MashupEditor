package mashup;

import java.util.ArrayList;
import java.util.List;

public class SimpleMashup {

	private int id;
	private String content;
	
	public SimpleMashup( int id, String content ) {
		this.id = id;
		this.content = content.replaceAll("[\\(\\)\\[\\]]", ""); 
	}
	
	public String getContent() {
		return content;
	}
	
	public int getLength( int serviceNameLength ) {
		return content.length() / serviceNameLength;
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
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
