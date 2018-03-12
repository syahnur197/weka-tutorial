package com.syahnur.jsp;

import java.sql.*;
import javax.sql.*;

public class Database {
	
	private String hostname = "";
	private String username = "";
	private String password = "";
	private String database = "";
	
	private boolean connection = false;
	private Connection myConn = null;
	private Statement myStmt = null;
	private ResultSet myRs = null;
	private String myQuery = "";
	private int numRows;
	
	public boolean connect() {
		if(!connection) {
			try {
				myConn = DriverManager.getConnection(hostname, username, password);
				myStmt = myConn.createStatement();
				return true;
			} catch (Exception e) {
				e.getLocalizedMessage();
				return false;
			}
		} else {
			return true;
		}
	}
	
	public boolean disconnect() {
		if(myRs != null) {
			try {
				myRs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if(myStmt != null) {
			try {
				myStmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if(myConn != null) {
			try {
				myConn.close();
				return true;
			} catch (SQLException e) {
				e.printStackTrace();
				return false;
			}
		} else {
			return true;
		}
	}
	
	public boolean select(String table, String row, String join, String where, String order) {
		return true;
	}
	
	private boolean tableExist(String table) throws SQLException {
		ResultSet tableInDb = myStmt.executeQuery("SHOW TABLES FROM " + database + " LIKE `" + table + "`");
		if(tableInDb.next()) {
			return true;
		} else {
			return false;
		}
	}

}
