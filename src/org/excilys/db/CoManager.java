package org.excilys.db;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

/**
 * Connection DB factory, use singleton pattern
 * 
 * @author pqwarlot
 *
 */
public class CoManager {
	protected static CoManager instance = null;
	
	protected ResourceBundle properties;
	protected static String resourceBundle = "org/excilys/db/config";
	
	protected CoManager() {
		properties = ResourceBundle.getBundle(resourceBundle);
		
		try {
			Class.forName(properties.getString("DB_DRIVER"));
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public Connection getConnection() {
		Connection connection = null;
		try {
			connection = (Connection) DriverManager.getConnection(properties.getString("JDBC_URL"), properties.getString("DB_LOGIN"), properties.getString("DB_PASSWORD"));
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return connection;
	}
	
	public synchronized static CoManager getInstance() {
		if (instance == null) {
			instance = new CoManager();
		}
		return instance;
	}
	
	public void cleanup(Connection connection, Statement stat, ResultSet rs) {
		if (rs !=null) {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (stat!=null) {
			try {
				stat.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (connection != null) {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
