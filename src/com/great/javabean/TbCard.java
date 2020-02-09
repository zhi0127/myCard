package com.great.javabean;

import java.math.BigDecimal;

public class TbCard
{
	private BigDecimal CARDID;
	private BigDecimal STATEID;
	private String CARDHEAD;
	private String CARDNUM;
	private String CARDTIME;
	private String STATENAME;
	private int CARDCOUNT;
	private String RN;
	private String PATIENTMONEY;
	private String PATIENTNAME;
	private String APPLYPERSONNAME;
	private String APPLYDATE;
	private String CARDSELLER;
	private String CARDSALESTIME;
	private String AUDITDATE;
	private String AUDITPERSONNAME;


	public TbCard()
	{
	}

	public TbCard(BigDecimal CARDID, BigDecimal STATEID, String CARDHEAD, String CARDNUM, String CARDTIME, String STATENAME, int CARDCOUNT, String RN, String PATIENTMONEY, String PATIENTNAME, String APPLYPERSONNAME, String APPLYDATE, String CARDSELLER, String CARDSALESTIME, String AUDITDATE, String AUDITPERSONNAME)
	{
		this.CARDID = CARDID;
		this.STATEID = STATEID;
		this.CARDHEAD = CARDHEAD;
		this.CARDNUM = CARDNUM;
		this.CARDTIME = CARDTIME;
		this.STATENAME = STATENAME;
		this.CARDCOUNT = CARDCOUNT;
		this.RN = RN;
		this.PATIENTMONEY = PATIENTMONEY;
		this.PATIENTNAME = PATIENTNAME;
		this.APPLYPERSONNAME = APPLYPERSONNAME;
		this.APPLYDATE = APPLYDATE;
		this.CARDSELLER = CARDSELLER;
		this.CARDSALESTIME = CARDSALESTIME;
		this.AUDITDATE = AUDITDATE;
		this.AUDITPERSONNAME = AUDITPERSONNAME;
	}

	public BigDecimal getCARDID()
	{
		return CARDID;
	}

	public void setCARDID(BigDecimal CARDID)
	{
		this.CARDID = CARDID;
	}

	public BigDecimal getSTATEID()
	{
		return STATEID;
	}

	public void setSTATEID(BigDecimal STATEID)
	{
		this.STATEID = STATEID;
	}

	public String getCARDHEAD()
	{
		return CARDHEAD;
	}

	public void setCARDHEAD(String CARDHEAD)
	{
		this.CARDHEAD = CARDHEAD;
	}

	public String getCARDNUM()
	{
		return CARDNUM;
	}

	public void setCARDNUM(String CARDNUM)
	{
		this.CARDNUM = CARDNUM;
	}

	public String getCARDTIME()
	{
		return CARDTIME;
	}

	public void setCARDTIME(String CARDTIME)
	{
		this.CARDTIME = CARDTIME;
	}

	public String getRN()
	{
		return RN;
	}

	public void setRN(String RN)
	{
		this.RN = RN;
	}

	public String getSTATENAME()
	{
		return STATENAME;
	}

	public void setSTATENAME(String STATENAME)
	{
		this.STATENAME = STATENAME;
	}

	public int getCARDCOUNT()
	{
		return CARDCOUNT;
	}

	public void setCARDCOUNT(int CARDCOUNT)
	{
		this.CARDCOUNT = CARDCOUNT;
	}

	public String getPATIENTMONEY()
	{
		return PATIENTMONEY;
	}

	public void setPATIENTMONEY(String PATIENTMONEY)
	{
		this.PATIENTMONEY = PATIENTMONEY;
	}

	public String getPATIENTNAME()
	{
		return PATIENTNAME;
	}

	public void setPATIENTNAME(String PATIENTNAME)
	{
		this.PATIENTNAME = PATIENTNAME;
	}

	public String getAPPLYPERSONNAME()
	{
		return APPLYPERSONNAME;
	}

	public void setAPPLYPERSONNAME(String APPLYPERSONNAME)
	{
		this.APPLYPERSONNAME = APPLYPERSONNAME;
	}

	public String getAPPLYDATE()
	{
		return APPLYDATE;
	}

	public void setAPPLYDATE(String APPLYDATE)
	{
		this.APPLYDATE = APPLYDATE;
	}

	public String getCARDSELLER()
	{
		return CARDSELLER;
	}

	public void setCARDSELLER(String CARDSELLER)
	{
		this.CARDSELLER = CARDSELLER;
	}

	public String getCARDSALESTIME()
	{
		return CARDSALESTIME;
	}

	public void setCARDSALESTIME(String CARDSALESTIME)
	{
		this.CARDSALESTIME = CARDSALESTIME;
	}

	public String getAUDITDATE()
	{
		return AUDITDATE;
	}

	public void setAUDITDATE(String AUDITDATE)
	{
		this.AUDITDATE = AUDITDATE;
	}

	public String getAUDITPERSONNAME()
	{
		return AUDITPERSONNAME;
	}

	public void setAUDITPERSONNAME(String AUDITPERSONNAME)
	{
		this.AUDITPERSONNAME = AUDITPERSONNAME;
	}
}
