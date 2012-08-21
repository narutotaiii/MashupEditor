package mashup;

import org.jdom.*;

public class Simple implements ProcessPattern {

	String text;
	
	public Simple( Element element ) {
		//System.out.println( "Simple:" + element.getText() );
		this.text = element.getText(); 
	}
	
	@Override
	public ProcessPatternCategory getCategory() {
		// TODO Auto-generated method stub
		return ProcessPatternCategory.Simple;
	}

	@Override
	public String getText() {
		// TODO Auto-generated method stub
		return text;
	}
}
