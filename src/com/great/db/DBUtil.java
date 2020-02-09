package com.great.db;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class DBUtil
{
	private ComboPooledDataSource dataSource;
	public static DBUtil dbUtil;
	private Connection conn;

	private DBUtil()
	{
		dataSource = new ComboPooledDataSource("oracle");
	}

	public synchronized static DBUtil getInstance()
	{
		if (dbUtil == null)
		{
			dbUtil = new DBUtil();
		}
		return dbUtil;
	}

	public Connection getConn()
	{
		try
		{
			if (conn == null)
			{
				conn = dataSource.getConnection();
			}
		} catch (SQLException e)
		{
			e.printStackTrace();
		}
		return conn;
	}

	public ComboPooledDataSource getDataSource()
	{
		return dataSource;
	}

	public void close(Connection conn)
	{
		try
		{
			if (conn != null)
			{
				this.conn.close();
				this.conn = null;
			}
		} catch (SQLException e)
		{
			e.printStackTrace();
		}
	}
}
