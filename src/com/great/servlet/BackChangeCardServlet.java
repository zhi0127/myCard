package com.great.servlet;

import com.great.db.CardAction;
import com.great.db.PatientAction;
import com.great.javabean.TbCard;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet(name = "BackChangeCardServlet", urlPatterns = "/BackChangeCardServlet")
public class BackChangeCardServlet extends HttpServlet

{

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{

		String loginID = request.getParameter("loginID");
		String loginName = request.getParameter("loginName");
		String oldCardID = request.getParameter("oldCardID");
		String patientID = request.getParameter("patientID");
		String cardID = request.getParameter("cardID");
		CardAction cardAction = new CardAction();
		TbCard card1 = cardAction.getSaleCard(loginID);
		System.out.println("card1.getCARDNUM()" + card1.getCARDNUM());
		boolean flag = cardAction.changeCard(oldCardID, cardID, patientID);
		if (flag)
		{
			TbCard card = cardAction.getSaleCard(loginID);
			System.out.println("card.getCARDNUM()" + card.getCARDNUM());
			request.setAttribute("card1", card1);
			request.setAttribute("card", card);
			request.setAttribute("msg", "成功");
			request.setAttribute("loginName", loginName);
			request.setAttribute("loginID", loginID);
			request.getRequestDispatcher("jsp/Back-ChangeCard.jsp").forward(request, response);
		}


	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{

		String loginID = request.getParameter("loginID");
		String loginName = request.getParameter("loginName");
		CardAction cardAction = new CardAction();
		TbCard card = cardAction.getSaleCard(loginID);
		request.setAttribute("card", card);
		request.setAttribute("loginID", loginID);
		request.setAttribute("loginName", loginName);
		request.getRequestDispatcher("jsp/Back-ChangeCard.jsp").forward(request, response);

	}
}
