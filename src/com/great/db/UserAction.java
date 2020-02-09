package com.great.db;


import com.great.javabean.TbPageSearch;
import com.great.javabean.TbUser;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.MapHandler;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class UserAction
{

	private TbUser user;

	//	public TbPageSearch PageSearch(String wheres, int StartNum)
	//	{
	//		TbPageSearch pageSearchInf = new TbPageSearch();
	//		pageSearchInf.setStart(StartNum);
	//		Connection conn = null;
	//		String searchSql1 = null;
	//		QueryRunner qr = null;
	//		try
	//		{
	//			conn = DBUtil.getInstance().getConn();
	//			String countSql = "SELECT COUNT(*)COUNT  FROM  TB_USER ";
	//			searchSql1 = "SELECT ROWNUM RN,TB_USER.* FROM  TB_USER ";
	//			if (wheres!=null && wheres.length()>0)
	//			{
	//				countSql += wheres;
	//				searchSql1 += wheres;
	//			}
	//			qr = new QueryRunner();
	//			Map<String,Object> map = qr.query(conn,countSql,new MapHandler());
	//			BigDecimal count = (BigDecimal) map.get("COUNT");
	//			int x = count.intValue();
	//			int countPage =  x%4 == 0?x/4:x/4+1;
	//			BigDecimal b = new BigDecimal(countPage);
	//			pageSearchInf.setCount(b);
	//			String searchSql2 = "SELECT * FROM  ("+searchSql1+") A WHERE A.RN  BETWEEN "+((StartNum-1)*4+1)+" AND "+StartNum*4 +"Order by USERID";
	//			List<TbUser> list = qr.query(conn,searchSql2,new BeanListHandler<>(TbUser.class));
	//			pageSearchInf.setList(list);
	//		} catch (SQLException e)
	//		{
	//			e.printStackTrace();
	//		}finally
	//		{
	//			DBUtil.getInstance().close(conn);
	//		}
	//		return pageSearchInf;
	//	}
	//	public boolean addUser(String name, String pass)
	//	{
	//		boolean flag = false;
	//		Connection conn = DBUtil.getInstance().getConn();
	//		String sql = "INSERT INTO TB_USER(USERNAME,USERPASS) values('"+name+"','"+pass+"')";
	//		try
	//		{
	//			conn.setAutoCommit(false);
	//			QueryRunner qr = new QueryRunner();
	//			int num=qr.update(conn,sql);
	//
	//			if (num>0) {
	//				flag = true;
	//			}
	//			conn.commit();
	//
	//		} catch (SQLException e)
	//		{
	//			e.printStackTrace();
	//		}finally
	//		{
	//			DBUtil.getInstance().close(conn);
	//		}
	//		return flag;
	//	}


	public boolean login(String userName, String adminPass)
	{
		Connection conn = DBUtil.getInstance().getConn();
		String sql = "SELECT * FROM TB_USER WHERE USERNAME = '" + userName + "'";
		QueryRunner qr = new QueryRunner();
		try
		{
			user = qr.query(conn, sql, new BeanHandler<>(TbUser.class));
			if (user.getUSERPASS().equals(adminPass))
			{
				return true;
			}

		} catch (SQLException e)
		{
			e.printStackTrace();
		} finally
		{
			DBUtil.getInstance().close(conn);
		}
		return false;
	}

	public TbUser getUser()
	{
		return user;
	}

	public void setUser(TbUser user)
	{
		this.user = user;
	}
}
