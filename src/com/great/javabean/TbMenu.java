package com.great.javabean;

import java.math.BigDecimal;

public class TbMenu
{

	private BigDecimal MENUID;
	private String prentName;
	private String childName;
	private String MENUURL;

	public TbMenu()
	{
	}

	public TbMenu(BigDecimal MENUID, String prentName, String childName, String MENUURL)
	{
		this.MENUID = MENUID;
		this.prentName = prentName;
		this.childName = childName;
		this.MENUURL = MENUURL;
	}

	public BigDecimal getMENUID()
	{
		return MENUID;
	}

	public void setMENUID(BigDecimal MENUID)
	{
		this.MENUID = MENUID;
	}

	public String getPrentName()
	{
		return prentName;
	}

	public void setPrentName(String prentName)
	{
		this.prentName = prentName;
	}

	public String getChildName()
	{
		return childName;
	}

	public void setChildName(String childName)
	{
		this.childName = childName;
	}

	public String getMENUURL()
	{
		return MENUURL;
	}

	public void setMENUURL(String MENUURL)
	{
		this.MENUURL = MENUURL;
	}
}
