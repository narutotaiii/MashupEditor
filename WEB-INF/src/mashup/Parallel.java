package mashup;

import java.util.*;

import org.jdom.*;

public class Parallel extends Nested {
		
	public Parallel( Element element ) {
		super( element, "(", ")" );
	}
	
	@Override
	public ProcessPatternCategory getCategory() {
		// TODO Auto-generated method stub
		return ProcessPatternCategory.Parallel;
	}
}
