package restfulService.editor;

public abstract class MashupDocumentProduce {
	private String filePath;
	
	public MashupDocumentProduce(String memberID)
	{
		filePath = "./members/"+memberID;
	}
	
	public abstract boolean produce(String data);
	
	public void setFilePath(String name)
	{
		filePath = name;
	}
	public String getFilePath()
	{
		return filePath;
	}
}
