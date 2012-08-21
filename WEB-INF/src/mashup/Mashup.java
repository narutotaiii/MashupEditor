package mashup;

import java.util.*;

public class Mashup {
	
	private int id;
	private String content;
	
	public Mashup( int id, String content ) {
		this.id = id;
		this.content = content;
	}
	
	public int getId() {
		return id;
	}
	
	public String getContent() {
		return content;
	}
	
	public List<Combination> getCombinations( CombinationCategory category ) {
		
		char start = ' ', end = ' ';
		switch( category ) {
		case Selection:
			start = '['; end = ']';
			break;
		case Parallel:
			start = '('; end = ')';
			break;
		}
		
		List<Combination> combinations = new ArrayList<Combination>();
		
		int current = 0;
		int stringBegin = 0, stringEnd = 0;
		while( true ){
			
			while( current < content.length() && content.charAt(current) != start )
				++current;
			
			stringBegin = current + 1;
			
			while( current < content.length() && content.charAt(current) != end )
				++current;
			
			stringEnd = current;
			
			if( current == content.length() ) 
				break;
			
			combinations.add( new Combination(category,  
					                          content.substring(stringBegin, stringEnd) ) );
		}
		
		return combinations;
	}
	
	public static void main( String[] args ) {
		
		Mashup m = new Mashup( 999, "A[BCD]" );
		
		
		List<Combination> combinations = m.getCombinations(CombinationCategory.Selection);
		for( Combination c : combinations ) {
			System.out.println( c.getContent() );
		}
	}
}
