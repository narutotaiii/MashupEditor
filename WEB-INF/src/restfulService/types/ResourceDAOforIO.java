package restfulService.types;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ResourceDAOforIO extends AbstractDAO<ResourceIO>{
	private static String table = "resourceInput";
	private static String key = "id";
	
	
	public ResourceDAOforIO(Connection conn) {
		super(conn);
	}
	
	@Override
	public ResourceIO find(String id) {//搜尋單一resource
		Statement stmt = null;
		ResultSet rs = null;
		ResourceIO result = null;
		String queryStr = "SELECT * FROM " + table + " WHERE " + key + "=" + id;
		
		
		try {
			stmt = getConnection().createStatement();
			rs = stmt.executeQuery(queryStr);
			if (rs.next()) {
				result = new ResourceIO(rs.getInt("id"), rs.getInt("rid"),
						  rs.getString("field_name"), rs.getString("field_type")
						  );
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	@Override
	public List<ResourceIO> findList(String cond) {//搜尋resource清單
		List<ResourceIO> result = new ArrayList<ResourceIO>();
		Statement stmt = null;
		ResultSet rs = null;
		String queryStr = "SELECT * FROM " + table + " WHERE " + cond ;
		
		//System.out.println(queryStr);
		
		try {
			stmt = getConnection().createStatement();
			rs = stmt.executeQuery(queryStr);
			
			while (rs.next()) {
				int resourceID = rs.getInt("id");
				ResourceIO tmpResourceIO = new ResourceIO(resourceID, 
										rs.getInt("rid"),
										rs.getString("field_name"), 
										rs.getString("field_type")
										);
				result.add(tmpResourceIO);
				tmpResourceIO = null;
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	@Override
	public synchronized boolean update(String id, ResourceIO obj) {//更新resource資訊
		Statement stmt = null;
		/*String queryStr = "UPDATE " + table + " SET length='" + obj.getLength() + 
						  "',release_date='" + obj.getReleaseDate() + 
						  "',film_cht_name='" + obj.getChtName() + 
						  "',film_eng_name='" + obj.getEngName() + 
						  "',introduction='" + obj.getIntroduction() + 
						  "',nation='" + obj.getNation() + 
						  "',type='" + obj.getType() + 
						  "',level='" + obj.getLevel() + 
						  "',director='" + obj.getDirector() + 
						  "',writer='" + obj.getWriter() + 
						  "',cast='" + obj.getCast() + 
						  "',publisher='" + obj.getPublisher() + 
						  "',box_office='" + obj.getBoxOffice() + 
						  "',official='" + obj.getOfficial() + 
						  "',picture='" + obj.getPicture() + 
						  "',trailer='" + obj.getTrailer() + 
						  "',source='" + obj.getSource() + 
						  "',truemovie_source='" + obj.getTruemovieSource() + 
						  "' WHERE " + key + "=" + id;
		
		//System.out.println(queryStr);
		
		try {
			stmt = getConnection().createStatement();
			stmt.executeUpdate(queryStr);
		}
		catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		*/
		return true;
	}
	
	@Override
	public synchronized boolean insert(ResourceIO obj) {//新增resource資訊
		Statement stmt = null;
		String queryStr = "INSERT INTO " + table + 
						  "(id,rid,field_name,field_type)VALUES('" +
						  obj.getID() + "','" + obj.getRID() + "','" + 
						  obj.getFieldName() + "','" + obj.getFieldType() + "')";

		//System.out.println(queryStr);
		
		try {
			stmt = getConnection().createStatement();
			stmt.executeUpdate(queryStr);
		}
		catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		
		return true;
	}
	
	@Override
	public synchronized boolean delete(String id) {//刪除resource
		Statement stmt = null;
		String queryStr = "DELETE FROM " + table + " WHERE " + key + "=" + id;
		//System.out.println(queryStr);
		
		try {
			stmt = getConnection().createStatement();
			stmt.executeUpdate(queryStr);
		}
		catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		
		return true;
	}
	
	/*public List<Date> getReleaseDates(Date beginDate, Date endDate) {
		ArrayList<Date> result = new ArrayList<Date>();
		Statement stmt = null;
		ResultSet rs = null;
		String queryStr = "SELECT DISTINCT " + date + " FROM " + table + " WHERE " + 
						  date + " BETWEEN '" + beginDate + "' AND '" + endDate + 
						  "' ORDER BY " + date + " ASC";
		
		try {
			stmt = getConnection().createStatement();
			rs = stmt.executeQuery(queryStr);
			while (rs.next()) {
				result.add(rs.getDate(date));
			}
			
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		
		return result;
	}*/
	
	public int getResourceAmount() {//回傳resource數
		int result = 0;
		Statement stmt = null;
		ResultSet rs = null;
		String queryStr = "SELECT COUNT(DISTINCT " + key + ") FROM " + table;
		
		try {
			stmt = getConnection().createStatement();
			rs = stmt.executeQuery(queryStr);
			rs.next();
			result = rs.getInt(1);
			
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	public List<String> findFieldNameList(String cond) {//只取得fieldName清單
		List<String> result = new ArrayList<String>();
		Statement stmt = null;
		ResultSet rs = null;
		String queryStr = "SELECT field_name FROM " + table + " WHERE " + cond ;
		
		//System.out.println(queryStr);
		
		try {
			stmt = getConnection().createStatement();
			rs = stmt.executeQuery(queryStr);
			
			while (rs.next()) {														
				result.add(rs.getString("field_name"));				
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	public void setTable(String name)
	{
		table = name;
	}
	
	public void setKey(String key)
	{
		this.key = key;
	}
	/*public Map<String, String> getNames(String cond) {
		Map<String, String> result = new HashMap<String, String>();
		Statement stmt = null;
		ResultSet rs = null;
		String queryStr = "SELECT film_id, film_cht_name FROM " + table + 
				" WHERE " + cond;

		try {
			stmt = getConnection().createStatement();
			rs = stmt.executeQuery(queryStr);
			while (rs.next()) {
				result.put(rs.getString("film_cht_name"), 
						String.valueOf(rs.getInt("film_id")));
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}*/

}
