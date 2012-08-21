package restfulService.types;

public class ResourceIO 
{
	private int id;//IO�s��
	private int rid;//�A�Ƚs��
	private String field_name;//���W��
	private String field_type;//�������
	
	public ResourceIO(int num , int num2 , String name , String type)
	{
		id = num;
		rid = num2;
		field_name = name;
		field_type = type;
	}
	//setting
	public void setID(int num)
	{
		id = num;
	}
	public void setRID(int num)
	{
		rid = num;
	}
	public void setFieldName(String data)
	{
		field_name = data;
	}
	public void setFieldType(String data)
	{
		field_type = data;
	}
	//getting
	public int getID()
	{
		return id;
	}
	public int getRID()
	{
		return rid;
	}
	public String getFieldName()
	{
		return field_name;
	}
	public String getFieldType()
	{
		return field_type;
	}
}
