package mysql;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.apache.tomcat.jdbc.pool.PoolProperties;

public class DBConnection {
	
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	static final String DB_URL = "jdbc:mysql://localhost/mysql";
	static final String USER = "root";
	static final String PASS = "";

	
	public static Connection getConnection() throws SQLException{
		
		Connection con = null;
		try {
			
			// load the Driver Class
			Class.forName(JDBC_DRIVER);

			// create the connection now
			con = DriverManager.getConnection(DB_URL, USER, PASS);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return con;
	}
	
public static Connection getPooledConnection() throws SQLException{
		
		Connection con = null;
			
			PoolProperties p = new PoolProperties();
			
			p.setDriverClassName(JDBC_DRIVER);
			p.setUrl(DB_URL);
			p.setUsername(USER);
	        p.setPassword(PASS);
	        
	        //p.setJmxEnabled(true);
	          //p.setTestWhileIdle(false);
	          //p.setTestOnBorrow(true);
	          //p.setValidationQuery("SELECT 1");
	          //p.setTestOnReturn(false);
	          //p.setValidationInterval(30000);
	          //p.setTimeBetweenEvictionRunsMillis(30000);
	          
	         /* p.setInitialSize(10);
	          p.setMaxActive(20);
	          
	          p.setMinIdle(10);
	          p.setMaxIdle(10);
	          
	          p.setMaxWait(10000);
	          p.setRemoveAbandonedTimeout(60);*/
	          
	          //p.setMinEvictableIdleTimeMillis(30000);
	          
	          //p.setLogAbandoned(true);
	          //p.setRemoveAbandoned(true);
	          /*p.setJdbcInterceptors(
	            "org.apache.tomcat.jdbc.pool.interceptor.ConnectionState;"+
	            "org.apache.tomcat.jdbc.pool.interceptor.StatementFinalizer");*/
	          
	          //p.setJmxEnabled(true);
	          //p.setTestWhileIdle(false);
	          //p.setTestOnBorrow(true);
	          p.setValidationQuery("SELECT 1");
	          p.setTestOnReturn(false);
	          p.setValidationInterval(30000);
	          p.setTimeBetweenEvictionRunsMillis(30000);
	          p.setMaxActive(100);
	          p.setInitialSize(10);
	          p.setMaxWait(10000);
	          p.setRemoveAbandonedTimeout(60);
	          p.setMinEvictableIdleTimeMillis(30000);
	          p.setMinIdle(10);
	          p.setLogAbandoned(true);
	          p.setRemoveAbandoned(true);
			
			DataSource datasource = new DataSource();
			datasource.setPoolProperties(p);
			
			con = datasource.getConnection();
		//	System.out.println(datasource.getActive());
			
		return con;
	}

}
