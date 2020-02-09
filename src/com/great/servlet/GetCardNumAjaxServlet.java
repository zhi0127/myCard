package com.great.servlet;

import com.google.gson.Gson;
import com.great.db.CardAction;
import com.great.db.CollaCardAction;
import com.great.javabean.Msg;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "GetCardNumAjaxServlet", urlPatterns = "/GetCardNumAjaxServlet")
public class GetCardNumAjaxServlet extends HttpServlet
{


	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
	{
		super.doGet(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
	{
		System.out.println(" GetCardNumAjaxServlet doPost");
		String s = req.getParameter("msg");
		Gson g = new Gson();
		System.out.println("sss");
		Msg msg = g.fromJson(s, Msg.class);
		System.out.println(msg.getMsg1());
		CollaCardAction collaCardAction = new CollaCardAction();
		int cardCount = collaCardAction.getCardCount(msg.getMsg1());
		int applyNum = Integer.valueOf(msg.getMsg2());
		//构造传输的对象
		Msg m = new Msg();
		if (cardCount >= applyNum)
		{
			String startCardNum = collaCardAction.getStartCardNum(msg.getMsg1());
			m.setMsg1("卡充足");
			m.setMsg2(startCardNum);
		} else
		{
			m.setMsg1("卡不足");
			m.setMsg2(String.valueOf(cardCount));
		}
		System.out.println(m.getMsg1());
		String jsonstr = g.toJson(m);
		PrintWriter printWriter = new PrintWriter(resp.getWriter());
		printWriter.println(jsonstr);
		printWriter.flush();


	}


}
