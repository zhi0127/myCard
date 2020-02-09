package com.great.servlet;

import com.great.db.CardAction;
import com.great.db.CollaCardAction;
import com.great.javabean.TbPageSearch;
import com.great.javabean.TbSelectType;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet(name = "BackCollarCardServlet", urlPatterns = "/BackCollarCardServlet")
public class BackCollarCardServlet extends HttpServlet

{

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{

		String loginID = request.getParameter("loginID");
		String loginName = request.getParameter("loginName");
		String auditState = request.getParameter("auditState");
		String startTime = request.getParameter("startTime");
		String endTime = request.getParameter("endTime");
		int startPage = Integer.valueOf(request.getParameter("startPage"));
		CollaCardAction collaCardAction = new CollaCardAction();
		TbPageSearch psu = collaCardAction.PageSearch(loginID, auditState, startTime, endTime, startPage);
		CardAction cardAction = new CardAction();
		TbSelectType selectType = cardAction.getSelectType();
		request.setAttribute("selectType", selectType);
		request.setAttribute("loginID", loginID);
		request.setAttribute("loginName", loginName);
		request.setAttribute("auditState", auditState);
		request.setAttribute("startTime", startTime);
		request.setAttribute("endTime", endTime);
		request.setAttribute("msg", psu);
		request.getRequestDispatcher("jsp/Back-CollarCard.jsp").forward(request, response);
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{

		String loginID = request.getParameter("loginID");
		String loginName = request.getParameter("loginName");
		CollaCardAction collaCardAction = new CollaCardAction();
		TbPageSearch psu = collaCardAction.PageSearch(loginID, null, null, null, 1);
		CardAction cardAction = new CardAction();
		TbSelectType selectType = cardAction.getSelectType();
		request.setAttribute("selectType", selectType);
		request.setAttribute("loginID", loginID);
		request.setAttribute("loginName", loginName);
		request.setAttribute("msg", psu);
		request.getRequestDispatcher("jsp/Back-CollarCard.jsp").forward(request, response);

	}
}
