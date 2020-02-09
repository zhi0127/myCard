package com.great.db;

import com.great.javabean.*;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.MapHandler;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class PatientAction
{
	private TbWorkPerson workPerson;

	//登录
	public boolean login(String personID, String personPass)
	{
		Connection conn = DBUtil.getInstance().getConn();
		String sql = "SELECT * FROM TB_WORKPERSON,TB_STATE WHERE TB_WORKPERSON.STATEID=TB_STATE.STATEID AND TB_STATE.STATEID=1 AND TB_WORKPERSON.PERSONID= " + personID;
		QueryRunner qr = new QueryRunner();
		try
		{
			workPerson = qr.query(conn, sql, new BeanHandler<>(TbWorkPerson.class));
			if (workPerson != null)
			{
				if (workPerson.getPASSWORD().equals(Md5Test.toMD5(personPass)))
				{
					return true;
				}
			} else
			{
				return false;
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


	//分页
	public TbPageSearch PageSearch(String userName, String departType, String roleType, String stateType, int StartNum)
	{
		TbPageSearch pageSearchInf = new TbPageSearch();
		pageSearchInf.setStart(StartNum);
		Connection conn = null;
		String searchSql1 = null;
		QueryRunner qr = null;
		try
		{
			conn = DBUtil.getInstance().getConn();
			String countSql = "SELECT COUNT(*)COUNT FROM  TB_WORKPERSON,TB_WPR,TB_ROLE,TB_DEPARTMENT,TB_STATE WHERE TB_WORKPERSON.PERSONID=TB_WPR.PERSONID AND TB_WPR.ROLEID=TB_ROLE.ROLEID AND TB_WORKPERSON.DEPARTID=TB_DEPARTMENT.DEPARTID AND TB_WORKPERSON.STATEID=TB_STATE.STATEID";
			//同步			searchSql1 = "SELECT ROWNUM RN,TB_WORKPERSON.PERSONID,TB_WORKPERSON.PERSONNAME,TB_DEPARTMENT.DEPARTNAME,TB_ROLE.ROLENAME,TB_STATE.STATENAME FROM  TB_WORKPERSON,TB_WPR,TB_ROLE,TB_DEPARTMENT,TB_STATE WHERE TB_WORKPERSON.PERSONID=TB_WPR.PERSONID AND TB_WPR.ROLEID=TB_ROLE.ROLEID AND TB_WORKPERSON.DEPARTID=TB_DEPARTMENT.DEPARTID AND TB_WORKPERSON.STATEID=TB_STATE.STATEID";
			//			searchSql1 = "SELECT ROWNUM RN,TB_WORKPERSON.PERSONID,TB_WORKPERSON.DEPARTID,TB_WORKPERSON.PERSONNAME,TB_WPR.ROLEID,TB_DEPARTMENT.DEPARTNAME,TB_ROLE.ROLENAME,TB_STATE.STATENAME FROM  TB_WORKPERSON,TB_WPR,TB_ROLE,TB_DEPARTMENT,TB_STATE WHERE TB_WORKPERSON.PERSONID=TB_WPR.PERSONID AND TB_WPR.ROLEID=TB_ROLE.ROLEID AND TB_WORKPERSON.DEPARTID=TB_DEPARTMENT.DEPARTID AND TB_WORKPERSON.STATEID=TB_STATE.STATEID";

			//			searchSql1 = "SELECT ROWNUM RN ,A.* FROM  (SELECT TB_WORKPERSON.PERSONID,TB_WORKPERSON.PERSONNAME,TB_WORKPERSON.DEPARTID,TB_DEPARTMENT.DEPARTNAME,TB_WPR.ROLEID,TB_ROLE.ROLENAME,TB_STATE.STATENAME FROM  TB_WORKPERSON,TB_WPR,TB_ROLE,TB_DEPARTMENT,TB_STATE  WHERE TB_WORKPERSON.PERSONID=TB_WPR.PERSONID AND TB_WPR.ROLEID=TB_ROLE.ROLEID AND TB_WORKPERSON.DEPARTID=TB_DEPARTMENT.DEPARTID AND TB_WORKPERSON.STATEID=TB_STATE.STATEID\n" + "ORDER BY PERSONNAME ) A ";
			searchSql1 = "SELECT TB_WORKPERSON.PERSONID,TB_WORKPERSON.PERSONNAME,TB_WORKPERSON.DEPARTID,TB_DEPARTMENT.DEPARTNAME,TB_WPR.ROLEID,TB_ROLE.ROLENAME,TB_STATE.STATENAME FROM  TB_WORKPERSON,TB_WPR,TB_ROLE,TB_DEPARTMENT,TB_STATE  WHERE TB_WORKPERSON.PERSONID=TB_WPR.PERSONID AND TB_WPR.ROLEID=TB_ROLE.ROLEID AND TB_WORKPERSON.DEPARTID=TB_DEPARTMENT.DEPARTID AND TB_WORKPERSON.STATEID=TB_STATE.STATEID  ";

			if (userName != null && userName.length() > 0)
			{
				countSql += " AND TB_WORKPERSON.PERSONNAME LIKE'%" + userName + "%'";
				searchSql1 += " AND TB_WORKPERSON.PERSONNAME LIKE'%" + userName + "%'";
			}
			if (departType != null && departType.length() > 0)
			{
				countSql += " AND TB_DEPARTMENT.DEPARTID='" + departType + "'";
				searchSql1 += " AND TB_DEPARTMENT.DEPARTID='" + departType + "'";
			}
			if (roleType != null && roleType.length() > 0)
			{
				countSql += " AND TB_ROLE.ROLEID='" + roleType + "'";
				searchSql1 += " AND TB_ROLE.ROLEID='" + roleType + "'";
			}
			if (stateType != null && stateType.length() > 0)
			{
				countSql += " AND TB_STATE.STATENAME='" + stateType + "'";
				searchSql1 += " AND TB_STATE.STATENAME='" + stateType + "'";
			}

			qr = new QueryRunner();
			Map<String, Object> map = qr.query(conn, countSql, new MapHandler());
			BigDecimal count = (BigDecimal) map.get("COUNT");
			int x = count.intValue();
			int countPage = x % 4 == 0 ? x / 4 : x / 4 + 1;
			BigDecimal b = new BigDecimal(countPage);
			pageSearchInf.setCount(b);
			//			String searchSql2 = "SELECT * FROM  ("+searchSql1+") B WHERE B.RN  BETWEEN "+((StartNum-1)*4+1)+" AND "+StartNum*4 ;
			String searchSql2 = "SELECT * FROM  (SELECT ROWNUM RN ,A.* FROM  (" + searchSql1 + "ORDER BY PERSONNAME ) A ) B WHERE B.RN  BETWEEN " + ((StartNum - 1) * 4 + 1) + " AND " + StartNum * 4;

			List<TbWorkPerson> list = qr.query(conn, searchSql2, new BeanListHandler<>(TbWorkPerson.class));
			System.out.println("list=" + list.size());
			pageSearchInf.setList(list);
		} catch (SQLException e)
		{
			e.printStackTrace();
		} finally
		{
			DBUtil.getInstance().close(conn);
		}
		return pageSearchInf;
	}

	//启用,禁用,删除,重置密码
	public boolean changeType(String personID, String stateID)
	{
		boolean flag = false;
		Connection conn = DBUtil.getInstance().getConn();
		String sql = null;
		String sql2 = null;
		if (stateID != null)
		{
			sql = "UPDATE TB_WORKPERSON SET STATEID = " + stateID + " WHERE PERSONID= " + personID;
		} else
		{
			sql = "UPDATE TB_WORKPERSON SET PASSWORD = 'e10adc3949ba59abbe56e057f20f883e' WHERE PERSONID = " + personID;

		}

		try
		{
			conn.setAutoCommit(false);
			QueryRunner qr = new QueryRunner();
			int num = qr.update(conn, sql);
			if (num > 0)
			{
				flag = true;
			}
			conn.commit();

		} catch (SQLException e)
		{
			e.printStackTrace();
		} finally
		{
			DBUtil.getInstance().close(conn);
		}
		return flag;
	}


	//修改密码
	public boolean updatePass(String loginID, String oldPass, String newPass)
	{
		boolean flag = false;
		Connection conn = DBUtil.getInstance().getConn();


		String oldPassMD5 = Md5Test.toMD5(oldPass);
		String newPassMD5 = Md5Test.toMD5(newPass);
		String isExistSql = "SELECT * FROM TB_WORKPERSON WHERE PERSONID=" + loginID + " AND PASSWORD='" + oldPassMD5 + "'";
		String sql1 = "UPDATE TB_WORKPERSON SET PASSWORD = '" + newPassMD5 + "' WHERE PERSONID= " + loginID;
		try
		{
			conn.setAutoCommit(false);
			QueryRunner qr = new QueryRunner();
			workPerson = qr.query(conn, isExistSql, new BeanHandler<>(TbWorkPerson.class));
			if (workPerson != null)
			{
				int num1 = qr.update(conn, sql1);
				if (num1 > 0)
				{
					flag = true;
				}
			}

			conn.commit();

		} catch (SQLException e)
		{
			e.printStackTrace();
		} finally
		{
			DBUtil.getInstance().close(conn);
		}
		return flag;
	}


	//新增
	public boolean addUser(String cardID, String patientName, String patientAge, String patientWeek, String patientSexy, String patientBirthPlace, String patientIdentity, String patientPhone, String patientAdress, String patientMoney, String cardSeller, String cardSalesTime)
	{
		boolean flag = false;
		int intMoney = Integer.valueOf(patientMoney) - 5;
		Connection conn = DBUtil.getInstance().getConn();
		String sql1 = "INSERT INTO TB_PATIENT ( CARDID, PATIENTNAME, PATIENTAGE, PATIENTWEEK, PATIENTSEXY, PATIENTBIRTHPLACE, PATIENTIDENTITY, PATIENTPHONE, PATIENTADRESS, PATIENTMONEY,CARDSELLER,CARDSALESTIME) VALUES ('" + cardID + "', '" + patientName + "', '" + patientAge + "', '" + patientWeek + "', '" + patientSexy + "', '" + patientBirthPlace + "', '" + patientIdentity + "', '" + patientPhone + "', '" + patientAdress + "', '" + String.valueOf(intMoney) + "', '" + cardSeller + "', '" + cardSalesTime + "')";
		String sql2 = "UPDATE TB_CARD SET STATEID = '9' WHERE CARDID=" + cardID;

		try
		{
			conn.setAutoCommit(false);
			QueryRunner qr = new QueryRunner();
			int num1 = qr.update(conn, sql1);
			int num2 = qr.update(conn, sql2);
			if (num1 > 0 && num2 > 0)
			{
				flag = true;
			}
			conn.commit();

		} catch (SQLException e)
		{
			e.printStackTrace();
		} finally
		{
			DBUtil.getInstance().close(conn);
		}
		return flag;
	}


	//修改
	public boolean updatePersosn(String personID, String departType, String roleType)
	{
		boolean flag = false;
		Connection conn = DBUtil.getInstance().getConn();
		String sql1 = "UPDATE TB_WORKPERSON SET DEPARTID = " + departType + "  WHERE PERSONID= " + personID;
		String sql2 = "UPDATE TB_WPR SET ROLEID = " + roleType + "  WHERE PERSONID= " + personID;
		try
		{
			conn.setAutoCommit(false);
			QueryRunner qr = new QueryRunner();
			int num1 = qr.update(conn, sql1);
			int num2 = qr.update(conn, sql2);
			System.out.println("num1=" + num1);
			System.out.println("num2=" + num2);
			if (num1 > 0 && num2 > 0)
			{
				flag = true;
			}
			conn.commit();

		} catch (SQLException e)
		{
			e.printStackTrace();
		} finally
		{
			DBUtil.getInstance().close(conn);
		}
		return flag;
	}

	//下拉框选项
	public TbSelectType getSelectType()
	{
		Connection conn = null;
		QueryRunner qr = null;
		TbSelectType selectType = new TbSelectType();
		try
		{
			conn = DBUtil.getInstance().getConn();
			String DEPARTNAME = "SELECT * FROM TB_DEPARTMENT";
			String ROLENAME = "SELECT * FROM TB_ROLE";
			qr = new QueryRunner();
			List<TbDepartment> list1 = qr.query(conn, DEPARTNAME, new BeanListHandler<>(TbDepartment.class));
			List<TbRole> list2 = qr.query(conn, ROLENAME, new BeanListHandler<>(TbRole.class));
			selectType.setDepartmentList(list1);
			selectType.setRoleList(list2);
		} catch (SQLException e)
		{
			e.printStackTrace();
		} finally
		{
			DBUtil.getInstance().close(conn);
		}
		return selectType;
	}


	public TbWorkPerson getWorkPerson()
	{
		return workPerson;
	}

	public void setWorkPerson(TbWorkPerson workPerson)
	{
		this.workPerson = workPerson;
	}


}
