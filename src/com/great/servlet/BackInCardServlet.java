package com.great.servlet;

import com.great.db.CardAction;

import com.great.javabean.TbPageSearch;
import com.great.javabean.TbSelectType;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet(name = "BackInCardServlet", urlPatterns = "/BackInCardServlet")
public class BackInCardServlet extends HttpServlet

{

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{


		String loginID = request.getParameter("loginID");
		String loginName = request.getParameter("loginName");
		String cardHead = request.getParameter("cardHead");
		String startNum = request.getParameter("startNum");
		String endNum = request.getParameter("endNum");
		String cardState = request.getParameter("cardState");
		String startTime = request.getParameter("startTime");
		System.out.println(startTime);
		String endTime = request.getParameter("endTime");
		int startPage = Integer.valueOf(request.getParameter("startPage"));
		CardAction cardAction = new CardAction();
		TbPageSearch psu = cardAction.PageSearch(cardHead, startNum, endNum, cardState, startTime, endTime, startPage);
		TbSelectType selectType = cardAction.getSelectType();
		request.setAttribute("loginID", loginID);
		request.setAttribute("loginName", loginName);
		request.setAttribute("cardHead", cardHead);
		request.setAttribute("startNum", startNum);
		request.setAttribute("endNum", endNum);
		request.setAttribute("cardState", cardState);
		request.setAttribute("startTime", startTime);
		request.setAttribute("endTime", endTime);
		request.setAttribute("msg", psu);
		request.setAttribute("selectType", selectType);
		request.getRequestDispatcher("jsp/Back-inCard.jsp").forward(request, response);
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{

		CardAction cardAction = new CardAction();
		TbPageSearch psu = cardAction.PageSearch(null, null, null, null, null, null, 1);
		TbSelectType selectType = cardAction.getSelectType();
		String loginID = request.getParameter("loginID");
		String loginName = request.getParameter("loginName");
		request.setAttribute("loginID", loginID);
		request.setAttribute("loginName", loginName);
		request.setAttribute("selectType", selectType);
		request.setAttribute("msg", psu);
		request.getRequestDispatcher("jsp/Back-inCard.jsp").forward(request, response);

	}
}
