//package 

import java.sql.Date;

public class Resource
{
	private int resourceID;
	private String resourceName;
	private String resourceType;
	private int clumnNum;
	private description;
	
	
	public ResourceInfo(int id , String name , String type , int num , String str,)
	{
		resourceID = id;
		resourceName = name;
		resourceType =type;
		clumnNum = num;
		description = str;
	}
	
	public void setData(int index , String data)
	{
		switch(index)
		{
			case 0 : resourceID = Integer.parseInt(data);break;
			case 1 : resourceName = data;break;
			case 2 : resourceType = data;break;
			case 3 : clumnNum = Integer.parseInt(data);break;
			case 4 : description = data;break;
		}
	}
	
	public int getResourceID()
	{
		return resourceID;
	}
	public String getResourceName()
	{
		return resourceName;
	}
	public String getResourceType()
	{
		return resourcetype;
	}
	public int getClumnNum()
	{
		return clumnNum;
	}
	public String getDescription()
	{
		return description;
	}
}