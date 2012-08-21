package mashup;

import org.jdom.output.*;
import org.jdom.input.*;
import org.jdom.*;

import java.io.*;
import java.util.*;

public class parseTestMashupDoc {
	
	public static void main(String[] args) throws Exception
    {     
		String dirname = "c:\\temp\\test\\test\\"; 
		File f1 = new File(dirname); 
		FilenameFilter only = new OnlyExt("xml"); 
		String s[] = f1.list(only);
		//System.out.println( s.length );
		for (int i=0; i < s.length; i++) 
		{ 
//			System.out.println(s[i]);
			
			SAXBuilder builder = new SAXBuilder();
			Document read_doc = builder.build(new File("c:\\temp\\test\\test\\"+s[i]));
			
			Element root = read_doc.getRootElement();
			List children = root.getChildren();
			
			
			List<ProcessPattern> patterns = new ArrayList<ProcessPattern>();
			for( int j = 0; j < children.size(); ++j ) {
				ProcessPattern pattern = ProcessPatternFactory.produce( (Element)children.get(j) );
				patterns.add( pattern );
			}
			
			for( ProcessPattern pattern : patterns ) {
				System.out.println( pattern.getText() );
			}
			
		} 
		 
		             
    }

}
