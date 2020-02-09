package com.great.javabean;


import java.math.BigDecimal;

public class TbUser
{
	private BigDecimal USERID;
	private String USERNAME;
	private String USERPASS;
	private String USERSEX;
	private BigDecimal USERTEL;
	private String USERSTATE;
	private String USERADDRESS;

	public TbUser()
	{
	}

	public TbUser(BigDecimal USERID, String USERNAME, String USERPASS, String USERSEX, BigDecimal USERTEL, String USERSTATENUMBER, String USERADDRESS)
	{
		this.USERID = USERID;
		this.USERNAME = USERNAME;
		this.USERPASS = USERPASS;
		this.USERSEX = USERSEX;
		this.USERTEL = USERTEL;
		this.USERSTATE = USERSTATENUMBER;
		this.USERADDRESS = USERADDRESS;
	}

	public BigDecimal getUSERID()
	{
		return USERID;
	}

	public void setUSERID(BigDecimal USERID)
	{
		this.USERID = USERID;
	}

	public String getUSERNAME()
	{
		return USERNAME;
	}

	public void setUSERNAME(String USERNAME)
	{
		this.USERNAME = USERNAME;
	}

	public String getUSERPASS()
	{
		return USERPASS;
	}

	public void setUSERPASS(String USERPASS)
	{
		this.USERPASS = USERPASS;
	}

	public String getUSERSEX()
	{
		return USERSEX;
	}

	public void setUSERSEX(String USERSEX)
	{
		this.USERSEX = USERSEX;
	}

	public BigDecimal getUSERTEL()
	{
		return USERTEL;
	}

	public void setUSERTEL(BigDecimal USERTEL)
	{
		this.USERTEL = USERTEL;
	}

	public String getUSERSTATE()
	{
		return USERSTATE;
	}

	public void setUSERSTATE(String USERSTATE)
	{
		this.USERSTATE = USERSTATE;
	}

	public String getUSERADDRESS()
	{
		return USERADDRESS;
	}

	public void setUSERADDRESS(String USERADDRESS)
	{
		this.USERADDRESS = USERADDRESS;
	}
}
