package com.great.javabean;

import oracle.sql.CHAR;

import java.math.BigDecimal;

public class TbSchedule
{
	private BigDecimal SCHEDULEID;
	private BigDecimal PATIENTID;
	private BigDecimal CARDID;
	private BigDecimal PERSONID;
	private String SCHEDULEDATE;
	private String SCHEDULETIME;
	private String APPOINTMENTDATE;
	private String PERSONNAME;
	private String DEPARTNAME;
	private String PATIENTNAME;

	public TbSchedule()
	{
	}

	public TbSchedule(BigDecimal SCHEDULEID, BigDecimal PATIENTID, BigDecimal CARDID, BigDecimal PERSONID, String SCHEDULEDATE, String SCHEDULETIME, String APPOINTMENTDATE, String PERSONNAME, String DEPARTNAME, String PATIENTNAME)
	{
		this.SCHEDULEID = SCHEDULEID;
		this.PATIENTID = PATIENTID;
		this.CARDID = CARDID;
		this.PERSONID = PERSONID;
		this.SCHEDULEDATE = SCHEDULEDATE;
		this.SCHEDULETIME = SCHEDULETIME;
		this.APPOINTMENTDATE = APPOINTMENTDATE;
		this.PERSONNAME = PERSONNAME;
		this.DEPARTNAME = DEPARTNAME;
		this.PATIENTNAME = PATIENTNAME;
	}

	public BigDecimal getSCHEDULEID()
	{
		return SCHEDULEID;
	}

	public void setSCHEDULEID(BigDecimal SCHEDULEID)
	{
		this.SCHEDULEID = SCHEDULEID;
	}

	public BigDecimal getPATIENTID()
	{
		return PATIENTID;
	}

	public void setPATIENTID(BigDecimal PATIENTID)
	{
		this.PATIENTID = PATIENTID;
	}

	public BigDecimal getCARDID()
	{
		return CARDID;
	}

	public void setCARDID(BigDecimal CARDID)
	{
		this.CARDID = CARDID;
	}

	public BigDecimal getPERSONID()
	{
		return PERSONID;
	}

	public void setPERSONID(BigDecimal PERSONID)
	{
		this.PERSONID = PERSONID;
	}

	public String getSCHEDULEDATE()
	{
		return SCHEDULEDATE;
	}

	public void setSCHEDULEDATE(String SCHEDULEDATE)
	{
		this.SCHEDULEDATE = SCHEDULEDATE;
	}

	public String getSCHEDULETIME()
	{
		return SCHEDULETIME;
	}

	public void setSCHEDULETIME(String SCHEDULETIME)
	{
		this.SCHEDULETIME = SCHEDULETIME;
	}

	public String getAPPOINTMENTDATE()
	{
		return APPOINTMENTDATE;
	}

	public void setAPPOINTMENTDATE(String APPOINTMENTDATE)
	{
		this.APPOINTMENTDATE = APPOINTMENTDATE;
	}

	public String getPERSONNAME()
	{
		return PERSONNAME;
	}

	public void setPERSONNAME(String PERSONNAME)
	{
		this.PERSONNAME = PERSONNAME;
	}

	public String getDEPARTNAME()
	{
		return DEPARTNAME;
	}

	public void setDEPARTNAME(String DEPARTNAME)
	{
		this.DEPARTNAME = DEPARTNAME;
	}

	public String getPATIENTNAME()
	{
		return PATIENTNAME;
	}

	public void setPATIENTNAME(String PATIENTNAME)
	{
		this.PATIENTNAME = PATIENTNAME;
	}
}
