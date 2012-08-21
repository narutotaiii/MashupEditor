package mashup;

import java.util.*;

public class ServicePairProducer {

	static final int serviceNameLength = 1;
	
	public static List<ServicePair> produceFrom( Mashup mashup ) {
		
		String content = mashup.getContent();
		
		List<ServicePair> pairs = new ArrayList<ServicePair>();
		
		Set<String> previousServices = null;
		
		ServicePairCategory category = ServicePairCategory.Normal;
		int current = 0; 
		while( current < content.length() ) {
			
			Set<String> currentServices = new TreeSet<String>();
			
			if( content.charAt(current) == '[' ) {
				category = ServicePairCategory.Selection;
				for( ++current; content.charAt(current) != ']'; current += serviceNameLength ) {
					currentServices.add( getService(content, current) );
				}
				++current;
			} else if( content.charAt(current) == '(' ) {
				category = ServicePairCategory.Parallel;
				for( ++current; content.charAt(current) != ')'; current += serviceNameLength ) {
					currentServices.add( getService(content, current) );
				}
				++current;
			} else {
				currentServices.add( getService(content, current) );
				current += serviceNameLength;
			}
			
			if( previousServices != null ) {
				
				if( previousServices.size() == 1 && currentServices.size() == 1 )
					category = ServicePairCategory.Normal;
				
				for( String source : previousServices ) {
					for( String destination : currentServices )
						pairs.add( new ServicePair( category, source, destination ) );
				}
			}
			
			previousServices = currentServices;
		}
		
		return pairs;
	}
	
	// 取的服務擴充字元，ex:A1 為字元2  目前為A 字元1
	private static String getService( String str, int startIndex ) { 
		
		return str.substring( startIndex, startIndex + serviceNameLength );
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		List<ServicePair> pairs = produceFrom( new Mashup(999, "A[BC]D(EF)GH") );
		
		for( ServicePair pair : pairs ) {
			
			System.out.println( "category: " + pair.getCategory() + ", src: " + pair.getSource() 
					            + ", dest: " + pair.getDestination() );
		}
		
	}

}
