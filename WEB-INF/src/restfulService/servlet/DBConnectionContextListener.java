package restfulService.servlet;

import java.sql.*;
import javax.naming.*;
import javax.servlet.*;
import javax.sql.DataSource;

public class DBConnectionContextListener implements ServletContextListener {
	private DataSource connPool;
	private Connection conn;
	
	
	@Override
	public void contextDestroyed(ServletContextEvent event) {
		
		// Connection Pool
		
		connPool = null;
		
		
		// Single Connection
		try {
			if (conn != null && !(conn.isClosed())) {
				conn.close();
			}
		}
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

	@Override
	public void contextInitialized(ServletContextEvent event) {
		ServletContext sc = event.getServletContext();
		
		// Connection Pool
		
		try {
			Context initCtx = new InitialContext();
			Context envCtx = (Context) initCtx.lookup("java:comp/env");
			connPool = (DataSource) envCtx.lookup("jdbc/root");
			
			sc.setAttribute("dbConnPool", connPool);
		}
		catch (NamingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		// Single Connection
		
		
		String driver = sc.getInitParameter("dbDriver");
		String url = sc.getInitParameter("dbUrl");
		String user = sc.getInitParameter("dbUser");
		String password = sc.getInitParameter("dbPwd");
		
		try {
			Class.forName(driver);
			
			conn = DriverManager.getConnection(url, user, password);			
			sc.setAttribute("dbConn", conn);
			sc.setAttribute("user", user);
			sc.setAttribute("password", password);
		}
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
