package mysql;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.commons.lang3.time.StopWatch;

public class DBTest {

	public static void main(String[] args) {
		
		StopWatch watch = new StopWatch();
		println("Starting Stop Watch");
		watch.start();
		
		println("Making first connection");
		connect();
		println("First Connection took : " + watch.toString());
		
		println("Making 100 connections");
		for (int count = 0; count <= 20; count++) {
			connect();
		}
		
		println("Stopping Stop Watch");
		watch.stop();
		
		println("Time taken : " + watch.toString());

	}
	
	static void connect() {
		
		Connection con = null;
		try {
			con = DBConnection.getConnection();
			Statement stm = con.createStatement();
			ResultSet rs = stm.executeQuery("SELECT 10");
			if (rs.next()){
				rs.getString(1);
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
	
	static void print(String s) {
		System.out.print(s);
	}

	static void println(String s) {
		System.out.println(s);
	}

}

/*
Starting Stop Watch
Making first connection
First Connection took : 00:00:00.106
Making 1000 connections
Stopping Stop Watch
Time taken : 00:00:00.522
*/