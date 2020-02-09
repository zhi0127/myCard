package com.great.db;

import com.great.javabean.TbMenu;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MenuAction
{
	private HashMap<String, ArrayList<TbMenu>> map;

	public HashMap<String, ArrayList<TbMenu>> queryMenu(String personID)
	{
		Connection conn = null;
		try
		{
			conn = DBUtil.getInstance().getConn();
			String sql = "SELECT  A.MENUID,A.MENUNAME prentName,B.MENUID,B.MENUNAME childName,B.MENUURL FROM TB_MENU A,TB_MENU B,TB_WORKPERSON,TB_WPR,TB_RM WHERE A.MENUID = B.MENUPID AND TB_WORKPERSON.PERSONID=" + personID + " AND TB_WORKPERSON.PERSONID=TB_WPR.PERSONID AND TB_WPR.ROLEID=TB_RM.ROLEID AND TB_RM.MENUID=A.MENUID ORDER BY A.MENUID ,B.MENUID";
			QueryRunner qr = new QueryRunner();
			List<TbMenu> lis = qr.query(conn, sql, new BeanListHandler<>(TbMenu.class));
			map = new HashMap<>();
			for (int i = 0; i < lis.size(); i++)
			{
				TbMenu mi = lis.get(i);
				if (map.containsKey(mi.getPrentName()))
				{
					ArrayList<TbMenu> list = map.get(mi.getPrentName());
					list.add(mi);
				} else
				{
					ArrayList<TbMenu> list = new ArrayList<>();
					list.add(mi);
					map.put(mi.getPrentName(), list);
				}
			}
		} catch (SQLException e)
		{
			e.printStackTrace();
		} finally
		{
			DBUtil.getInstance().close(conn);
		}
		return map;
	}
}
