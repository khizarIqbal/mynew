package sqlite;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.apache.tomcat.jdbc.pool.PoolProperties;

public class DBConnectionPoolTest {

	static final String JDBC_DRIVER = "org.sqlite.JDBC";
	static final String DB_URL = "jdbc:sqlite:.\\DB\\cards.db";

	public static Connection getConnection() throws SQLException{
		
		Connection con = null;
			
			PoolProperties p = new PoolProperties();
			p.setUrl("jdbc:sqlite:.\\DB\\cards.db");
			p.setDriverClassName("org.sqlite.JDBC");
			p.setValidationQuery("SELECT 1");
			p.setMaxActive(1000);
			p.setInitialSize(100);
			
			DataSource datasource = new DataSource();
			datasource.setPoolProperties(p);
			con = datasource.getConnection();
		
			
		return con;
	}
	
	public static void main(String[] args) {
		Connection con = null;
		try {
			con = getConnection();
			Statement stm = con.createStatement();
			ResultSet rs = stm.executeQuery("SELECT 10+10");
			if (rs.next()){
				System.out.println(rs.getString(1));
			}
		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
		} finally {
			if (con != null)
				try {
					con.close();
				} catch (SQLException ignore) {
				}
		}
	}
}
