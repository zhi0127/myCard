package com.great.db;

import com.great.javabean.*;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.MapHandler;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Map;

public class CardAction
{
	private TbCard card;


	//卡入库分页
	public TbPageSearch PageSearch(String cardHead, String startNum, String endNum, String cardState, String startTime, String endTime, int StartNum)
	{
		TbPageSearch pageSearchInf = new TbPageSearch();
		pageSearchInf.setStart(StartNum);
		Connection conn = null;
		String searchSql1 = null;
		QueryRunner qr = null;
		try
		{
			conn = DBUtil.getInstance().getConn();
			String countSql = "SELECT COUNT(*)COUNT FROM  TB_CARD,TB_STATE WHERE TB_CARD.STATEID=TB_STATE.STATEID ";
			searchSql1 = "SELECT TB_CARD.*,TB_STATE.STATENAME FROM  TB_CARD,TB_STATE WHERE TB_CARD.STATEID=TB_STATE.STATEID ";

			if (cardHead != null && cardHead.length() > 0)
			{
				countSql += " AND TB_CARD.CARDHEAD ='" + cardHead + "'";
				searchSql1 += " AND TB_CARD.CARDHEAD ='" + cardHead + "'";
			}
			if (startNum != null && startNum.length() > 0 && endNum != null && endNum.length() > 0)
			{
				countSql += " AND TB_CARD.CARDNUM BETWEEN  " + startNum + " and " + endNum;
				searchSql1 += " AND TB_CARD.CARDNUM BETWEEN  " + startNum + " and " + endNum;
			}
			if (startTime != null && startTime.length() > 0 && endTime != null && endTime.length() > 0)
			{
				countSql += " AND TB_CARD.CARDTIME BETWEEN  '" + startTime + "' and '" + endTime + "'";
				searchSql1 += " AND TB_CARD.CARDTIME BETWEEN  '" + startTime + "' and '" + endTime + "'";
			}
			if (cardState != null && cardState.length() > 0)
			{
				countSql += " AND TB_CARD.STATEID=" + cardState;
				searchSql1 += " AND TB_CARD.STATEID=" + cardState;
			}

			qr = new QueryRunner();
			Map<String, Object> map = qr.query(conn, countSql, new MapHandler());
			BigDecimal count = (BigDecimal) map.get("COUNT");
			int x = count.intValue();
			int countPage = x % 4 == 0 ? x / 4 : x / 4 + 1;
			BigDecimal b = new BigDecimal(countPage);
			pageSearchInf.setCount(b);
			String searchSql2 = "SELECT * FROM  (SELECT ROWNUM RN ,A.* FROM  (" + searchSql1 + "ORDER BY TB_CARD.CARDTIME DESC,TB_CARD.CARDNUM ASC ) A ) B WHERE B.RN  BETWEEN " + ((StartNum - 1) * 4 + 1) + " AND " + StartNum * 4;

			List<TbCard> list = qr.query(conn, searchSql2, new BeanListHandler<>(TbCard.class));
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


	//删除卡
	public boolean delectCard(String cardID)
	{
		boolean flag = false;
		Connection conn = DBUtil.getInstance().getConn();
		String sql = null;
		if (cardID != null)
		{
			sql = "UPDATE TB_CARD SET STATEID = '5' WHERE CARDID=" + cardID;
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


	//注销卡
	public boolean cancelCard(String cardID)
	{
		boolean flag = false;
		Connection conn = DBUtil.getInstance().getConn();
		String sql = null;
		if (cardID != null)
		{
			sql = "UPDATE TB_CARD SET STATEID = '11' WHERE CARDID=" + cardID;
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

	//下拉框选项
	public TbSelectType getSelectType()
	{
		Connection conn = null;
		QueryRunner qr = null;
		TbSelectType selectType = new TbSelectType();
		try
		{
			conn = DBUtil.getInstance().getConn();
			String sql = "SELECT  DISTINCT CARDHEAD FROM TB_CARD";

			qr = new QueryRunner();
			List<TbCard> list1 = qr.query(conn, sql, new BeanListHandler<>(TbCard.class));

			selectType.setCardHeadList(list1);

		} catch (SQLException e)
		{
			e.printStackTrace();
		} finally
		{
			DBUtil.getInstance().close(conn);
		}
		return selectType;
	}

	//获取开始卡号
	public String getStartCardNum(String cardHead)
	{
		String startCardNum = null;
		Connection conn = null;
		QueryRunner qr = null;
		try
		{
			conn = DBUtil.getInstance().getConn();

			String sql = "SELECT MAX(CARDNUM) CARDNUM FROM TB_CARD WHERE CARDHEAD='" + cardHead + "'";

			qr = new QueryRunner();
			TbCard card = qr.query(conn, sql, new BeanHandler<>(TbCard.class));


			startCardNum = card.getCARDNUM();
			System.out.println(startCardNum);


		} catch (SQLException e)
		{
			e.printStackTrace();
		} finally
		{
			DBUtil.getInstance().close(conn);
		}
		return startCardNum;
	}

	//卡入库
	public boolean addCard(String cardHead, String cardStart, String cardEnd, String stateID, String cardTime)
	{
		boolean flag = false;
		Connection conn = DBUtil.getInstance().getConn();
		String sql1 = "INSERT ALL";
		int intCardStart = Integer.valueOf(cardStart);
		int intCardEnd = Integer.valueOf(cardEnd);
		for (int i = intCardStart; i <= intCardEnd; i++)
		{
			sql1 += " INTO TB_CARD(STATEID, CARDHEAD, CARDNUM, CARDTIME) VALUES ('" + stateID + "', '" + cardHead + "', '" + String.format("%08d", i) + "', '" + cardTime + "') ";
		}
		String sql2 = sql1 + "SELECT 1 FROM DUAL";

		try
		{
			conn.setAutoCommit(false);
			QueryRunner qr = new QueryRunner();
			int num = qr.update(conn, sql2);
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

	//获得要售的卡
	public TbCard getSaleCard(String applyPersonID)
	{
		TbCard card = null;
		Connection conn = DBUtil.getInstance().getConn();
		String sql = "SELECT * FROM (SELECT CARDID,CARDHEAD,CARDNUM ,ROWNUM RN FROM(SELECT TB_CARD.* FROM TB_COLLACARD,TB_CC,TB_CARD WHERE TB_COLLACARD.COLLACARDID=TB_CC.COLLACARDID AND TB_CC.CARDID=TB_CARD.CARDID AND TB_CARD.STATEID=8 AND TB_COLLACARD.APPLYPERSONID='" + applyPersonID + "' ORDER BY TB_CARD.CARDID))WHERE RN=1";

		try
		{
			conn.setAutoCommit(false);
			QueryRunner qr = new QueryRunner();
			card = qr.query(conn, sql, new BeanHandler<>(TbCard.class));

			conn.commit();
		} catch (SQLException e)
		{
			e.printStackTrace();
		} finally
		{
			DBUtil.getInstance().close(conn);
		}
		return card;
	}

	//出售卡改变卡状态
	public boolean SaleCard(String cardID)
	{
		boolean flag = false;
		Connection conn = DBUtil.getInstance().getConn();
		String sql = null;
		if (cardID != null)
		{
			sql = "UPDATE TB_CARD SET STATEID = '5' WHERE CARDID=" + cardID;
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


	//根据卡号读卡
	public TbPatient readCard(String cardHead, String cardNum, String identity, String phone)
	{
		TbPatient patient = null;
		String sql = null;
		Connection conn = DBUtil.getInstance().getConn();
		if (cardHead != null & cardNum != null)
		{
			sql = "SELECT * FROM TB_PATIENT WHERE CARDID=( SELECT CARDID FROM TB_CARD WHERE CARDHEAD='" + cardHead + "' AND CARDNUM=" + cardNum + " )";

		} else if (identity != null)
		{
			sql = "SELECT * FROM( SELECT ROWNUM RN,TB_PATIENT.* FROM TB_PATIENT WHERE PATIENTIDENTITY='" + identity + "'ORDER BY TB_PATIENT.PATIENTID)WHERE RN=1";

		} else if (phone != null)
		{
			sql = "SELECT * FROM( SELECT ROWNUM RN,TB_PATIENT.* FROM TB_PATIENT WHERE PATIENTPHONE='" + phone + "'ORDER BY TB_PATIENT.PATIENTID)WHERE RN=1";

		}

		try
		{
			conn.setAutoCommit(false);
			QueryRunner qr = new QueryRunner();
			patient = qr.query(conn, sql, new BeanHandler<>(TbPatient.class));

			conn.commit();
		} catch (SQLException e)
		{
			e.printStackTrace();
		} finally
		{
			DBUtil.getInstance().close(conn);
		}
		return patient;
	}


	//换卡,把旧卡状态改为已损坏
	public boolean changeCard(String oldCardID, String newCardID, String patientID)
	{
		boolean flag = false;
		Connection conn = DBUtil.getInstance().getConn();
		String sql1 = null;
		String sql2 = null;
		String sql3 = null;
		if (oldCardID != null && newCardID != null && patientID != null)
		{
			sql1 = "UPDATE TB_PATIENT SET CARDID = " + newCardID + " WHERE PATIENTID=" + patientID;
			sql2 = "UPDATE TB_CARD SET STATEID = 10 WHERE CARDID=" + oldCardID;
			sql3 = "UPDATE TB_CARD SET STATEID = 9 WHERE CARDID=" + newCardID;
		}

		try
		{
			conn.setAutoCommit(false);
			QueryRunner qr = new QueryRunner();
			int num1 = qr.update(conn, sql1);
			int num2 = qr.update(conn, sql2);
			int num3 = qr.update(conn, sql3);

			if (num1 > 0 && num2 > 0 && num3 > 0)
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

	//退卡,退押金
	public boolean returnCard(String oldCardID, String patientID)
	{
		boolean flag = false;
		Connection conn = DBUtil.getInstance().getConn();
		String sql1 = null;
		String sql2 = null;

		if (oldCardID != null && patientID != null)
		{
			sql1 = "DELETE FROM TB_PATIENT WHERE PATIENTID=" + patientID;
			sql2 = "UPDATE TB_CARD SET STATEID = 8 WHERE CARDID=" + oldCardID;
		}
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


	//卡查询分页
	public TbPageSearch inquirePageSearch(String card, String cardState, String applyPersonID, int StartNum)
	{
		TbPageSearch pageSearchInf = new TbPageSearch();
		pageSearchInf.setStart(StartNum);
		Connection conn = null;
		String searchSql1 = null;
		QueryRunner qr = null;
		try
		{
			conn = DBUtil.getInstance().getConn();
			String countSql = "SELECT COUNT(*)COUNT FROM( SELECT T3.*,T4.APPLYPERSONNAME,T4.APPLYPERSONID,T4.APPLYDATE FROM (SELECT TB_CARD.CARDID,TB_CARD.CARDHEAD,TB_CARD.CARDNUM,TB_CARD.STATEID, TB_PATIENT.PATIENTNAME,TB_PATIENT.PATIENTMONEY,TB_PATIENT.CARDSELLER,TB_PATIENT.CARDSALESTIME FROM TB_CARD LEFT JOIN TB_PATIENT ON TB_CARD.CARDID=TB_PATIENT.CARDID)T3 LEFT JOIN (SELECT TB_CC.CARDID,TB_COLLACARD.APPLYPERSONID,TB_COLLACARD.APPLYPERSONNAME,TB_COLLACARD.APPLYDATE FROM TB_CC,TB_COLLACARD WHERE TB_CC.COLLACARDID=TB_COLLACARD.COLLACARDID)T4 ON T3.CARDID=T4.CARDID )T1 LEFT JOIN TB_STATE T2 ON T1.STATEID=T2.STATEID WHERE 1=1 ";
			searchSql1 = "SELECT  T1.*,T2.STATENAME FROM( SELECT T3.*,T4.APPLYPERSONNAME,T4.APPLYPERSONID,T4.APPLYDATE FROM (SELECT TB_CARD.CARDID,TB_CARD.CARDHEAD,TB_CARD.CARDNUM,TB_CARD.STATEID, TB_PATIENT.PATIENTNAME,TB_PATIENT.PATIENTMONEY,TB_PATIENT.CARDSELLER,TB_PATIENT.CARDSALESTIME FROM TB_CARD LEFT JOIN TB_PATIENT ON TB_CARD.CARDID=TB_PATIENT.CARDID)T3 LEFT JOIN (SELECT TB_CC.CARDID,TB_COLLACARD.APPLYPERSONID,TB_COLLACARD.APPLYPERSONNAME,TB_COLLACARD.APPLYDATE FROM TB_CC,TB_COLLACARD WHERE TB_CC.COLLACARDID=TB_COLLACARD.COLLACARDID)T4 ON T3.CARDID=T4.CARDID )T1 LEFT JOIN TB_STATE T2 ON T1.STATEID=T2.STATEID WHERE 1=1 ";

			if (card != null && card.length() > 0)
			{
				String cardHead = card.replaceAll("\\d", "");
				String cardNum = card.replaceAll("[a-zA-Z]", "");

				if (null != cardHead && cardHead.length() > 0 && null != cardNum && cardNum.length() > 0)
				{
					countSql += " AND T1.CARDHEAD='" + cardHead + "' AND T1.CARDNUM=" + cardNum;
					searchSql1 += " AND T1.CARDHEAD='" + cardHead + "' AND T1.CARDNUM=" + cardNum;
				}

			}

			if (cardState != null && cardState.length() > 0)
			{
				countSql += " AND T1.STATEID=" + cardState;
				searchSql1 += " AND T1.STATEID=" + cardState;
			}

			if (applyPersonID != null && applyPersonID.length() > 0)
			{
				countSql += " AND T1.APPLYPERSONID='" + applyPersonID + "'";
				searchSql1 += " AND T1.APPLYPERSONID='" + applyPersonID + "'";
			}


			qr = new QueryRunner();
			Map<String, Object> map = qr.query(conn, countSql, new MapHandler());
			BigDecimal count = (BigDecimal) map.get("COUNT");
			int x = count.intValue();
			int countPage = x % 4 == 0 ? x / 4 : x / 4 + 1;
			BigDecimal b = new BigDecimal(countPage);
			pageSearchInf.setCount(b);
			String searchSql2 = "SELECT * FROM  (SELECT ROWNUM RN ,A.* FROM  (" + searchSql1 + "ORDER BY CARDID ) A ) B WHERE B.RN  BETWEEN " + ((StartNum - 1) * 4 + 1) + " AND " + StartNum * 4;

			List<TbCard> list = qr.query(conn, searchSql2, new BeanListHandler<>(TbCard.class));
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

	//卡查询领用人下拉框
	public TbSelectType getInquireSelect()
	{
		Connection conn = null;
		QueryRunner qr = null;
		TbSelectType selectType = new TbSelectType();
		try
		{
			conn = DBUtil.getInstance().getConn();
			String sql = "SELECT DISTINCT TB_COLLACARD.APPLYPERSONID,TB_COLLACARD.APPLYPERSONNAME FROM( SELECT DISTINCT COLLACARDID FROM TB_CC)T1 ,TB_COLLACARD WHERE T1.COLLACARDID=TB_COLLACARD.COLLACARDID";

			qr = new QueryRunner();
			List<TbCollaCard> list = qr.query(conn, sql, new BeanListHandler<>(TbCollaCard.class));

			selectType.setCollaCardList(list);

		} catch (SQLException e)
		{
			e.printStackTrace();
		} finally
		{
			DBUtil.getInstance().close(conn);
		}
		return selectType;
	}

	//领卡查询分页
	public TbPageSearch collarInquirePageSearch(String applyPersonID, String cardHead, String startNum, String endNum, String startTime, String endTime, int StartNum)
	{
		TbPageSearch pageSearchInf = new TbPageSearch();
		pageSearchInf.setStart(StartNum);
		Connection conn = null;
		String searchSql1 = null;
		QueryRunner qr = null;
		try
		{
			conn = DBUtil.getInstance().getConn();

			String countSql = "SELECT COUNT(*)COUNT FROM( SELECT TB_CARD.CARDID,TB_CARD.CARDHEAD,TB_CARD.CARDNUM,TB_COLLACARD.APPLYPERSONID,TB_COLLACARD.APPLYDATE,TB_COLLACARD.AUDITDATE,TB_STATE.STATENAME FROM TB_CARD,TB_CC,TB_COLLACARD,TB_STATE WHERE TB_CARD.CARDID=TB_CC.CARDID AND TB_CC.COLLACARDID=TB_COLLACARD.COLLACARDID AND TB_CARD.STATEID=TB_STATE.STATEID)T1 LEFT JOIN TB_PATIENT ON T1.CARDID=TB_PATIENT.CARDID WHERE APPLYPERSONID=" + applyPersonID;
			searchSql1 = "SELECT T1.*,TB_PATIENT.PATIENTNAME,TB_PATIENT.PATIENTMONEY FROM( SELECT TB_CARD.CARDID,TB_CARD.CARDHEAD,TB_CARD.CARDNUM,TB_COLLACARD.APPLYPERSONID,TB_COLLACARD.APPLYDATE,TB_COLLACARD.AUDITDATE,TB_COLLACARD.AUDITPERSONNAME,TB_STATE.STATENAME FROM TB_CARD,TB_CC,TB_COLLACARD,TB_STATE WHERE TB_CARD.CARDID=TB_CC.CARDID AND TB_CC.COLLACARDID=TB_COLLACARD.COLLACARDID AND TB_CARD.STATEID=TB_STATE.STATEID)T1 LEFT JOIN TB_PATIENT ON T1.CARDID=TB_PATIENT.CARDID WHERE APPLYPERSONID=" + applyPersonID;

			if (cardHead != null && cardHead.length() > 0)
			{
				countSql += " AND T1.CARDHEAD ='" + cardHead + "'";
				searchSql1 += " AND T1.CARDHEAD ='" + cardHead + "'";
			}
			if (startNum != null && startNum.length() > 0 && endNum != null && endNum.length() > 0)
			{
				countSql += " AND T1.CARDNUM BETWEEN  " + startNum + " and " + endNum;
				searchSql1 += " AND T1.CARDNUM BETWEEN  " + startNum + " and " + endNum;
			}
			if (startTime != null && startTime.length() > 0 && endTime != null && endTime.length() > 0)
			{
				countSql += " AND T1.APPLYDATE BETWEEN  '" + startTime + "' and '" + endTime + "'";
				searchSql1 += " AND T1.APPLYDATE BETWEEN  '" + startTime + "' and '" + endTime + "'";
			}


			qr = new QueryRunner();
			Map<String, Object> map = qr.query(conn, countSql, new MapHandler());
			BigDecimal count = (BigDecimal) map.get("COUNT");
			int x = count.intValue();
			int countPage = x % 4 == 0 ? x / 4 : x / 4 + 1;
			BigDecimal b = new BigDecimal(countPage);
			pageSearchInf.setCount(b);
			String searchSql2 = "SELECT * FROM  (SELECT ROWNUM RN ,A.* FROM  (" + searchSql1 + "ORDER BY T1.CARDID DESC ) A ) B WHERE B.RN  BETWEEN " + ((StartNum - 1) * 4 + 1) + " AND " + StartNum * 4;

			List<TbCard> list = qr.query(conn, searchSql2, new BeanListHandler<>(TbCard.class));
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

	//卡充值
	public boolean CardRecharge(String patientID, String rechargMoney)
	{
		boolean flag = false;
		System.out.println(patientID);
		System.out.println(rechargMoney);
		Connection conn = DBUtil.getInstance().getConn();
		String sql = null;
		if (patientID != null && rechargMoney != null)
		{
			sql = "UPDATE TB_PATIENT SET PATIENTMONEY = '" + rechargMoney + "' WHERE PATIENTID=" + patientID;
		}
		try
		{
			conn.setAutoCommit(false);
			QueryRunner qr = new QueryRunner();
			int num = qr.update(conn, sql);
			System.out.println("num=" + num);
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


	//卡注销分页
	public TbPageSearch cancelPageSearch(String cardHead, String startNum, String endNum, String cardState, String startTime, String endTime, int StartNum)
	{
		TbPageSearch pageSearchInf = new TbPageSearch();
		pageSearchInf.setStart(StartNum);
		Connection conn = null;
		String searchSql1 = null;
		QueryRunner qr = null;
		try
		{
			conn = DBUtil.getInstance().getConn();
			String countSql = "SELECT COUNT(*)COUNT FROM  TB_CARD,TB_STATE WHERE TB_CARD.STATEID=TB_STATE.STATEID AND TB_STATE.STATENAME='待销售' ";
			searchSql1 = "SELECT TB_CARD.*,TB_STATE.STATENAME FROM  TB_CARD,TB_STATE WHERE TB_CARD.STATEID=TB_STATE.STATEID AND TB_STATE.STATENAME='待销售' ";

			if (cardHead != null && cardHead.length() > 0)
			{
				countSql += " AND TB_CARD.CARDHEAD ='" + cardHead + "'";
				searchSql1 += " AND TB_CARD.CARDHEAD ='" + cardHead + "'";
			}
			if (startNum != null && startNum.length() > 0 && endNum != null && endNum.length() > 0)
			{
				countSql += " AND TB_CARD.CARDNUM BETWEEN  " + startNum + " and " + endNum;
				searchSql1 += " AND TB_CARD.CARDNUM BETWEEN  " + startNum + " and " + endNum;
			}
			if (startTime != null && startTime.length() > 0 && endTime != null && endTime.length() > 0)
			{
				countSql += " AND TB_CARD.CARDTIME BETWEEN  '" + startTime + "' and '" + endTime + "'";
				searchSql1 += " AND TB_CARD.CARDTIME BETWEEN  '" + startTime + "' and '" + endTime + "'";
			}
			if (cardState != null && cardState.length() > 0)
			{
				countSql += " AND TB_CARD.STATEID=" + cardState;
				searchSql1 += " AND TB_CARD.STATEID=" + cardState;
			}

			qr = new QueryRunner();
			Map<String, Object> map = qr.query(conn, countSql, new MapHandler());
			BigDecimal count = (BigDecimal) map.get("COUNT");
			int x = count.intValue();
			int countPage = x % 4 == 0 ? x / 4 : x / 4 + 1;
			BigDecimal b = new BigDecimal(countPage);
			pageSearchInf.setCount(b);
			String searchSql2 = "SELECT * FROM  (SELECT ROWNUM RN ,A.* FROM  (" + searchSql1 + "ORDER BY TB_CARD.CARDTIME DESC,TB_CARD.CARDNUM ASC ) A ) B WHERE B.RN  BETWEEN " + ((StartNum - 1) * 4 + 1) + " AND " + StartNum * 4;

			List<TbCard> list = qr.query(conn, searchSql2, new BeanListHandler<>(TbCard.class));
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


	public TbCard getCard()
	{
		return card;
	}

	public void setCard(TbCard card)
	{
		this.card = card;
	}
}
