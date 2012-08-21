//package enjoymovie.types;

import java.sql.*;
import java.sql.Date;
import java.util.*;

public class ResourceDAO extends AbstractDAO<Resource>{
	private static final String table = "resourceInfo";
	private static final String key = "resource_id";
	private static final String  date = "release_date";
	
	public FilmDAO(Connection conn) {
		super(conn);
	}
	
	@Override
	public Resource find(String id) {
		Statement stmt = null;
		ResultSet rs = null;
		Resource result = null;
		String queryStr = "SELECT * FROM " + table + " WHERE " + key + "=" + id;
		
		
		try {
			stmt = getConnection().createStatement();
			rs = stmt.executeQuery(queryStr);
			if (rs.next()) {
				result = new Resource(rs.getInt("resource_id"), rs.getString("resource_name"),
						  rs.getString("resource_type"), rs.getInt("clumnNum"),
						  rs.getString("description"),  rs.getString("url"),);
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	@Override
	public List<Resource> findList(String cond) {
		List<Resource> result = new ArrayList<Resource>();
		Statement stmt = null;
		ResultSet rs = null;
		String queryStr = "SELECT * FROM " + table + " WHERE " + cond ;
		Scoring filmScore = new Scoring(getConnection());
		//System.out.println(queryStr);
		
		try {
			stmt = getConnection().createStatement();
			rs = stmt.executeQuery(queryStr);
			
			while (rs.next()) {
				int resourceID = rs.getInt("resource_id");
				Resource tmpResource = new Resource(resourceID, 
										rs.getString("resource_name"),
										rs.getString("resource_type"), 									 
										rs.getInt("clumnNum"), 
										rs.getString("description"), 
										));
				result.add(tmpFilm);
				tmpFilm = null;
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	/*@Override
	public synchronized boolean update(String id, Film obj) {
		Statement stmt = null;
		String queryStr = "UPDATE " + table + " SET length='" + obj.getLength() + 
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
		
		return true;
	}*/
	
	@Override
	public synchronized boolean insert(Resource obj) {
		Statement stmt = null;
		String queryStr = "INSERT INTO " + table + 
						  "(resource_name,resource_type,clumnNum,description)VALUES('" +
						  obj.getResourceName() + "','" + obj.getResourceType() + "','" + 
						  obj.getClumnNum() + "','" + obj.getdescription() + "')";

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
	public synchronized boolean delete(String id) {
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
	
	public int getResourceAmount() {
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
