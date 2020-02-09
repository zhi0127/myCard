package com.great.servlet;

import com.google.gson.Gson;
import com.great.db.CardAction;
import com.great.db.WorkPersonAction;
import com.great.javabean.Msg;
import com.great.javabean.TbPatient;
import com.great.javabean.TbSchedule;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "FrontRegisteredAjaxServlet", urlPatterns = "/FrontRegisteredAjaxServlet")
public class FrontRegisteredAjaxServlet extends BasicServlet
{


	public void readCard(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
	{

		System.out.println(" BackReadCardAjaxServlet doPost");
		String s = req.getParameter("msg");
		System.out.println("s=" + s);

		Gson g = new Gson();
		Msg msg = g.fromJson(s, Msg.class);
		System.out.println(msg.getMsg1());
		System.out.println(msg.getMsg2());
		System.out.println(msg.getMsg3());
		CardAction cardAction = new CardAction();
		TbPatient patient = null;
		if (msg.getMsg1().equals("1"))
		{
			patient = cardAction.readCard(msg.getMsg2(), msg.getMsg3(), null, null);
		} else if (msg.getMsg1().equals("2"))
		{
			patient = cardAction.readCard(null, null, msg.getMsg2(), null);
		} else if (msg.getMsg1().equals("3"))
		{
			patient = cardAction.readCard(null, null, null, msg.getMsg2());
		}

		String jsonstr = g.toJson(patient);
		PrintWriter printWriter = new PrintWriter(resp.getWriter());
		printWriter.println(jsonstr);
		printWriter.flush();
	}

	public void showRegistered(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
	{
		System.out.println(" showRegistered doPost");
		String s = req.getParameter("msg");
		System.out.println(s);
		Gson g = new Gson();
		Msg msg = g.fromJson(s, Msg.class);
		System.out.println(msg.getMsg1());
		System.out.println(msg.getMsg2());
		System.out.println(msg.getMsg3());
		WorkPersonAction workPersonAction = new WorkPersonAction();
		List<TbSchedule> list = workPersonAction.showRegistered(msg.getMsg1(), msg.getMsg2());
		String jsonstr = g.toJson(list);
		PrintWriter printWriter = new PrintWriter(resp.getWriter());
		printWriter.println(jsonstr);
		printWriter.flush();

	}

	public void registered(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
	{
		System.out.println(" registered doPost");
		String s = req.getParameter("msg");
		System.out.println(s);
		Gson g = new Gson();
		Msg msg = g.fromJson(s, Msg.class);
		System.out.println(msg.getMsg1());
		System.out.println(msg.getMsg2());
		System.out.println(msg.getMsg3());
		System.out.println(msg.getMsg4());
		boolean flag1 = false;
		boolean flag2 = false;
		int patientMoney = Integer.valueOf(msg.getMsg6());
		CardAction cardAction = new CardAction();
		WorkPersonAction workPersonAction = new WorkPersonAction();
		Msg m = new Msg();
		if (msg.getMsg5().equals("取消"))
		{
			flag1 = workPersonAction.registered(msg.getMsg1(), "", msg.getMsg3(), msg.getMsg4());
			if (flag1)
			{
				flag2 = cardAction.CardRecharge(msg.getMsg2(), String.valueOf(patientMoney + 30));
				if (flag2)
				{
					m.setMsg1("取消成功");
				}

			}


		} else if (msg.getMsg5().equals("预约"))
		{
			flag1 = workPersonAction.registered(msg.getMsg1(), msg.getMsg2(), msg.getMsg3(), msg.getMsg4());

			if (flag1)
			{
				flag2 = cardAction.CardRecharge(msg.getMsg2(), String.valueOf(patientMoney - 30));

				if (flag2)
				{
					m.setMsg1("预约成功");
				}
			}

		}
		String jsonstr = g.toJson(m);
		PrintWriter printWriter = new PrintWriter(resp.getWriter());
		printWriter.println(jsonstr);
		printWriter.flush();

	}

}
