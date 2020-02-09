package com.great.servlet;

import com.google.gson.Gson;
import com.great.db.CardAction;
import com.great.db.WorkPersonAction;
import com.great.javabean.Msg;
import com.great.javabean.TbSchedule;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "ScheduleAjaxServlet", urlPatterns = "/ScheduleAjaxServlet")
public class ScheduleAjaxServlet extends HttpServlet
{


	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
	{
		super.doGet(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
	{
		System.out.println(" addSchedule doPost");
		String s = req.getParameter("msg");
		System.out.println(s);
		Gson g = new Gson();
		Msg msg = g.fromJson(s, Msg.class);
		System.out.println(msg.getMsg1());
		String jsonstr = null;
		WorkPersonAction workPersonAction = new WorkPersonAction();
		if ("get".equals(msg.getMsg1()))
		{
			System.out.println();
			List<TbSchedule> list = workPersonAction.getSchedule();
			jsonstr = g.toJson(list);
		} else if ("add".equals(msg.getMsg1()))
		{
			boolean flag = workPersonAction.existSchedule(msg.getMsg2(), msg.getMsg3());
			if (flag)
			{
				jsonstr = g.toJson("该排班已经存在");
			} else
			{
				boolean flag2 = workPersonAction.addSchedule(msg.getMsg2(), msg.getMsg3());
				if (flag2)
				{
					jsonstr = g.toJson("添加成功");
				} else
				{
					jsonstr = g.toJson("添加失败");
				}
			}
		} else if ("delete".equals(msg.getMsg1()))
		{
			boolean flag3 = workPersonAction.deleteSchedule(msg.getMsg2(), msg.getMsg3());
			if (flag3)
			{
				jsonstr = g.toJson("删除成功");
			} else
			{
				jsonstr = g.toJson("删除失败");
			}
		}
		PrintWriter printWriter = new PrintWriter(resp.getWriter());
		printWriter.println(jsonstr);
		printWriter.flush();


	}


}
