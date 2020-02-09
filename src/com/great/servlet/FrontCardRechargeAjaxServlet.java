package com.great.servlet;

import com.google.gson.Gson;
import com.great.db.CardAction;
import com.great.javabean.Msg;
import com.great.javabean.TbPatient;
import com.sun.org.apache.bcel.internal.generic.IF_ACMPEQ;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "FrontCardRechargeAjaxServlet", urlPatterns = "/FrontCardRechargeAjaxServlet")
public class FrontCardRechargeAjaxServlet extends HttpServlet
{


	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
	{
		super.doGet(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
	{
		System.out.println("FrontCardRechargeAjaxServlet doPost");
		String s = req.getParameter("msg");
		System.out.println("s=" + s);

		Gson g = new Gson();
		Msg msg = g.fromJson(s, Msg.class);

		CardAction cardAction = new CardAction();
		boolean flag = cardAction.CardRecharge(msg.getMsg1(), msg.getMsg2());


		String jsonstr = g.toJson(flag);

		PrintWriter printWriter = new PrintWriter(resp.getWriter());
		printWriter.println(jsonstr);
		printWriter.flush();

	}

}
