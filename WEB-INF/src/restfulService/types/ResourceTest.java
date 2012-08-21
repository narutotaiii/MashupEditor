package restfulService.types;

import java.sql.*;
import restfulService.editor.*;
public class ResourceTest {
	
	public static void main(String args[])
	{
		
		String address = "jdbc:mysql://localhost:3306/rest";
		String user= "root";
		String password = "0000";
		String driver = "com.mysql.jdbc.Driver";
		String query = "{queryKey:'flight'}";
		String query2 = "[{resourceID:\"1\"}]";
		Connection conn = null;
		ResourceOrder order = null;
		//ServiceProcess run = new ServiceProcess("01");
		//DataComposition run = new DataComposition("01");
		//WidgetDesign run = new WidgetDesign("01");
		String data = "[{resourceName:START,from:[root],to:[A, B],pattern:parallel,input:[]},{resourceName:A,from:[START],"
			+"to: [END],pattern: sequence,input:[name,date]},{resourceName: B,from:[START],to:[END],pattern: sequence,"
			+"input:[name,date]}]";
		String data2 = "[{DataSet : Flights-Weather,Composition :[{resourceName :Flights,joinColumn : Arrive},"
						+"{resourceName : Weather,joinColumn : Location}],order :{primaryColumn: flight-data,"
						+"secondaryColumn : Weather,style : nestedLoop},}]";
		String data3 = "[{dataSet: Flights-Hotels,type: googlemap,locationName: hotel_name,location: hotel_location}]";

		try
		{	        
			Class.forName(driver);
			conn = DriverManager.getConnection(address, user, password);
			order = new ResourceOrder(conn);			
			//System.out.println(oder.searchResource(query));
			//System.out.println(order.chooseResource(query2));
			//System.out.println(run.changeProcessExpression(data));
			System.out.println(order.searchResource(query));
			
		}
		catch(Exception e) {
			e.printStackTrace();			
		}
		finally {
			// ¨Ì§ÇÃö³¬³s½u
			try {
				if (conn != null ) {
					conn.close();
				}
			}
			catch(Exception e) {
				e.printStackTrace();
				
			}			
			
		}
		
	}
}
