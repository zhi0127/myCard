package com.great.javabean;


import java.math.BigDecimal;
import java.util.List;

public class TbSelectType
{
	private List departmentList;
	private List roleList;
	private List cardHeadList;
	private List collaCardList;
	private List doctorsList;


	public TbSelectType()
	{
	}

	public TbSelectType(List departmentList, List roleList, List cardHeadList, List collaCardList, List doctorsList)
	{
		this.departmentList = departmentList;
		this.roleList = roleList;
		this.cardHeadList = cardHeadList;
		this.collaCardList = collaCardList;
		this.doctorsList = doctorsList;
	}

	public List getDepartmentList()
	{
		return departmentList;
	}

	public void setDepartmentList(List departmentList)
	{
		this.departmentList = departmentList;
	}

	public List getRoleList()
	{
		return roleList;
	}

	public void setRoleList(List roleList)
	{
		this.roleList = roleList;
	}

	public List getCardHeadList()
	{
		return cardHeadList;
	}

	public void setCardHeadList(List cardHeadList)
	{
		this.cardHeadList = cardHeadList;
	}

	public List getCollaCardList()
	{
		return collaCardList;
	}

	public void setCollaCardList(List collaCardList)
	{
		this.collaCardList = collaCardList;
	}

	public List getDoctorsList()
	{
		return doctorsList;
	}

	public void setDoctorsList(List doctorsList)
	{
		this.doctorsList = doctorsList;
	}
}
