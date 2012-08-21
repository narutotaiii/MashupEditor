package restfulService.editor;
import restfulService.types.*;
import java.util.ArrayList;
import java.sql.*;
import org.json.*;

public class ResourceOder {
	private ResourceDAO run;//執行SQL指令
	private ResourceDAOforIO execute;//執行SQL指令
	 
	public ResourceOder(Connection conn)
	{
		run = new ResourceDAO(conn);
		execute = new ResourceDAOforIO(conn);
	}
	
	public JSONArray searchResource(String query)//以關鍵字來尋找服務資源，並回傳JSONArray
	{
		String condition = null;		
		ArrayList<ResourceInfo> searchResult = new ArrayList<ResourceInfo>();		
		JSONArray resourceList = null;
		JSONObject queryLine = null;
		try
		{	
			queryLine = new JSONObject(query);//先將query轉換成JSONObject物件		
			condition = "resource_name LIKE '%"+ queryLine.getString("queryKey") +"%'";//SQL指令	condition		
			searchResult = (ArrayList) run.findList(condition);//儲存搜尋結果
			//設定JSON內容
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
			idList = new JSONArray(query);//先將query轉換成JSONArray物件
			
			for(int i = 0 ; i < idList.length() ; i++)
			{
				temp = (JSONObject) idList.get(i);
				nameList.add(run.find(temp.getString("resourceID")).getResourceName());//先取得resourceName
			}						
			resourceList = new JSONArray();			
			
			for(int i = 0 ; i < idList.length(); i++)
			{
				//設定JSONObject物件
				temp = new JSONObject();
				temp.put("resourceID", ((JSONObject) idList.get(i)).getString("resourceID"));
				temp.put("resourceName", nameList.get(i));
				execute.setTable("resourceInput");//設定資料庫table名稱
				ioList =new JSONArray( (ArrayList<String>) execute.findFieldNameList("rid ='"+((JSONObject) idList.get(i)).getString("resourceID")+"'"));							
				temp.put("input", ioList);
				execute.setTable("resourceOutput");//設定資料庫table名稱
				ioList =new JSONArray( (ArrayList<String>) execute.findFieldNameList("rid ='"+((JSONObject) idList.get(i)).getString("resourceID")+"'"));	
				temp.put("output", ioList);	
				resourceList.put(temp);//新增至JSONArray物件裡			
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
}
