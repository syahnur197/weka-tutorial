package com.syahnur.jsp;

import java.sql.*;
import java.util.HashMap;

import javax.sql.*;

public class ConnectionManager {
	private static String url = "jdbc:mysql://localhost:3306/weka_db?useSSL=false";
	private static String username = "root";
	private static String password = "";
	private static Connection conn;
	private static String myQuery;
	private static ResultSet myRs;
	private static int insert_id = 0;
	
	private static Connection getConnection() {
		try {
			DriverManager.registerDriver(new com.mysql.jdbc.Driver());
			conn = DriverManager.getConnection(url, username, password);
		} catch(Exception exc) {
			exc.printStackTrace();
		}
		return conn;
	}
	
	public PreparedStatement getPreparedStatement(String sql) {
		try {
			return getConnection().prepareStatement(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public Connection getConn() {
		return conn;
	}
	
	public boolean disconnect() {

		if(conn != null) {
			try {
				conn.close();
				return true;
			} catch (SQLException e) {
				e.printStackTrace();
				return false;
			}
		} else {
			return true;
		}
	}
	
	public boolean executeQuery(String query) {
		try {
			java.sql.Statement stmt = getConnection().createStatement();
			ResultSet rs = stmt.executeQuery(query);
			myRs = rs;
			myQuery = query;
			if(rs.next()) {
				return true;
			} else {
				return false;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}
	
	public boolean executeUpdate(String query) {
		Connection connection = getConnection();
		try {
			Statement stmt = connection.createStatement();
			stmt.executeUpdate(query, Statement.RETURN_GENERATED_KEYS);
			ResultSet rs = stmt.getGeneratedKeys();
			myRs = rs;
			myQuery = query;
			if(rs.next()) {
				insert_id = rs.getInt(1);
				return true;
			} else {
				return false;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}
	
	public boolean get(String table, String column, String where) {
		
		String query = "";
		query += "SELECT " + column + " FROM " + table;
		query += " WHERE " + where;
		return executeQuery(query);
	}
	
	public boolean get(String table, String column) {
		String query = "";
		query += "SELECT " + column + " FROM " + table;
		return executeQuery(query);
	}
	
	public boolean get(String table) {
		String query = "";
		query += "SELECT * FROM " + table;
		return executeQuery(query);
	}
	
	public boolean insert(String table, HashMap<String, String> values) {
		String query = "";
		String query2 = "(";
		query += "INSERT INTO `" + table + "` (";
		for(String key : values.keySet()) {
			query += "`"+key+"`,";
			query2 += "\""+values.get(key)+"\",";
		}
		query = query.substring(0, query.length() - 1);
		query2 = query2.substring(0, query2.length() - 1);
		query += ")";
		query2 += ")";
		query = query + " VALUES " + query2;
		return executeUpdate(query);
	}
	
	public String getSQL() {
		String val = myQuery;
		myQuery = "";
		return val;
	}
	
	public ResultSet getResult() {
		ResultSet val = myRs;
		myRs = null;
		return val;
	}
	
	public int getInsertID() {
		if (insert_id != 0) {
			int val = insert_id;
			insert_id = 0;
			return val;
		} else {
			return 0;
		}
	}

}
