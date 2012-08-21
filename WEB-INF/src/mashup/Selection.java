package mashup;

import java.util.ArrayList;
import java.util.List;

import org.jdom.Element;

public class Selection extends Nested {
	
	public Selection( Element element ) {
		super( element, "[", "]" );
	}
	
	@Override
	public ProcessPatternCategory getCategory() {
		// TODO Auto-generated method stub
		return ProcessPatternCategory.Selection;
	}
}
