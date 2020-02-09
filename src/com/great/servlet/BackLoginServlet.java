package com.great.servlet;

import com.great.db.MenuAction;
import com.great.db.WorkPersonAction;
import com.great.javabean.TbMenu;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

@WebServlet(name = "BackLoginServlet", urlPatterns = "/BackLoginServlet")
public class BackLoginServlet extends HttpServlet
{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
	{
		System.out.println("doPost");
		String loginID = req.getParameter("loginID");
		String loginPass = req.getParameter("loginPass");
		WorkPersonAction workPersonAction = new WorkPersonAction();
		boolean flag = workPersonAction.login(loginID, loginPass);
		MenuAction ma = new MenuAction();
		HashMap<String, ArrayList<TbMenu>> map = ma.queryMenu(loginID);
		if (flag)
		{
			System.out.println("success");
			req.setAttribute("loginName", workPersonAction.getWorkPerson().getPERSONNAME());
			req.setAttribute("loginID", loginID);
			req.setAttribute("menu", map);
			req.setAttribute("username", workPersonAction.getWorkPerson().getPERSONNAME());
			req.getRequestDispatcher("jsp/Back-index.jsp").forward(req, resp);
		} else
		{
			System.out.println("fail");
			req.setAttribute("isFail", "fail");
			req.getRequestDispatcher("jsp/Back-login.jsp").forward(req, resp);
		}

	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
	{
		doPost(req, resp);
	}
}
