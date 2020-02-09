package com.great.servlet;

import com.google.gson.Gson;
import com.great.db.WorkPersonAction;
import com.great.javabean.Msg;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "BackLoginAjaxServlet", urlPatterns = "/BackLoginAjaxServlet")
public class BackLoginAjaxServlet extends HttpServlet
{


	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
	{
		super.doGet(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
	{
		System.out.println(" BackLoginAjaxServlet doPost");
		String s = req.getParameter("msg");
		System.out.println(s);

		Gson g = new Gson();
		Msg msg = g.fromJson(s, Msg.class);
		System.out.println(msg.getMsg1());
		WorkPersonAction workPersonAction = new WorkPersonAction();
		boolean flag = workPersonAction.isLoginDisable(msg.getMsg1());
		//构造传输的对象
		Msg m = new Msg();
		if (flag)
		{

			m.setMsg1("被禁用");

		}

		String jsonstr = g.toJson(m);
		PrintWriter printWriter = new PrintWriter(resp.getWriter());
		printWriter.println(jsonstr);
		printWriter.flush();


	}

}
