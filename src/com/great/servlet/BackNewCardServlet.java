package com.great.servlet;

import com.great.db.CardAction;
import com.great.db.WorkPersonAction;
import com.great.javabean.TbPageSearch;
import com.great.javabean.TbSelectType;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "BackNewCardServlet", urlPatterns = "/BackNewCardServlet")
public class BackNewCardServlet extends HttpServlet
{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
	{


		System.out.println("doPost");
		String loginID = req.getParameter("loginID");
		String loginName = req.getParameter("loginName");
		String newCardHead = req.getParameter("newCardHead");
		String newCardStart = req.getParameter("newCardStart");
		String newCardEnd = req.getParameter("newCardEnd");
		String newCardTime = req.getParameter("newCardTime");
		System.out.println("newCardTime" + newCardTime);
		CardAction cardAction = new CardAction();
		boolean flag = cardAction.addCard(newCardHead, newCardStart, newCardEnd, "4", newCardTime);
		if (flag)
		{
			System.out.println("success");
			TbPageSearch psu = cardAction.PageSearch(null, null, null, null, null, null, 1);
			TbSelectType selectType = cardAction.getSelectType();
			req.setAttribute("loginID", loginID);
			req.setAttribute("loginName", loginName);
			req.setAttribute("selectType", selectType);
			req.setAttribute("msg", psu);
			req.getRequestDispatcher("jsp/Back-inCard.jsp").forward(req, resp);
		} else
		{
			System.out.println("fail");
		}
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
	{
		doPost(req, resp);
	}
}
