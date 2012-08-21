package mashup;

import java.util.ArrayList;
import java.util.List;

import org.jdom.Element;

public abstract class Nested implements ProcessPattern {
	private List<ProcessPattern> patterns = new ArrayList<ProcessPattern>();
	private String start;
	private String end;
	
	public Nested( Element element, String start, String end ) {
		List<Element> children = element.getChildren();
		//System.out.println( children )
		for( Element child : children ) {
			patterns.add( ProcessPatternFactory.produce(child) );
		}
		this.start = start;
		this.end = end;
	}
	
	@Override
	public String getText() {
		StringBuilder builder = new StringBuilder();
		
		for( ProcessPattern pattern : patterns ) {
			builder.append( pattern.getText() );
		}
		
		// TODO Auto-generated method stub
		return start + builder.toString() + end;
	}
}
