package com.great.javabean;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.*;


public class Tool
{

	public static Date getDateType(String startDate)
	{
		Date date = null;
		try
		{
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			date = formatter.parse(startDate);
		} catch (ParseException e)
		{
			e.printStackTrace();
		}
		return date;
	}

	public static List<String> getDateType(List<Date> typeDate)
	{
		List<String> dateList = new ArrayList<>();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		for (int i = 0; i < typeDate.size(); i++)
		{
			String date = formatter.format(typeDate.get(i));
			dateList.add(date);
		}
		return dateList;
	}

	//获取上一周的日期
	public static Date getLastWeekMonday(Date date)
	{
		Calendar cal = Calendar.getInstance();
		cal.setTime(getThisWeekMonday(date));
		cal.add(Calendar.DATE, -7);
		return cal.getTime();
	}

	//获取下一周的日期
	public static Date getNextWeekMonday(Date date)
	{
		Calendar cal = Calendar.getInstance();
		cal.setTime(getThisWeekMonday(date));
		cal.add(Calendar.DATE, 7);
		return cal.getTime();
	}

	private static Date getThisWeekMonday(Date date)
	{
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		// 获得当前日期是一个星期的第几天
		int dayWeek = cal.get(Calendar.DAY_OF_WEEK);
		if (1 == dayWeek)
		{
			cal.add(Calendar.DAY_OF_MONTH, -1);
		}
		// 设置一个星期的第一天，按中国的习惯一个星期的第一天是星期一
		cal.setFirstDayOfWeek(Calendar.MONDAY);
		// 获得当前日期是一个星期的第几天
		int day = cal.get(Calendar.DAY_OF_WEEK);
		// 根据日历的规则，给当前日期减去星期几与一个星期第一天的差值
		cal.add(Calendar.DATE, cal.getFirstDayOfWeek() - day);
		return cal.getTime();
	}

	//获取本日所在周
	public static List<Date> dateToWeek(Date mdate)
	{
		int b = mdate.getDay();
		Date fdate;
		List<Date> list = new ArrayList<Date>();
		Long fTime = mdate.getTime() - b * 24 * 3600000;
		for (int a = 1; a <= 7; a++)
		{
			fdate = new Date();
			fdate.setTime(fTime + (a * 24 * 3600000));
			list.add(a - 1, fdate);
		}
		return list;
	}

}
