package com.great.javabean;

import java.math.BigDecimal;

public class TbWorkPerson
{
	private BigDecimal PERSONID;
	private String PASSWORD;
	private String PERSONNAME;
	private BigDecimal DEPARTID;
	private String DEPARTNAME;
	private BigDecimal ROLEID;
	private String ROLENAME;
	private BigDecimal STATEID;
	private String STATENAME;
	private String RN;

	public TbWorkPerson()
	{
	}

	public TbWorkPerson(BigDecimal PERSONID, String PASSWORD, String PERSONNAME, BigDecimal DEPARTID, String DEPARTNAME, BigDecimal ROLEID, String ROLENAME, BigDecimal STATEID, String STATENAME, String RN)
	{
		this.PERSONID = PERSONID;
		this.PASSWORD = PASSWORD;
		this.PERSONNAME = PERSONNAME;
		this.DEPARTID = DEPARTID;
		this.DEPARTNAME = DEPARTNAME;
		this.ROLEID = ROLEID;
		this.ROLENAME = ROLENAME;
		this.STATEID = STATEID;
		this.STATENAME = STATENAME;
		this.RN = RN;
	}

	public String getRN()
	{
		return RN;
	}

	public void setRN(String RN)
	{
		this.RN = RN;
	}

	public BigDecimal getPERSONID()
	{
		return PERSONID;
	}

	public void setPERSONID(BigDecimal PERSONID)
	{
		this.PERSONID = PERSONID;
	}

	public String getPASSWORD()
	{
		return PASSWORD;
	}

	public void setPASSWORD(String PASSWORD)
	{
		this.PASSWORD = PASSWORD;
	}

	public String getDEPARTNAME()
	{
		return DEPARTNAME;
	}

	public void setDEPARTNAME(String DEPARTNAME)
	{
		this.DEPARTNAME = DEPARTNAME;
	}

	public BigDecimal getDEPARTID()
	{
		return DEPARTID;
	}

	public void setDEPARTID(BigDecimal DEPARTID)
	{
		this.DEPARTID = DEPARTID;
	}

	public BigDecimal getROLEID()
	{
		return ROLEID;
	}

	public void setROLEID(BigDecimal ROLEID)
	{
		this.ROLEID = ROLEID;
	}

	public BigDecimal getSTATEID()
	{
		return STATEID;
	}

	public void setSTATEID(BigDecimal STATEID)
	{
		this.STATEID = STATEID;
	}

	public String getPERSONNAME()
	{
		return PERSONNAME;
	}

	public void setPERSONNAME(String PERSONNAME)
	{
		this.PERSONNAME = PERSONNAME;
	}

	public String getROLENAME()
	{
		return ROLENAME;
	}

	public void setROLENAME(String ROLENAME)
	{
		this.ROLENAME = ROLENAME;
	}

	public String getSTATENAME()
	{
		return STATENAME;
	}

	public void setSTATENAME(String STATENAME)
	{
		this.STATENAME = STATENAME;
	}
}
