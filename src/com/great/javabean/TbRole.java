package com.great.javabean;

import java.math.BigDecimal;

public class TbRole
{
	private BigDecimal ROLEID;
	private String ROLENAME;

	public TbRole()
	{
	}

	public TbRole(BigDecimal ROLEID, String ROLENAME)
	{
		this.ROLEID = ROLEID;
		this.ROLENAME = ROLENAME;
	}

	public BigDecimal getROLEID()
	{
		return ROLEID;
	}

	public void setROLEID(BigDecimal ROLEID)
	{
		this.ROLEID = ROLEID;
	}

	public String getROLENAME()
	{
		return ROLENAME;
	}

	public void setROLENAME(String ROLENAME)
	{
		this.ROLENAME = ROLENAME;
	}
}
