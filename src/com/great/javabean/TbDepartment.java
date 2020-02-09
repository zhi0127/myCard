package com.great.javabean;

import java.math.BigDecimal;

public class TbDepartment
{
	private BigDecimal DEPARTID;
	private String DEPARTNAME;


	public TbDepartment()
	{
	}

	public TbDepartment(BigDecimal DEPARTID, String DEPARTNAME)
	{
		this.DEPARTID = DEPARTID;
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

	public String getDEPARTNAME()
	{
		return DEPARTNAME;
	}

	public void setDEPARTNAME(String DEPARTNAME)
	{
		this.DEPARTNAME = DEPARTNAME;
	}
}
