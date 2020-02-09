package com.great.javabean;

import java.math.BigDecimal;

public class TbState
{
	private BigDecimal STATEID;
	private String STATENAME;

	public TbState()
	{
	}

	public TbState(BigDecimal STATEID, String STATENAME)
	{
		this.STATEID = STATEID;
		this.STATENAME = STATENAME;
	}

	public BigDecimal getSTATEID()
	{
		return STATEID;
	}

	public void setSTATEID(BigDecimal STATEID)
	{
		this.STATEID = STATEID;
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
