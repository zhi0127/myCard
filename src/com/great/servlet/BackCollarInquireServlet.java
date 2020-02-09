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


@WebServlet(name = "BackCollarInquireServlet", urlPatterns = "/BackCollarInquireServlet")
public class BackCollarInquireServlet extends HttpServlet

{

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{


		String loginID = request.getParameter("loginID");
		String loginName = request.getParameter("loginName");
		String cardHead = request.getParameter("cardHead");
		String startNum = request.getParameter("startNum");
		String endNum = request.getParameter("endNum");
		String startTime = request.getParameter("startTime");
		String endTime = request.getParameter("endTime");
		int startPage = Integer.valueOf(request.getParameter("startPage"));
		CardAction cardAction = new CardAction();
		TbPageSearch psu = cardAction.collarInquirePageSearch(loginID, cardHead, startNum, endNum, startTime, endTime, startPage);
		TbSelectType selectType = cardAction.getSelectType();
		request.setAttribute("loginID", loginID);
		request.setAttribute("loginName", loginName);
		request.setAttribute("cardHead", cardHead);
		request.setAttribute("startNum", startNum);
		request.setAttribute("endNum", endNum);
		request.setAttribute("startTime", startTime);
		request.setAttribute("endTime", endTime);
		request.setAttribute("msg", psu);
		request.setAttribute("selectType", selectType);
		request.getRequestDispatcher("jsp/Back-CollarInquire.jsp").forward(request, response);
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{

		String loginID = request.getParameter("loginID");
		String loginName = request.getParameter("loginName");
		CardAction cardAction = new CardAction();
		TbPageSearch psu = cardAction.collarInquirePageSearch(loginID, null, null, null, null, null, 1);
		TbSelectType selectType = cardAction.getSelectType();
		request.setAttribute("loginID", loginID);
		request.setAttribute("loginName", loginName);
		request.setAttribute("selectType", selectType);
		request.setAttribute("msg", psu);
		request.getRequestDispatcher("jsp/Back-CollarInquire.jsp").forward(request, response);

	}
}
