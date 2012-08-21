package restfulService.types;

import java.sql.Connection;
import java.util.List;

public abstract class AbstractDAO<T> {
	private Connection conn;
	
	protected AbstractDAO(Connection conn) {
		this.conn = conn;
	}
	
	public abstract T find(String id);
	public abstract List<T> findList(String cond);
	public abstract boolean update(String id, T obj);
	public abstract boolean insert(T obj);
	public abstract boolean delete(String id);
	
	protected Connection getConnection() {
		return conn;
	}
}
