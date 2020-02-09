package com.great.javabean;

import oracle.sql.CHAR;

import java.math.BigDecimal;

public class TbCollaCard
{
	private String RN;
	private BigDecimal COLLACARDID;
	private BigDecimal STATEID;
	private String STATENAME;
	private String APPLYDATE;
	private String APPLYNUM;
	private BigDecimal APPLYPERSONID;
	private BigDecimal AUDITPERSONID;
	private String APPLYPERSONNAME;
	private String AUDITPERSONNAME;
	private String AUDITDATE;
	private String CARDHEAD;

	public TbCollaCard()
	{
	}

	public TbCollaCard(String RN, BigDecimal COLLACARDID, BigDecimal STATEID, String STATENAME, String APPLYDATE, String APPLYNUM, BigDecimal APPLYPERSONID, BigDecimal AUDITPERSONID, String APPLYPERSONNAME, String AUDITPERSONNAME, String AUDITDATE, String CARDHEAD)
	{
		this.RN = RN;
		this.COLLACARDID = COLLACARDID;
		this.STATEID = STATEID;
		this.STATENAME = STATENAME;
		this.APPLYDATE = APPLYDATE;
		this.APPLYNUM = APPLYNUM;
		this.APPLYPERSONID = APPLYPERSONID;
		this.AUDITPERSONID = AUDITPERSONID;
		this.APPLYPERSONNAME = APPLYPERSONNAME;
		this.AUDITPERSONNAME = AUDITPERSONNAME;
		this.AUDITDATE = AUDITDATE;
		this.CARDHEAD = CARDHEAD;
	}

	public String getRN()
	{
		return RN;
	}

	public void setRN(String RN)
	{
		this.RN = RN;
	}

	public BigDecimal getCOLLACARDID()
	{
		return COLLACARDID;
	}

	public void setCOLLACARDID(BigDecimal COLLACARDID)
	{
		this.COLLACARDID = COLLACARDID;
	}

	public BigDecimal getSTATEID()
	{
		return STATEID;
	}

	public void setSTATEID(BigDecimal STATEID)
	{
		this.STATEID = STATEID;
	}

	public String getAPPLYDATE()
	{
		return APPLYDATE;
	}

	public void setAPPLYDATE(String APPLYDATE)
	{
		this.APPLYDATE = APPLYDATE;
	}

	public String getAPPLYNUM()
	{
		return APPLYNUM;
	}

	public void setAPPLYNUM(String APPLYNUM)
	{
		this.APPLYNUM = APPLYNUM;
	}

	public BigDecimal getAPPLYPERSONID()
	{
		return APPLYPERSONID;
	}

	public void setAPPLYPERSONID(BigDecimal APPLYPERSONID)
	{
		this.APPLYPERSONID = APPLYPERSONID;
	}

	public BigDecimal getAUDITPERSONID()
	{
		return AUDITPERSONID;
	}

	public void setAUDITPERSONID(BigDecimal AUDITPERSONID)
	{
		this.AUDITPERSONID = AUDITPERSONID;
	}

	public String getAPPLYPERSONNAME()
	{
		return APPLYPERSONNAME;
	}

	public void setAPPLYPERSONNAME(String APPLYPERSONNAME)
	{
		this.APPLYPERSONNAME = APPLYPERSONNAME;
	}

	public String getAUDITPERSONNAME()
	{
		return AUDITPERSONNAME;
	}

	public void setAUDITPERSONNAME(String AUDITPERSONNAME)
	{
		this.AUDITPERSONNAME = AUDITPERSONNAME;
	}

	public String getAUDITDATE()
	{
		return AUDITDATE;
	}

	public void setAUDITDATE(String AUDITDATE)
	{
		this.AUDITDATE = AUDITDATE;
	}

	public String getSTATENAME()
	{
		return STATENAME;
	}

	public String getCARDHEAD()
	{
		return CARDHEAD;
	}

	public void setCARDHEAD(String CARDHEAD)
	{
		this.CARDHEAD = CARDHEAD;
	}

	public void setSTATENAME(String STATENAME)
	{
		this.STATENAME = STATENAME;
	}
}
