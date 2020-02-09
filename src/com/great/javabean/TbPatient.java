package com.great.javabean;

import java.math.BigDecimal;

public class TbPatient
{
	private BigDecimal PATIENTID;
	private BigDecimal CARDID;
	private String PATIENTNAME;
	private BigDecimal PATIENTAGE;
	private BigDecimal PATIENTWEEK;
	private String PATIENTSEXY;
	private String PATIENTBIRTHPLACE;
	private String PATIENTIDENTITY;
	private String PATIENTPHONE;
	private String PATIENTADRESS;
	private BigDecimal PATIENTMONEY;
	private String RN;

	public TbPatient()
	{
	}

	public TbPatient(BigDecimal PATIENTID, BigDecimal CARDID, String PATIENTNAME, BigDecimal PATIENTAGE, BigDecimal PATIENTWEEK, String PATIENTSEXY, String PATIENTBIRTHPLACE, String PATIENTIDENTITY, String PATIENTPHONE, String PATIENTADRESS, BigDecimal PATIENTMONEY, String RN)
	{
		this.PATIENTID = PATIENTID;
		this.CARDID = CARDID;
		this.PATIENTNAME = PATIENTNAME;
		this.PATIENTAGE = PATIENTAGE;
		this.PATIENTWEEK = PATIENTWEEK;
		this.PATIENTSEXY = PATIENTSEXY;
		this.PATIENTBIRTHPLACE = PATIENTBIRTHPLACE;
		this.PATIENTIDENTITY = PATIENTIDENTITY;
		this.PATIENTPHONE = PATIENTPHONE;
		this.PATIENTADRESS = PATIENTADRESS;
		this.PATIENTMONEY = PATIENTMONEY;
		this.RN = RN;
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

	public String getPATIENTNAME()
	{
		return PATIENTNAME;
	}

	public void setPATIENTNAME(String PATIENTNAME)
	{
		this.PATIENTNAME = PATIENTNAME;
	}

	public BigDecimal getPATIENTAGE()
	{
		return PATIENTAGE;
	}

	public void setPATIENTAGE(BigDecimal PATIENTAGE)
	{
		this.PATIENTAGE = PATIENTAGE;
	}

	public BigDecimal getPATIENTWEEK()
	{
		return PATIENTWEEK;
	}

	public void setPATIENTWEEK(BigDecimal PATIENTWEEK)
	{
		this.PATIENTWEEK = PATIENTWEEK;
	}

	public String getPATIENTSEXY()
	{
		return PATIENTSEXY;
	}

	public void setPATIENTSEXY(String PATIENTSEXY)
	{
		this.PATIENTSEXY = PATIENTSEXY;
	}

	public String getPATIENTBIRTHPLACE()
	{
		return PATIENTBIRTHPLACE;
	}

	public void setPATIENTBIRTHPLACE(String PATIENTBIRTHPLACE)
	{
		this.PATIENTBIRTHPLACE = PATIENTBIRTHPLACE;
	}

	public String getPATIENTIDENTITY()
	{
		return PATIENTIDENTITY;
	}

	public void setPATIENTIDENTITY(String PATIENTIDENTITY)
	{
		this.PATIENTIDENTITY = PATIENTIDENTITY;
	}

	public String getPATIENTPHONE()
	{
		return PATIENTPHONE;
	}

	public void setPATIENTPHONE(String PATIENTPHONE)
	{
		this.PATIENTPHONE = PATIENTPHONE;
	}

	public String getPATIENTADRESS()
	{
		return PATIENTADRESS;
	}

	public void setPATIENTADRESS(String PATIENTADRESS)
	{
		this.PATIENTADRESS = PATIENTADRESS;
	}

	public BigDecimal getPATIENTMONEY()
	{
		return PATIENTMONEY;
	}

	public void setPATIENTMONEY(BigDecimal PATIENTMONEY)
	{
		this.PATIENTMONEY = PATIENTMONEY;
	}

	public String getRN()
	{
		return RN;
	}

	public void setRN(String RN)
	{
		this.RN = RN;
	}
}
