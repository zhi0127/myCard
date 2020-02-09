package com.great.servlet;

import com.google.gson.Gson;
import com.great.db.WorkPersonAction;
import com.great.javabean.Msg;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

@WebServlet(name = "AjaxServlet", urlPatterns = "/AjaxServlet")
public class AjaxServlet extends HttpServlet
{


	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
	{
		super.doGet(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
	{
		System.out.println(" AjaxServlet doPost");
		String s = req.getParameter("msg");
		System.out.println(s);

		Gson g = new Gson();
		Msg msg = g.fromJson(s, Msg.class);
		System.out.println(msg.getMsg1());
		System.out.println(msg.getMsg2());

		WorkPersonAction workPersonAction = new WorkPersonAction();
		boolean flag = workPersonAction.changeType(msg.getMsg1(), msg.getMsg2());

		if (flag)
		{

			//构造传输的对象
			Msg m = new Msg();
			if ("1".equals(msg.getMsg2()))
			{
				m.setMsg1("1");
			} else if ("2".equals(msg.getMsg2()))
			{
				m.setMsg1("2");
			} else if ("3".equals(msg.getMsg2()))
			{
				m.setMsg1("3");
			} else
			{
				m.setMsg1("4");
			}

			String jsonstr = g.toJson(m);
			PrintWriter printWriter = new PrintWriter(resp.getWriter());
			printWriter.println(jsonstr);
			printWriter.flush();
		}

	}

}
