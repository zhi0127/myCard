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
import java.math.BigDecimal;

@WebServlet(name = "CardHeadAjaxServlet2", urlPatterns = "/CardHeadAjaxServlet2")
public class CardHeadAjaxServlet2 extends HttpServlet
{


	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
	{
		super.doGet(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
	{
		System.out.println(" CardHeadAjaxServlet2 doPost");
		String s = req.getParameter("msg");
		Gson g = new Gson();
		System.out.println("sss");
		Msg msg = g.fromJson(s, Msg.class);
		System.out.println(msg.getMsg1());
		CardAction cardAction = new CardAction();

		String startCardNum = cardAction.getStartCardNum(msg.getMsg1());
		//构造传输的对象
		Msg m = new Msg();
		m.setMsg1(startCardNum);
		System.out.println(m.getMsg1());
		String jsonstr = g.toJson(m);
		PrintWriter printWriter = new PrintWriter(resp.getWriter());
		printWriter.println(jsonstr);
		printWriter.flush();


	}


}
