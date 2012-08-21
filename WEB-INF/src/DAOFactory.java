package RestfulService.types;

import java.sql.Connection;

public interface DAOFactory {
	public DAOFactory getInstance();
	public AbstractDAO createDAO(Connection conn);
}
