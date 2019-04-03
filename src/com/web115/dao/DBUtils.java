package com.web115.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class DBUtils {

	private static String DB_DRIVER = "com.mysql.jdbc.Driver";
	private static String DB_URL = "jdbc:mysql://localhost/";
	private static String SSL = "?useUnicode=true&characterEncoding=utf-8&useSSL=false&autoReconnect=true";//?useSSL=false&autoReconnect=true
	private static String USER = "root";
	private static String PASSWORD = "root";
	private static Connection connection;
	private static PreparedStatement preparedStatement;
	private static ResultSet resultSet;
	
	 
	static {
		try {
			Class.forName(DB_DRIVER);
		} catch (ClassNotFoundException e) {
			System.err.println("驱动加载失败！");
			System.err.println("原因如下：\n{");
			e.printStackTrace();
			System.err.println("}");
		}
	}
	
	public static Connection getConnection(String database) {
		try {
			connection = DriverManager.getConnection(DB_URL + database + SSL,USER,PASSWORD);
		} catch (SQLException e) {
			System.err.println("数据库连接失败！！！");
			System.err.println("原因如下：\n{");
			e.printStackTrace();
			System.err.println("}");
		}
		return connection;
	}
	
	public static PreparedStatement getPreparedStatement(String sql) {
		try {
			preparedStatement = connection.prepareStatement(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return preparedStatement;
	}
	
	public static ResultSet executeQuery() {
		try {
			resultSet = preparedStatement.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return resultSet;
	}
	
	public static void closeResultSet() {
		try {
			resultSet.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void closePreparedStatement() {
		try {
			preparedStatement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void closeConnection() {
		try {
			connection.close();
		} catch (SQLException e) {
			System.err.println("数据库断开失败！");
			System.err.println("原因如下：\n{");
			e.printStackTrace();
			System.err.println("}");
		}
	}
	
	public static void closeAll() {
		try {
			if(resultSet != null) {
				resultSet.close();
			}
			if(preparedStatement != null) {
				preparedStatement.close();
			}
			if(connection != null) {
				connection.close();
			}
		} catch (SQLException e) {
			System.err.println("数据库断开失败！");
			System.err.println("原因如下：\n{");
			e.printStackTrace();
			System.err.println("}");
		}
	}
	
	public static void insert(String table,List<String> properties,List values) {
		String sql="insert into ";
		sql = sql + table + " (";
		for(int i = 0;i<properties.size();i++) {
			if(i == properties.size() - 1) {
				sql = sql + properties.get(i) + ") values(";
			}else {
				sql = sql + properties.get(i) + ",";
			}
		}
		for(int i = 0;i<values.size();i++) {
			if(i == values.size() - 1) {
				if(!(values.get(i) instanceof Integer)) {
					sql = sql + '"' + values.get(i) + '"' + ")";
				}else {
					sql = sql + values.get(i) + ")";
				}
			}else {
				if(!(values.get(i) instanceof Integer)) {
					sql = sql + '"' + values.get(i) + '"' + ",";
				}else {
					sql = sql + values.get(i) + ",";
				}
			}
		}
		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			System.err.println("数据插入失败！");
			System.err.println("原因如下：\n{");
			e.printStackTrace();
			System.err.println("}");
		}
	}
	
	public static int selectProperty(String table,String property,String value) {
		String sql = "select * from " + table + " where " + property + "=\"" + value + "\"";
		int result = 0;	// 查询的结果，如果为0则正确，-1则错误
		try {
			preparedStatement = connection.prepareStatement(sql);
			resultSet = preparedStatement.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			if(resultSet.next()) {
				result = 0;
			}else {
				result = -1;
			}
		} catch (SQLException e) {
			result = -1;
			e.printStackTrace();
		}
		return result;
	}
	
	public static int selectProperties(String table,String[] properties,String[] values) {
		String sql = "select * from " + table + " where ";// + property + "=\"" + value + "\"";
		int result = 0;	// 查询的结果，如果为0则正确，-1则错误
		
		for(int i=0;i<properties.length;i++) {
			sql = sql + properties[i] + "=\"" + values[i] + "\" and ";
		}
		sql = sql.substring(0,sql.length()-5);
		
		/////////
//		System.out.println(sql);
		try {
			preparedStatement = connection.prepareStatement(sql);
			resultSet = preparedStatement.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			if(resultSet.next()) {
				result = 0;
			}else {
				result = -1;
			}
		} catch (SQLException e) {
			result = -1;
			e.printStackTrace();
		}
		return result;
	}
	
	
	public static ResultSet selectPropertyByProperty(String table,String resultProperty,String property,String value) {
		String sql = "select " + resultProperty + " from " + table + " where " + property + "=\"" + value + "\"";
		try {
			preparedStatement = connection.prepareStatement(sql);
			resultSet = preparedStatement.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return resultSet;
	}
	
	public static int getNumber(String table) {
		int number = 0;
		String sql = "select COUNT(*) from " + table;
		try {
			preparedStatement = connection.prepareStatement(sql);
			resultSet = preparedStatement.executeQuery();
			resultSet.next();
			number = resultSet.getInt(1);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return number;
	}
	
	public static void updateSet(String table,String set,String result,String bySet,String value) {
		String sql = "update " + table + " set " + set + "=\"" + result + "\"" + " where " + bySet + "=\"" + value + "\"";
		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
}
