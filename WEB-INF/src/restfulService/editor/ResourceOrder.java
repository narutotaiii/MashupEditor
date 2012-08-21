package restfulService.editor;
import restfulService.types.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;
import java.sql.*;
import org.json.*;

public class ResourceOrder {
	private ResourceDAO run;//����SQL���O
	private ResourceDAOforIO execute;//����SQL���O
	 
	public ResourceOrder(Connection conn)
	{
		run = new ResourceDAO(conn);
		execute = new ResourceDAOforIO(conn);
	}
	
	public JSONArray searchResource(String query)//�H����r�ӴM��A�ȸ귽�A�æ^��JSONArray
	{
		String condition = null;		
		ArrayList<ResourceInfo> searchResult = new ArrayList<ResourceInfo>();		
		JSONArray resourceList = null;
		JSONObject queryLine = null;
		try
		{	
			queryLine = new JSONObject(query);//���Nquery�ഫ��JSONObject����		
			condition = "resource_name LIKE '%"+ queryLine.getString("queryKey") +"%'";//SQL���O	condition		
			searchResult = (ArrayList) run.findList(condition);//�x�s�j�M���G
			//�]�wJSON���e
			resourceList = new JSONArray(searchResult);
			//System.out.println(resourceList.toString());
			
		}
		catch(Exception e) {
			e.printStackTrace();			
		}
		finally
		{
			return resourceList;
		}		
	}
	public JSONArray chooseResource(String query)
	{
		ArrayList<String> nameList = new ArrayList<String>();		
		JSONObject temp = null;
		JSONArray resourceList = null;
		JSONArray idList = null;
		JSONArray ioList = null;
		
		try
		{			
			idList = new JSONArray(query);//���Nquery�ഫ��JSONArray����
			
			for(int i = 0 ; i < idList.length() ; i++)
			{
				temp = (JSONObject) idList.get(i);
				nameList.add(run.find(temp.getString("resourceID")).getResourceName());//�����oresourceName
			}						
			resourceList = new JSONArray();			
			
			for(int i = 0 ; i < idList.length(); i++)
			{
				//�]�wJSONObject����
				temp = new JSONObject();
				temp.put("resourceID", ((JSONObject) idList.get(i)).getString("resourceID"));
				temp.put("resourceName", nameList.get(i));
				execute.setTable("resourceInput");//�]�w��Ʈwtable�W��
				ioList =new JSONArray( (ArrayList<String>) execute.findFieldNameList("rid ='"+((JSONObject) idList.get(i)).getString("resourceID")+"'"));							
				temp.put("input", ioList);
				execute.setTable("resourceOutput");//�]�w��Ʈwtable�W��
				ioList =new JSONArray( (ArrayList<String>) execute.findFieldNameList("rid ='"+((JSONObject) idList.get(i)).getString("resourceID")+"'"));	
				temp.put("output", ioList);	
				resourceList.put(temp);//�s�W��JSONArray�����			
			}
			
		}
		catch(Exception e) {
			e.printStackTrace();			
		}
		finally
		{
			return resourceList;
		}		
	}
	public JSONArray relationalRecommand(Set<String> services)
	{
		Iterator it = services.iterator();
		JSONArray recommandList = new JSONArray();
		//String condition = null;
		while( it.hasNext() ) {
			//condition = "resource_name ='"+ (String)it.next() +"'";
			recommandList.put(new JSONObject(run.find((String)it.next())));
		}
		return recommandList;
		
	}
}
