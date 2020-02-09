package com.great.servlet;

import com.great.db.CardAction;
import com.great.javabean.TbCard;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet(name = "BackReturnCardServlet", urlPatterns = "/BackReturnCardServlet")
public class BackReturnCardServlet extends HttpServlet

{

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{

		String loginID = request.getParameter("loginID");
		String loginName = request.getParameter("loginName");
		String oldCardID = request.getParameter("oldCardID");
		String patientID = request.getParameter("patientID");
		String returnMoney = request.getParameter("returnMoney");
		CardAction cardAction = new CardAction();
		boolean flag = cardAction.returnCard(oldCardID, patientID);
		if (flag)
		{
			request.setAttribute("msg", "成功");
			request.setAttribute("loginName", loginName);
			request.setAttribute("loginID", loginID);
			request.setAttribute("returnMoney", returnMoney);
			request.getRequestDispatcher("jsp/Back-ReturnCard.jsp").forward(request, response);
		}


	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{

		String loginID = request.getParameter("loginID");
		String loginName = request.getParameter("loginName");
		request.setAttribute("loginID", loginID);
		request.setAttribute("loginName", loginName);
		request.getRequestDispatcher("jsp/Back-ReturnCard.jsp").forward(request, response);

	}
}
