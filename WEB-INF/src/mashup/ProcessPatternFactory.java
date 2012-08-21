package mashup;

import org.jdom.*;

public class ProcessPatternFactory {
	
	public static ProcessPattern produce( Element element ) {
		
		String type = element.getAttributeValue("type");
		if( type.equals("simple") ) {
			return new Simple(element);
		} else if( type.equals("parallel") ) {
			return new Parallel(element);
		} else if( type.equals("selection") ) {
			return new Selection(element);
		} else if( type.equals("root") ) {
			return new Root(element);
		}
		
		return null;
	}
}
