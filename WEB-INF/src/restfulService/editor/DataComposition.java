package restfulService.editor;

import java.io.FileOutputStream;
import java.io.IOException;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;
import org.json.JSONArray;
import org.json.JSONObject;

public class DataComposition extends MashupDocumentProduce{
	
	
	public DataComposition(String memberID) {
		super(memberID);
		
	}
	@Override
	public boolean produce(String data)  
	{
		SAXBuilder builder = new SAXBuilder();
		Element root = null , tempElement =null, tempElement2 =null , tempElement3 =null , dataMashup = null , id = null;
		
		try
		{
			Document read_doc = builder.build(getFilePath()+"/MashupDocument.xml");
			root = read_doc.getRootElement();
			dataMashup = new Element("div");
			dataMashup.setAttribute("class", "DataMashup");
			
			JSONArray processData = new JSONArray(data);			
			JSONObject temp = null;
			for(int i = 0 ; i < processData.length() ; i++)
			{
				//輸入需要的div value
				temp = processData.getJSONObject(i);
				id = new Element("div");
				id.setAttribute("id", "D-"+temp.getString("DataSet"));
				id.setAttribute("class", "Data");
				tempElement = new Element("div");
				tempElement.setAttribute("class", "DataSetToJoin");
				tempElement2 = new Element("div");
				tempElement2.setAttribute("class", "PrimaryDataSet");
				tempElement2.setText(temp.getJSONArray("Composition").getJSONObject(0).getString("resourceName")
						+"-DataSet");
				tempElement.addContent(tempElement2);
				tempElement2 = new Element("div");
				tempElement2.setAttribute("class", "SecondaryDataSet");
				tempElement2.setText(temp.getJSONArray("Composition").getJSONObject(1).getString("resourceName")
						+"-DataSet");
				tempElement.addContent(tempElement2);
				tempElement3 = new Element("div");
				tempElement3.setAttribute("class", "InnerJoin");
				tempElement3.addContent(tempElement);
				tempElement = new Element("div");
				tempElement.setAttribute("class", "Joinkeys");
				tempElement2 = new Element("div");
				tempElement2.setAttribute("class", "Key");
				tempElement2.setText(temp.getJSONArray("Composition").getJSONObject(0).getString("resourceName")
						+"-DataSet."+temp.getJSONArray("Composition").getJSONObject(0).getString("joinColumn")
						+"="+temp.getJSONArray("Composition").getJSONObject(1).getString("resourceName")
						+"-DataSet."+temp.getJSONArray("Composition").getJSONObject(1).getString("joinColumn")
						);
				tempElement.addContent(tempElement2);
				tempElement3.addContent(tempElement);
				tempElement = new Element("div");
				tempElement.setAttribute("class", "OutputJoinedDataSetName");
				tempElement.setText(temp.getString("DataSet")+"-JoinDataSet");
				tempElement3.addContent(tempElement);
				id.addContent(tempElement3);
				tempElement3 = new Element("div");
				tempElement3.setAttribute("class", "Ranking");
				tempElement2 = new Element("div");
				tempElement2.setAttribute("class", "JoinDataSetName");
				tempElement2.setText(temp.getString("DataSet")+"-JoinDataSet");
				tempElement3.addContent(tempElement2);
				tempElement2 = new Element("div");
				tempElement2.setAttribute("class", "SortingStyle");
				if(temp.getJSONObject("order").getString("style").equals("nestedLoop"))
					tempElement2.setText("NL");
				else
					tempElement2.setText("MS");
				tempElement3.addContent(tempElement2);
				tempElement2 = new Element("div");
				tempElement2.setAttribute("class", "SortingKey");
				tempElement = new Element("div");
				tempElement.setAttribute("class", "Vertical");
				tempElement.setText(temp.getJSONObject("order").getString("primaryColumn"));
				tempElement2.addContent(tempElement);
				tempElement = new Element("div");
				tempElement.setAttribute("class", "Horizontal");
				tempElement.setText(temp.getJSONObject("order").getString("secondaryColumn"));
				tempElement2.addContent(tempElement);
				tempElement3.addContent(tempElement2);
				tempElement2 = new Element("div");
				tempElement2.setAttribute("class", "OutputSortedDataSetName");
				tempElement2.setText(temp.getString("DataSet")+"-SortedDataSet");
				tempElement3.addContent(tempElement2);
				id.addContent(tempElement3);
			}	
			dataMashup.addContent(id);
			root.addContent(dataMashup);
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
}
