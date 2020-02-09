package com.great.javabean;

import java.math.BigDecimal;
import java.util.List;

public class TbPageSearch
{
	private int start;
	private BigDecimal count;
	private List list;

	public int getStart()
	{
		return start;
	}

	public void setStart(int start)
	{
		this.start = start;
	}

	public BigDecimal getCount()
	{
		return count;
	}

	public void setCount(BigDecimal count)
	{
		this.count = count;
	}

	public List getList()
	{
		return list;
	}

	public void setList(List list)
	{
		this.list = list;
	}

	public TbPageSearch(int start, BigDecimal count, List list)
	{
		this.start = start;
		this.count = count;
		this.list = list;
	}

	public TbPageSearch()
	{
	}
}
