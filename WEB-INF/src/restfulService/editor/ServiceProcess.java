package restfulService.editor;

import java.io.*;
import java.util.ArrayList;
import org.json.*;
import org.jdom.*;
import org.jdom.input.SAXBuilder;
import org.jdom.output.*;

public class ServiceProcess extends MashupDocumentProduce{
	private String fieldNameData;
	private String dataPath;//欄位名稱檔案路徑
	ArrayList<String> resourceList;//儲存服務順序
	
	public ServiceProcess(String memberID)
	{
		super(memberID);
		resourceList = new ArrayList<String>();
	}
	@Override
	public boolean produce(String data)
	{		
		File file = new File(getFilePath());//取得檔案路徑			
		ArrayList<String> tempList = new ArrayList<String>();
		ArrayList<Element> children = new ArrayList<Element>();
		Element root = null , tempElement = null , start = null , serviceProcess = null;
		try
		{
			if(!file.isDirectory())//是否有使用者的使用紀錄
				firstCreat();
			//建立MD格式
	        root = new Element("div");
	        root.setAttribute("class", "MashupDocument");
	        root.setAttribute("interaction", "true");        
	        Document doc =	new Document(root);
	        serviceProcess = new Element("div");
	        serviceProcess.setAttribute("class","ServiceProcessMashup");	        
			JSONArray processData = new JSONArray(data);			
			JSONObject temp = null;			
			start = new Element("div");
			//產出<ServiceProcessMashup>
			for(int i = 0 ; i < processData.length() ; i++)
			{
				temp = processData.getJSONObject(i);			
				if(temp.getString("resourceName").equals("START"))//先取得Start資訊
				{					
					start.setAttribute("type","root");
					start.setAttribute("class","Sequence");
					if(!temp.getString("pattern").equals("sequence"))//偵測為平行或select服務
					{
						tempElement = new Element("div");
						if(temp.getString("pattern").equals("parallel"))
							tempElement.setAttribute("class","parallel");
						else
							tempElement.setAttribute("class","select");
						children = insertComponent(processData,temp.getString("resourceName"));//取得服務下的所有節點
						for(int index = 0 ; index < children.size() ; index++)
						{
							tempElement.addContent(children.get(index));
						}
						start.addContent(tempElement);
					}									
				}
				else//其餘為循序服務
				{
					if(!checkResource(temp.getString("resourceName")))
					{						
						tempElement = new Element("div");
						tempElement.setAttribute("type","simple");
						tempElement.setAttribute("id",temp.getString("resourceName"));
						tempElement.setAttribute("class","ComponentService");
						if(temp.getJSONArray("from").getString(0).contains("Select"))
						{
							tempElement.setAttribute("Condition","location");
						}
						tempElement.setText(temp.getString("resourceName") +" Service");						
						resourceList.add(temp.getString("resourceName"));
					}
					if(!temp.getString("pattern").equals("sequence"))//再繼續偵測有無平行或select服務
					{
						tempElement = new Element("div");
						if(temp.getString("pattern").equals("parallel"))
							tempElement.setAttribute("class","parallel");
						else
							tempElement.setAttribute("class","select");
						children = insertComponent(processData,temp.getString("resourceName"));
						for(int index = 0 ; index < children.size() ; index++)
						{
							tempElement.addContent(children.get(index));
						}
						start.addContent(tempElement);
					}													
				}				
			}
			tempElement = new Element("div");
			tempElement.setAttribute("id","Traveling");
			tempElement.setAttribute("class","ProcessService");
			tempElement.addContent(start);
			serviceProcess.addContent(tempElement);
			
			//產出<ServiceBinding>
	        Element componment = null;
	        Element componment2 = null;
			
			for(int i = 0 ; i < resourceList.size() ; i++)
			{
				componment = new Element("div");
				componment.setAttribute("id",resourceList.get(i));
				componment.setAttribute("class","ServiceBinding");				
				for(int j = 0 ; j < processData.length() ; j++)
				{
					
					if(resourceList.get(i).equals(processData.getJSONObject(j).getString("resourceName")))
					{
						
						tempElement = new Element("div");						
						tempElement.setAttribute("class","ServiceID");
						tempElement.setAttribute("serviceDescriptionName",resourceList.get(i)+"ServiceDescription");
						tempElement.setText(resourceList.get(i));
						componment.addContent(tempElement);
						componment2 = new Element("div");						
						componment2.setAttribute("class","Input");					
						
						for(int index = 0 ; index < processData.getJSONObject(j).getJSONArray("input").length() ; index++)
						{
							tempElement = new Element("div");						
							tempElement.setAttribute("class","parameter");
							tempElement.setAttribute("name",processData.getJSONObject(j).getJSONArray("input").getString(index));
							tempElement.setAttribute("interaction","true");
							componment2.addContent(tempElement);
						}
						componment.addContent(componment2);
						tempElement = new Element("div");						
						tempElement.setAttribute("class","OutputDataSetName");
						tempElement.setAttribute("datasetType","group");
						tempElement.setText(resourceList.get(i)+"-DataSet");
						componment.addContent(tempElement);
						break;
					}
				}
				serviceProcess.addContent(componment);
			}
			
			root.addContent(serviceProcess);
			Format format = Format.getCompactFormat();	                  
	        format.setIndent("    ");//在元素後换行，每一層元素缩排四格	        
	        XMLOutputter XMLOut = new XMLOutputter(format);
	        XMLOut.output(doc, new FileOutputStream(getFilePath()+"/MashupDocument.xml",true));
	        			
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return false;
		}
		finally
		{			
			
			return true;
		}		
	}
	public boolean produceFieldNameData(String data)//存取欄位名稱修改資訊
	{		
		File file = new File(getFilePath());
		FileWriter fw =null;
		
		try
		{			
			if(!file.isDirectory())
				firstCreat();
			file = new File(getFilePath()+"/FieleNameData.xml");
			if(file == null)
			{
				file.createNewFile();
			}
			fw = new FileWriter(file,true);
			fw.append(data+"\n");					
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{			
			try
			{
				fw.close();
			} 
			catch (IOException e) 
			{
				e.printStackTrace();
			}	
			return true;
		}		
	}
	public void firstCreat() throws IOException//建立使用者資料夾空間
	{
		File file = new File(getFilePath());
		file.mkdir();	 
	}
	//插入XML child
	public ArrayList<Element> insertComponent(JSONArray data ,String resoureName) throws JSONException
	{
		JSONObject temp = null;
		Element tempElement =null;
		ArrayList<Element>  children= new ArrayList<Element>();
		ArrayList<Element> subChildren = new ArrayList<Element>();
		for(int i = 0 ; i < data.length() ; i++)
		{
			temp = data.getJSONObject(i);
			if(temp.getJSONArray("from").getString(0).equals(resoureName))
			{	
				
				if(temp.getString("resourceName").contains("Select"))
				{
					tempElement = new Element("div");
					tempElement.setAttribute("class","select");
					subChildren = insertComponent(data,temp.getString("resourceName"));
					for(int index = 0 ; index < subChildren.size() ; index++)
					{
						tempElement.addContent(subChildren.get(index));
					}
					children.add(tempElement);
					
				}	
				else if(temp.getString("pattern").equals("parallel"))
				{
					tempElement = new Element("div");
					tempElement.setAttribute("class","parallel");
					subChildren = insertComponent(data,temp.getString("resourceName"));
					for(int index = 0 ; index < subChildren.size() ; index++)
					{
						tempElement.addContent(subChildren.get(index));
					}
					children.add(tempElement);
				}
				else
				{
					if(!checkResource(temp.getString("resourceName")))
					{
						
						tempElement = new Element("div");
						tempElement.setAttribute("type","simple");
						tempElement.setAttribute("id",temp.getString("resourceName"));
						tempElement.setAttribute("class","ComponentService");
						if(temp.getJSONArray("from").getString(0).contains("Select"))
						{
							tempElement.setAttribute("Condition","location");
						}
						tempElement.setText(temp.getString("resourceName") +" Service");						
						resourceList.add(temp.getString("resourceName"));
						children.add(tempElement);
					}
				}						
			}				
		}
		return children;		
	}
	public boolean checkResource(String name)//檢查服務是否已存取
	{
		for(int i = 0 ; i < resourceList.size(); i++)
		{
			if(resourceList.get(i).equals(name))
				return true;
		}
		return false;
	}
	public String changeProcessExpression(String data) throws JSONException
	{
		JSONArray processData = new JSONArray(data);
		JSONObject temp = null;
		String process = "";
		String nextResource = new String("START");
		for(int i = 0 ; i < processData.length() ; i++)
		{
			temp = processData.getJSONObject(i);
			if(temp.get("resourceName").equals(nextResource))
			{
				if(temp.get("pattern").equals("parallel"))
				{
					process += "(";
				}
				for(int j = 0 ; j < temp.getJSONArray("to").length() ; j++)
				{	
					process += temp.getJSONArray("to").get(j);
				}
				if(temp.get("pattern").equals("parallel"))
				{
					process += ")";
				}
				nextResource = (String) temp.getJSONArray("to").get(0);							
			}			
		}
		return process.replace("END", "");
	}
}
