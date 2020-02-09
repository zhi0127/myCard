package com.great.servlet;

import com.great.db.WorkPersonAction;
import com.great.javabean.TbPageSearch;
import com.great.javabean.TbSchedule;
import com.great.javabean.TbSelectType;
import com.great.javabean.Tool;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;


@WebServlet(name = "FrontRegisteredServlet", urlPatterns = "/FrontRegisteredServlet")
public class FrontRegisteredServlet extends HttpServlet

{
	private WorkPersonAction workPersonAction;

	public FrontRegisteredServlet()
	{
		workPersonAction = new WorkPersonAction();
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{

		System.out.println("doPost");
		String doWhich = request.getParameter("doWhich");
		String nowDate = request.getParameter("now-Date");
		Date date = Tool.getDateType(nowDate);
		if ("上一周".equals(doWhich))
		{
			List<Date> dateList = Tool.dateToWeek(Tool.getLastWeekMonday(date));
			List<String> days = Tool.getDateType(dateList);
			Map<String, List<TbSchedule>> map = workPersonAction.getRegisteredTable(days);
			request.setAttribute("tableBody", map);
			request.setAttribute("tableHead", days);
		} else
		{
			List<Date> dateList = Tool.dateToWeek(Tool.getNextWeekMonday(date));
			List<String> days = Tool.getDateType(dateList);
			Map<String, List<TbSchedule>> map = workPersonAction.getRegisteredTable(days);
			request.setAttribute("tableBody", map);
			request.setAttribute("tableHead", days);
		}
		request.getRequestDispatcher("jsp/Front-registered.jsp").forward(request, response);

	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{

		Date date = new Date(System.currentTimeMillis());
		List<Date> dateList = Tool.dateToWeek(date);
		List<String> days = Tool.getDateType(dateList);
		Map<String, List<TbSchedule>> map = workPersonAction.getRegisteredTable(days);
		request.setAttribute("tableBody", map);
		request.setAttribute("tableHead", days);
		request.getRequestDispatcher("jsp/Front-registered.jsp").forward(request, response);

	}
}
