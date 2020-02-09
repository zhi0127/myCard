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


@WebServlet(name = "BackInquireCardServlet", urlPatterns = "/BackInquireCardServlet")
public class BackInquireCardServlet extends HttpServlet

{

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{


		String loginID = request.getParameter("loginID");
		String loginName = request.getParameter("loginName");
		String card = request.getParameter("card");
		System.out.println(card);
		String applyPersonID = request.getParameter("applyPersonID");
		String cardState = request.getParameter("cardState");
		int startPage = Integer.valueOf(request.getParameter("startPage"));

		CardAction cardAction = new CardAction();
		TbPageSearch psu = cardAction.inquirePageSearch(card, cardState, applyPersonID, startPage);
		TbSelectType selectType = cardAction.getInquireSelect();
		request.setAttribute("loginID", loginID);
		request.setAttribute("loginName", loginName);
		request.setAttribute("card", card);
		request.setAttribute("cardState", cardState);
		request.setAttribute("applyPersonID", applyPersonID);
		request.setAttribute("msg", psu);
		request.setAttribute("selectType", selectType);
		request.getRequestDispatcher("jsp/Back-InquireCard.jsp").forward(request, response);
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{

		CardAction cardAction = new CardAction();
		TbPageSearch psu = cardAction.inquirePageSearch(null, null, null, 1);
		TbSelectType selectType = cardAction.getInquireSelect();
		String loginID = request.getParameter("loginID");
		String loginName = request.getParameter("loginName");
		request.setAttribute("loginID", loginID);
		request.setAttribute("loginName", loginName);
		request.setAttribute("selectType", selectType);
		request.setAttribute("msg", psu);
		request.getRequestDispatcher("jsp/Back-InquireCard.jsp").forward(request, response);

	}
}
