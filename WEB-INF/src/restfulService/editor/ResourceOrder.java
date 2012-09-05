package restfulService.editor;
import restfulService.types.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.sql.*;

import org.json.*;

import mashup.*;

public class ResourceOrder {
	private ResourceDAO run;//執行SQL指令
	private ResourceDAOforIO execute;//執行SQL指令
	 
	public ResourceOrder(Connection conn)
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
	public JSONArray patternRecommand(String data) 
	{		
		String rid = null;
		JSONArray recommandServices = new JSONArray();
		List<String> recommandList = null;		
		try
		{
			JSONArray services = new JSONArray(data);			
			SPRecommender recommander = new SPRecommender( CombinationCategory.Selection );				
			//System.out.println(services.getJSONObject(0).toString());
			for(int i = 0 ; i < services.length() ; i++)
			{
				rid = services.getJSONObject(i).getString("resourceID");
				//System.out.println(rid);
				recommandList = recommander.recommend( rid , 2 );
				for(int index = 0 ; index < recommandList.size() ; index++)
				{					
					recommandServices.put(new JSONObject(run.find(recommandList.get(index))));					
				}
			}
			//System.out.println(recommandServices.length());
			for(int j = 0 ; j < recommandServices.length() ; j++)
			{
				for(int k = 0 ; k < j ; k++ )
				{					
					if(recommandServices.getJSONObject(k).getString("resourceName").equals(recommandServices.getJSONObject(j).getString("resourceName")))
					{						
						recommandServices.remove(j);
					}
				}
			}
		}
		catch(Exception e)
		{
			e.getStackTrace();
		}
		finally
		{
			return recommandServices;
		}	
		
	}
}
