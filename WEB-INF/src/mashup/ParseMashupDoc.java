package mashup;

import org.jdom.output.*;
import org.jdom.input.*;
import org.jdom.*;
import java.sql.*;

import java.io.*;
import java.util.*;

public class ParseMashupDoc {
	
	public static void main(String[] args) throws Exception
    {    
		String driver = "com.mysql.jdbc.Driver"; 
        String url = "jdbc:mysql://localhost:3306/services?"; 
        String user = "root"; 
        String password = "123456";
        Connection conn = null;
        Statement stmt = null;
        
		
		String dirname = "c:\\temp\\test\\"; 
		File f1 = new File(dirname); 
		FilenameFilter only = new OnlyExt("xml"); 
		String s[] = f1.list(only); 
		for (int i=0; i < s.length; i++) 
		{ 
//			System.out.println(s[i]);
	    
			
			SAXBuilder builder = new SAXBuilder();
			Document read_doc = builder.build(new File("c:\\temp\\test\\"+s[i]));
			
			Element MashupDocument = read_doc.getRootElement();
			List MashupDocumentList = MashupDocument.getChildren();
			
	        Element ServiceProcessMashup = (Element) MashupDocumentList.get(0);
	        List ServiceProcessMashupList = ServiceProcessMashup.getChildren();
	        
	        Element ServiceProcess = (Element) ServiceProcessMashupList.get(0);
	        List ServiceProcessList = ServiceProcess.getChildren();
	        
//	        XMLOutputter outputter = new XMLOutputter();
//	        System.out.println(outputter.outputString(ServiceProcess));

			List<ProcessPattern> patterns = new ArrayList<ProcessPattern>();
			for( int j = 0; j < ServiceProcessList.size(); ++j ) {
				ProcessPattern pattern = ProcessPatternFactory.produce( (Element)ServiceProcessList.get(j) );
				patterns.add( pattern );
				

			}
			
			for( ProcessPattern pattern : patterns ) {
				System.out.println( pattern.getText() );
				Class.forName(driver);
				conn = DriverManager.getConnection(url, user, password);
				stmt = conn.createStatement();
				int result = stmt.executeUpdate( "INSERT INTO services (mashup)" +
				"VALUES (\'" + pattern.getText() + "\')" );
			}
		} 
           
    }

}
