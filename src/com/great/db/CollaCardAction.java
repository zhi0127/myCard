package com.great.db;

import com.great.javabean.TbCard;
import com.great.javabean.TbCollaCard;
import com.great.javabean.TbPageSearch;
import com.great.javabean.TbSelectType;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.MapHandler;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class CollaCardAction
{
	private TbCollaCard collaCard;


	//申请分页
	public TbPageSearch PageSearch(String applyPersonID, String auditState, String startTime, String endTime, int StartNum)
	{
		TbPageSearch pageSearchInf = new TbPageSearch();
		pageSearchInf.setStart(StartNum);
		Connection conn = null;
		String searchSql1 = null;
		QueryRunner qr = null;
		try
		{
			conn = DBUtil.getInstance().getConn();

			String countSql = "SELECT COUNT(*)COUNT FROM  TB_COLLACARD,TB_STATE WHERE TB_COLLACARD.STATEID=TB_STATE.STATEID ";
			searchSql1 = "SELECT TB_COLLACARD.*,TB_STATE.STATENAME FROM  TB_COLLACARD,TB_STATE WHERE TB_COLLACARD.STATEID=TB_STATE.STATEID ";

			if (applyPersonID != null && applyPersonID.length() > 0)
			{
				countSql += "  AND TB_COLLACARD.APPLYPERSONID=" + applyPersonID;
				searchSql1 += " AND TB_COLLACARD.APPLYPERSONID=" + applyPersonID;
			}

			if (startTime != null && startTime.length() > 0 && endTime != null && endTime.length() > 0)
			{
				countSql += " AND TB_COLLACARD.APPLYDATE BETWEEN  '" + startTime + "' and '" + endTime + "'";
				searchSql1 += " AND TB_COLLACARD.APPLYDATE BETWEEN  '" + startTime + "' and '" + endTime + "'";
			}
			if (auditState != null && auditState.length() > 0)
			{
				countSql += " AND TB_COLLACARD.STATEID=" + auditState;
				searchSql1 += " AND TB_COLLACARD.STATEID=" + auditState;
			}

			qr = new QueryRunner();
			Map<String, Object> map = qr.query(conn, countSql, new MapHandler());
			BigDecimal count = (BigDecimal) map.get("COUNT");
			int x = count.intValue();
			int countPage = x % 4 == 0 ? x / 4 : x / 4 + 1;
			BigDecimal b = new BigDecimal(countPage);
			pageSearchInf.setCount(b);
			String searchSql2 = "SELECT * FROM  (SELECT ROWNUM RN ,A.* FROM  (" + searchSql1 + " ORDER BY TB_COLLACARD.APPLYDATE DESC ) A ) B WHERE B.RN  BETWEEN " + ((StartNum - 1) * 4 + 1) + " AND " + StartNum * 4;

			List<TbCollaCard> list = qr.query(conn, searchSql2, new BeanListHandler<>(TbCollaCard.class));
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

	//修改申请卡数量
	public boolean updateApplyNumType(String collaCardID, String updateApplyNum)
	{
		boolean flag = false;
		Connection conn = DBUtil.getInstance().getConn();
		String sql = null;
		if (updateApplyNum != null && updateApplyNum.length() > 0)
		{
			sql = "UPDATE TB_COLLACARD SET APPLYNUM = " + updateApplyNum + " WHERE COLLACARDID = " + collaCardID;
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
			String sql = "SELECT  DISTINCT APPLYPERSONID,APPLYPERSONNAME FROM TB_COLLACARD";

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

	//申请卡
	public boolean addApplyCard(String cardHead, String auditState, String applyDate, String applyNum, String applyPersonID, String applyPersonName)
	{
		boolean flag = false;
		Connection conn = DBUtil.getInstance().getConn();
		String sql = "INSERT INTO TB_COLLACARD (CARDHEAD,STATEID, APPLYDATE, APPLYNUM, APPLYPERSONID, APPLYPERSONNAME) VALUES ('" + cardHead + "', '" + auditState + "', '" + applyDate + "', '" + applyNum + "', '" + applyPersonID + "', '" + applyPersonName + "')";

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

	//获取待领取卡片数量
	public int getCardCount(String cardHead)
	{
		int cardCount = 0;
		Connection conn = null;
		QueryRunner qr = null;
		try
		{
			conn = DBUtil.getInstance().getConn();
			String sqlCount = "SELECT COUNT(*) CARDCOUNT  FROM TB_CARD WHERE STATEID=4";
			if (cardHead != null && cardHead.length() > 0)
			{
				sqlCount += "AND CARDHEAD='" + cardHead + "'";
			}


			qr = new QueryRunner();
			TbCard card = qr.query(conn, sqlCount, new BeanHandler<>(TbCard.class));
			cardCount = card.getCARDCOUNT();
			System.out.println(cardCount);

		} catch (SQLException e)
		{
			e.printStackTrace();
		} finally
		{
			DBUtil.getInstance().close(conn);
		}
		return cardCount;
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
			qr = new QueryRunner();
			String sqlMin = "SELECT MIN(CARDNUM) CARDNUM FROM TB_CARD WHERE STATEID=4 ";
			if (cardHead != null && cardHead.length() > 0)
			{
				sqlMin += " AND CARDHEAD='" + cardHead + "'";
			}

			TbCard card = qr.query(conn, sqlMin, new BeanHandler<>(TbCard.class));
			startCardNum = card.getCARDNUM();
			card.getCARDHEAD();

		} catch (SQLException e)
		{
			e.printStackTrace();
		} finally
		{
			DBUtil.getInstance().close(conn);
		}
		return startCardNum;
	}


	//审核通过
	public boolean approvalPass(String AUDITPERSONID, String AUDITPERSONNAME, String AUDITDATE, String COLLACARDID)
	{
		boolean flag = false;
		Connection conn = DBUtil.getInstance().getConn();
		String sql = "UPDATE TB_COLLACARD SET STATEID = '7', AUDITPERSONID = '" + AUDITPERSONID + "', AUDITPERSONNAME = '" + AUDITPERSONNAME + "', AUDITDATE = '" + AUDITDATE + "' WHERE  COLLACARDID=" + COLLACARDID;
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

	//	//设置卡为已领用,记录领卡审核通过的卡
	public boolean cardApproved(String COLLACARDID, String cardHead, int startCardNum, int applyNum)
	{
		boolean flag = false;
		Connection conn = DBUtil.getInstance().getConn();
		int num = 0;
		try
		{
			conn.setAutoCommit(false);
			QueryRunner qr = new QueryRunner();
			String sqlCard = "SELECT * FROM TB_CARD WHERE STATEID=4 AND CARDHEAD='" + cardHead + "' ORDER BY CARDNUM";
			List<TbCard> cardList = qr.query(conn, sqlCard, new BeanListHandler<>(TbCard.class));
			System.out.println("cardList" + cardList);


			for (int i = 0; i < applyNum; i++)
			{
				System.out.println("审批的卡" + cardList.get(i).getCARDID());
				String sql = "UPDATE TB_CARD SET STATEID = '8' WHERE CARDID=" + cardList.get(i).getCARDID();
				num = qr.update(conn, sql);
			}

			if (num > 0)
			{
				if (cardList.size() > 0)
				{
					String addSql1 = "INSERT ALL ";
					for (int i = 0; i < applyNum; i++)
					{
						addSql1 += " INTO TB_CC (COLLACARDID, CARDID) VALUES ('" + COLLACARDID + "', '" + cardList.get(i).getCARDID() + "')";
					}
					String addSql2 = addSql1 + "SELECT 1 FROM DUAL";
					int addSqlNum = qr.update(conn, addSql2);
					if (addSqlNum > 0)
					{
						flag = true;

					}
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


	//查看领用卡号
	public List<TbCard> getCreditCard(String collaCardID)
	{
		Connection conn = null;
		List<TbCard> list = null;
		QueryRunner qr = null;
		try
		{
			conn = DBUtil.getInstance().getConn();
			String sql = "SELECT TB_CARD.CARDHEAD,TB_CARD.CARDNUM FROM TB_CC,TB_CARD WHERE TB_CC.CARDID=TB_CARD.CARDID AND COLLACARDID=" + collaCardID + " ORDER BY TB_CARD.CARDNUM";

			qr = new QueryRunner();
			list = qr.query(conn, sql, new BeanListHandler<>(TbCard.class));


		} catch (SQLException e)
		{
			e.printStackTrace();
		} finally
		{
			DBUtil.getInstance().close(conn);
		}
		return list;
	}


	public TbCollaCard getCollaCard()
	{
		return collaCard;
	}

	public void setCollaCard(TbCollaCard collaCard)
	{
		this.collaCard = collaCard;
	}
}
