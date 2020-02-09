package com.great.servlet;

import com.google.gson.Gson;
import com.great.db.CollaCardAction;
import com.great.javabean.Msg;
import com.great.javabean.TbCard;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "CreditCardAjaxServlet", urlPatterns = "/CreditCardAjaxServlet")
public class CreditCardAjaxServlet extends HttpServlet
{


	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
	{
		super.doGet(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
	{
		System.out.println(" CreditCardAjaxServlet doPost");
		String s = req.getParameter("msg");
		Gson g = new Gson();
		Msg msg = g.fromJson(s, Msg.class);
		System.out.println(msg.getMsg1());

		CollaCardAction collaCardAction = new CollaCardAction();
		List<TbCard> list = collaCardAction.getCreditCard(msg.getMsg1());

		String jsonstr = g.toJson(list);

		PrintWriter printWriter = new PrintWriter(resp.getWriter());
		printWriter.println(jsonstr);
		printWriter.flush();


	}


}
