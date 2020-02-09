package com.great.db;

import com.great.javabean.*;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.MapHandler;
import sun.security.provider.MD5;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.*;

public class WorkPersonAction
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
	public boolean addUser(String newUserName, String newUserPass, String newDepartType, String newRoleType)
	{
		boolean flag = false;
		Connection conn = DBUtil.getInstance().getConn();
		String sql1 = "INSERT INTO TB_WORKPERSON (DEPARTID, PERSONNAME, PASSWORD, STATEID) VALUES ('" + newDepartType + "', '" + newUserName + "', '" + Md5Test.toMD5(newUserPass) + "', '1') ";
		String sql2 = " INSERT INTO TB_WPR (PERSONID, ROLEID) VALUES ((SELECT MAX(PERSONID) from TB_WORKPERSON ), '" + newRoleType + "')";

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

	//医生选项
	public TbSelectType getDoctorSelect()
	{
		Connection conn = null;
		QueryRunner qr = null;
		TbSelectType selectType = new TbSelectType();
		try
		{
			conn = DBUtil.getInstance().getConn();
			String sql = "SELECT TB_WORKPERSON.PERSONID,TB_WORKPERSON.PERSONNAME FROM TB_WORKPERSON,TB_WPR,TB_ROLE WHERE TB_WORKPERSON.PERSONID=TB_WPR.PERSONID AND TB_WPR.ROLEID=TB_ROLE.ROLEID AND TB_ROLE.ROLENAME='医生' AND TB_WORKPERSON.STATEID='1'";

			qr = new QueryRunner();
			List<TbWorkPerson> list = qr.query(conn, sql, new BeanListHandler<>(TbWorkPerson.class));
			selectType.setDepartmentList(list);
			selectType.setDoctorsList(list);
		} catch (SQLException e)
		{
			e.printStackTrace();
		} finally
		{
			DBUtil.getInstance().close(conn);
		}
		return selectType;
	}

	//添加医生排班
	public boolean addSchedule(String personID, String scheduleDate)
	{
		boolean flag = false;
		Connection conn = DBUtil.getInstance().getConn();


		String sql1 = "INSERT ALL";

		sql1 += " INTO TB_SCHEDULE (PERSONID, SCHEDULEDATE, SCHEDULETIME) VALUES ('" + personID + "', '" + scheduleDate + "', '09:00-10:00')";
		sql1 += " INTO TB_SCHEDULE (PERSONID, SCHEDULEDATE, SCHEDULETIME) VALUES ('" + personID + "', '" + scheduleDate + "', '10:00-11:00')";
		sql1 += " INTO TB_SCHEDULE (PERSONID, SCHEDULEDATE, SCHEDULETIME) VALUES ('" + personID + "', '" + scheduleDate + "', '11:00-12:00')";
		sql1 += " INTO TB_SCHEDULE (PERSONID, SCHEDULEDATE, SCHEDULETIME) VALUES ('" + personID + "', '" + scheduleDate + "', '14:00-15:00')";
		sql1 += " INTO TB_SCHEDULE (PERSONID, SCHEDULEDATE, SCHEDULETIME) VALUES ('" + personID + "', '" + scheduleDate + "', '15:00-16:00')";
		sql1 += " INTO TB_SCHEDULE (PERSONID, SCHEDULEDATE, SCHEDULETIME) VALUES ('" + personID + "', '" + scheduleDate + "', '16:00-17:00')";
		String sql2 = sql1 + "SELECT 1 FROM DUAL";
		//		sql1="INSERT ALL INTO TB_SCHEDULE (PERSONID, SCHEDULEDATE, SCHEDULETIME) VALUES ('29', '2019-11-06', '09:00-10:00') INTO TB_SCHEDULE (PERSONID, SCHEDULEDATE, SCHEDULETIME) VALUES ('29', '2019-11-06', '10:00-11:00') INTO TB_SCHEDULE (PERSONID, SCHEDULEDATE, SCHEDULETIME) VALUES ('29', '2019-11-06', '11:00-12:00') INTO TB_SCHEDULE (PERSONID, SCHEDULEDATE, SCHEDULETIME) VALUES ('29', '2019-11-06', '14:00-15:00') INTO TB_SCHEDULE (PERSONID, SCHEDULEDATE, SCHEDULETIME) VALUES ('29', '2019-11-06', '15:00-16:00') INTO TB_SCHEDULE (PERSONID, SCHEDULEDATE, SCHEDULETIME) VALUES ('29', '2019-11-06', '16:00-17:00')SELECT 1 FROM DUAL ";
		try
		{
			conn.setAutoCommit(false);
			QueryRunner qr = new QueryRunner();
			int num1 = qr.update(conn, sql2);

			if (num1 > 0)
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

	//删除医生排班
	public boolean deleteSchedule(String personID, String scheduleDate)
	{
		boolean flag = false;
		Connection conn = DBUtil.getInstance().getConn();

		String sql = "DELETE FROM TB_SCHEDULE WHERE PERSONID = " + personID + " AND SCHEDULEDATE='" + scheduleDate + "'";
		try
		{
			conn.setAutoCommit(false);
			QueryRunner qr = new QueryRunner();
			int num1 = qr.update(conn, sql);

			if (num1 > 0)
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

	//获取排班
	public List<TbSchedule> getSchedule()
	{
		List<TbSchedule> list = null;
		Connection conn = DBUtil.getInstance().getConn();


		String sql = "SELECT  DISTINCT TB_WORKPERSON.PERSONID,TB_WORKPERSON.PERSONNAME,TB_SCHEDULE.SCHEDULEDATE FROM TB_SCHEDULE,TB_WORKPERSON WHERE TB_WORKPERSON.PERSONID=TB_SCHEDULE.PERSONID";

		try
		{
			conn.setAutoCommit(false);
			QueryRunner qr = new QueryRunner();
			list = qr.query(conn, sql, new BeanListHandler<>(TbSchedule.class));
			conn.commit();

		} catch (SQLException e)
		{
			e.printStackTrace();
		} finally
		{
			DBUtil.getInstance().close(conn);
		}
		return list;
	}

	//判断排班是否存在
	public boolean existSchedule(String personID, String scheduleDate)
	{
		boolean flag = false;
		Connection conn = DBUtil.getInstance().getConn();
		if (personID != null && personID.length() > 0 && scheduleDate != null && scheduleDate.length() > 0)
		{

		}


		String sql = " SELECT *  FROM TB_SCHEDULE  WHERE PERSONID=" + personID + "  AND SCHEDULEDATE='" + scheduleDate + "'";

		try
		{
			conn.setAutoCommit(false);
			QueryRunner qr = new QueryRunner();
			TbSchedule schedule = qr.query(conn, sql, new BeanHandler<>(TbSchedule.class));
			if (schedule != null)
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

	//判断账号是否被禁用
	public boolean isLoginDisable(String personID)
	{
		boolean flag = false;
		Connection conn = DBUtil.getInstance().getConn();

		try
		{

			conn.setAutoCommit(false);
			QueryRunner qr = new QueryRunner();
			if (personID != null && personID.length() > 0)
			{
				String sql = "SELECT * FROM TB_WORKPERSON,TB_STATE WHERE TB_WORKPERSON.STATEID=TB_STATE.STATEID AND TB_STATE.STATENAME='禁用' AND TB_WORKPERSON.PERSONID=" + personID;
				TbWorkPerson workPerson = qr.query(conn, sql, new BeanHandler<>(TbWorkPerson.class));
				if (workPerson != null)
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


	//显示可以预约的医生
	public Map<String, List<TbSchedule>> getRegisteredTable(List<String> days)
	{
		Map<String, List<TbSchedule>> map = null;
		Connection conn = null;

		try
		{
			conn = DBUtil.getInstance().getConn();
			System.out.println(days.get(0));
			System.out.println(days.get(days.size() - 1));
			String sql = "SELECT DISTINCT TB_WORKPERSON.PERSONID,TB_WORKPERSON.PERSONNAME,TB_DEPARTMENT.DEPARTNAME,TB_SCHEDULE.SCHEDULEDATE FROM TB_SCHEDULE,TB_WORKPERSON,TB_DEPARTMENT WHERE TB_SCHEDULE.PERSONID=TB_WORKPERSON.PERSONID AND TB_WORKPERSON.DEPARTID=TB_DEPARTMENT.DEPARTID AND TB_SCHEDULE.SCHEDULEDATE BETWEEN '" + days.get(0) + "' AND  '" + days.get(days.size() - 1) + "'";
			QueryRunner qr = new QueryRunner();
			List<TbSchedule> lis = qr.query(conn, sql, new BeanListHandler<>(TbSchedule.class));
			map = new HashMap<>();
			for (int i = 0; i < lis.size(); i++)
			{

				TbSchedule schedule = lis.get(i);
				if (map.containsKey(schedule.getDEPARTNAME()))
				{
					List<TbSchedule> list = map.get(schedule.getDEPARTNAME());
					list.add(schedule);
				} else
				{
					List<TbSchedule> list = new ArrayList<>();
					list.add(schedule);
					map.put(schedule.getDEPARTNAME(), list);
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


	//显示预约时间段
	public List<TbSchedule> showRegistered(String scheduleDate, String personID)
	{
		List<TbSchedule> list = null;
		Connection conn = DBUtil.getInstance().getConn();


		String sql = "SELECT TB_SCHEDULE.*,TB_PATIENT.PATIENTNAME FROM TB_SCHEDULE LEFT JOIN TB_PATIENT ON TB_SCHEDULE.PATIENTID=TB_PATIENT.PATIENTID WHERE PERSONID='" + personID + "'AND SCHEDULEDATE='" + scheduleDate + "' ORDER BY SCHEDULETIME";

		try
		{
			conn.setAutoCommit(false);
			QueryRunner qr = new QueryRunner();
			list = qr.query(conn, sql, new BeanListHandler<>(TbSchedule.class));
			conn.commit();

		} catch (SQLException e)
		{
			e.printStackTrace();
		} finally
		{
			DBUtil.getInstance().close(conn);
		}
		return list;
	}


	//预约
	public boolean registered(String scheduleDate, String patientID, String scheduleTime, String personID)
	{


		boolean flag = false;
		Connection conn = DBUtil.getInstance().getConn();

		try
		{
			Date nowTime = new Date();
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			String appointmentDate = dateFormat.format(nowTime);
			String sql = null;
			conn.setAutoCommit(false);
			QueryRunner qr = new QueryRunner();
			if (personID != null && personID.length() > 0)
			{
				sql = "UPDATE  TB_SCHEDULE  SET PATIENTID = '" + patientID + "', APPOINTMENTDATE = '" + appointmentDate + "' WHERE SCHEDULEDATE = '" + scheduleDate + "' AND SCHEDULETIME = '" + scheduleTime + "' AND PERSONID='" + personID + "'";
				System.out.println(sql);

			} else
			{
				sql = "UPDATE  TB_SCHEDULE  SET PATIENTID = '" + patientID + "', APPOINTMENTDATE = '" + "" + "' WHERE SCHEDULEDATE = '" + scheduleDate + "' AND SCHEDULETIME = '" + scheduleTime + "' AND PERSONID='" + personID + "'";

			}
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

	//显示可取号表格
	public List<TbSchedule> getTake(String cardHead, String cardNum)
	{
		List<TbSchedule> list = null;
		Connection conn = DBUtil.getInstance().getConn();


		String sql = "SELECT TB_SCHEDULE.SCHEDULEID,TB_SCHEDULE.APPOINTMENTDATE,TB_SCHEDULE.SCHEDULEDATE,TB_WORKPERSON.PERSONNAME,TB_DEPARTMENT.DEPARTNAME FROM TB_CARD,TB_PATIENT,TB_SCHEDULE,TB_WORKPERSON,TB_DEPARTMENT WHERE TB_CARD.CARDID=TB_PATIENT.CARDID AND TB_PATIENT.PATIENTID=TB_SCHEDULE.PATIENTID AND TB_SCHEDULE.PERSONID=TB_WORKPERSON.PERSONID AND TB_WORKPERSON.DEPARTID=TB_DEPARTMENT.DEPARTID AND TB_CARD.CARDHEAD='" + cardHead + "' AND TB_CARD.CARDNUM=" + cardNum;

		try
		{
			conn.setAutoCommit(false);
			QueryRunner qr = new QueryRunner();
			list = qr.query(conn, sql, new BeanListHandler<>(TbSchedule.class));
			conn.commit();

		} catch (SQLException e)
		{
			e.printStackTrace();
		} finally
		{
			DBUtil.getInstance().close(conn);
		}
		return list;
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
