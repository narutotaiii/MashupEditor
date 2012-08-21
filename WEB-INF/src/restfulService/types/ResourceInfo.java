package restfulService.types;



import java.sql.Date;

public class ResourceInfo
{
	private int resourceID;//resourceID
	private String resourceName;//resource�W��
	private String resourceType;//resource����
	private int clumnNum;//���ƶq
	private String description;//�A�ȴy�z
	private String url;//���o�A�Ȫ�url
	
	
	public ResourceInfo(int id , String name , String type , int num , String str , String url)
	{
		resourceID = id;
		resourceName = name;
		resourceType =type;
		clumnNum = num;
		description = str;
		this.url = url;
	}
	
	//setting
	public void setData(int index , String data)
	{
		switch(index)
		{
			case 0 : resourceID = Integer.parseInt(data);break;
			case 1 : resourceName = data;break;
			case 2 : resourceType = data;break;
			case 3 : clumnNum = Integer.parseInt(data);break;
			case 4 : description = data;break;
			case 5 : url = data;break;
		}
	}
	
	//getting
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
		return resourceType;
	}
	public int getClumnNum()
	{
		return clumnNum;
	}
	public String getDescription()
	{
		return description;
	}
	public String getURL()
	{
		return url;
	}
}