package com.great.servlet;

import com.google.gson.Gson;
import com.great.db.CardAction;
import com.great.javabean.Msg;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "CancelCardAjaxServlet", urlPatterns = "/CancelCardAjaxServlet")
public class CancelCardAjaxServlet extends HttpServlet
{


	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
	{
		super.doGet(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
	{
		System.out.println(" CancelCardAjaxServlet doPost");
		String s = req.getParameter("msg");
		System.out.println(s);
		Gson g = new Gson();
		Msg msg = g.fromJson(s, Msg.class);
		System.out.println(msg.getMsg1());
		CardAction cardAction = new CardAction();
		boolean flag = cardAction.cancelCard(msg.getMsg1());

		if (flag)
		{

			//构造传输的对象
			Msg m = new Msg();
			m.setMsg1("1");
			String jsonstr = g.toJson(m);
			PrintWriter printWriter = new PrintWriter(resp.getWriter());
			printWriter.println(jsonstr);
			printWriter.flush();
		}


	}


}
