package mashup;

import java.util.ArrayList;
import java.util.List;

import org.jdom.*;

public class Root extends Nested {
	
	public Root( Element element ) {
		super( element, "", "" );
	}
	
	@Override
	public ProcessPatternCategory getCategory() {
		// TODO Auto-generated method stub
		return ProcessPatternCategory.Root;
	}
}
