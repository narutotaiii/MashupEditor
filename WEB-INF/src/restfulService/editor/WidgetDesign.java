package restfulService.editor;

import java.util.ArrayList;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;
import org.json.JSONArray;
import org.json.JSONObject;

public class WidgetDesign extends MashupDocumentProduce 
{
	public WidgetDesign(String memberID) {
		super(memberID);
		
	}

	@Override
	public boolean produce(String data) 
	{
		SAXBuilder builder = new SAXBuilder();
		Element root = null,widgetMashup =null,widgetContainer =null,tempElement =null,landmarkName=null,landmarkAddress=null;
		Element page =null ;
		ArrayList<Element> elementList = null;
		try
		{
			Document read_doc = builder.build(getFilePath()+"/MashupDocument.xml");
			root = read_doc.getRootElement();//取的root節點
			//宣告所需的Element
			widgetMashup = new Element("div");
			widgetMashup.setAttribute("class","WidgetMashup");
			widgetContainer = new Element("div");
			widgetContainer.setAttribute("id", "WC_Travel");//需要統一命名規則
			widgetContainer.setAttribute("class", "widgetContaine");
			widgetContainer.setAttribute("type", "TabbedPanel");
			JSONArray processData = new JSONArray(data);//將接收的資訊轉換成JSON物件
			JSONObject temp = null;
			for(int i = 0 ; i < processData.length() ; i++)
			{
				temp = processData.getJSONObject(i);//取得資料
				elementList = new ArrayList<Element>();//將每個分頁的內部設定，加入List
				//產生每個分頁設定				
				page = new Element("div");
				page.setAttribute("id", temp.getString("dataSet"));//需要統一命名規則
				page.setAttribute("name","Tab."+i);
				page.setAttribute("calss","widget");
				page.setAttribute("type",temp.getString("type"));
				if(temp.getString("type").equals("PagedTable"))//判斷分頁格式
				{
					//每個tempElement代表一個內部設定，設定完成加入List
					tempElement = new Element("div");
					tempElement.setAttribute("class", "RankingDataSetName");
					tempElement.setText(temp.getString("dataSet")+"-SortedDataSet");
					elementList.add(tempElement);
					tempElement.setAttribute("class", "ServiceURL");
					tempElement.setText(temp.getString("dataSet"));
					elementList.add(tempElement);					
				}
				else
				{
					tempElement = new Element("div");
					tempElement.setAttribute("class", "MapProvider");
					tempElement.setText("GoogleMap");
					elementList.add(tempElement);
					tempElement = new Element("div");
					tempElement.setAttribute("class", "RankingDataSetName");
					tempElement.setText(temp.getString("dataSet")+"-SortedDataSet");
					elementList.add(tempElement);
					tempElement = new Element("div");
					tempElement.setAttribute("class", "GoogleMap");
					tempElement.setText("");
					landmarkName = new Element("div");
					landmarkName.setAttribute("class", "LandmarkName");
					landmarkName.setText(temp.getString("locationName"));
					landmarkAddress = new Element("div");
					landmarkAddress.setAttribute("class", "LandmarkAddress");
					landmarkAddress.setText(temp.getString("location"));
					tempElement.addContent(landmarkName);
					tempElement.addContent(landmarkAddress);					
					elementList.add(tempElement);
					tempElement = new Element("div");
					tempElement.setAttribute("class", "ServiceURL");
					tempElement.setText(temp.getString("dataSet")+"Map");//需要命名規則
					elementList.add(tempElement);
				}
				//將每個設定加入至各分頁
				for(int index = 0 ; index < elementList.size(); index++)
				{
					page.addContent(elementList.get(index));
				}
				widgetContainer.addContent(page);//將分頁加入至widgetContainer
				
			}
			widgetMashup.addContent(widgetContainer);//將widgetContainer加入至widgetMashup
			root.addContent(widgetMashup);//將widgetMashup加入至root
			//寫入MD
			Format format = Format.getCompactFormat();	                  
		    format.setIndent("    ");//在元素後换行，每一層元素缩排四格        
		    XMLOutputter XMLOut = new XMLOutputter(format);
		    XMLOut.output(read_doc, new FileOutputStream(getFilePath()+"/MashupDocument.xml"));	
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
	public boolean produceWidgetSetup(String data)//存取widget設定資訊
	{		
		File file = new File(getFilePath());
		FileWriter fw =null;
		
		try
		{				
			file = new File(getFilePath()+"/widgetSetup.xml");
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
	

}
