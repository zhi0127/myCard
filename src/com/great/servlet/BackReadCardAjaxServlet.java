package com.great.servlet;

import com.google.gson.Gson;
import com.great.db.CardAction;
import com.great.javabean.Msg;
import com.great.javabean.TbCard;
import com.great.javabean.TbPatient;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "BackReadCardAjaxServlet", urlPatterns = "/BackReadCardAjaxServlet")
public class BackReadCardAjaxServlet extends HttpServlet
{


	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
	{
		super.doGet(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
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

}
