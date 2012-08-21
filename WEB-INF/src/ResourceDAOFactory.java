package RestfulService.types;

import java.sql.Connection;

public class ResourceDAOFactory implements DAOFactory {
	private ResourceDAOFactory factory;
	
	private ResourceDAOFactory() {
	}
	
	@Override
	public synchronized ResourceDAOFactory getInstance() {
		if (factory == null) {
			factory = new ResourceDAOFactory();
		}
		return factory;
	}

	@Override
	public ResourceDAO createDAO(Connection conn) {
		// TODO: TO MODIFY
		return null;
	}

}
