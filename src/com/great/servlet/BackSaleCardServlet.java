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
import java.text.SimpleDateFormat;
import java.util.Date;


@WebServlet(name = "BackSaleCardServlet", urlPatterns = "/BackSaleCardServlet")
public class BackSaleCardServlet extends HttpServlet

{

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{

		String loginID = request.getParameter("loginID");
		String loginName = request.getParameter("loginName");

		String cardID = request.getParameter("cardID");
		String patientName = request.getParameter("patientName");
		String patientAge = request.getParameter("patientAge");
		String patientWeek = request.getParameter("patientWeek");
		String patientSexy = request.getParameter("patientSexy");
		String patientBirthPlace = request.getParameter("s_province") + request.getParameter("s_city");
		String patientIdentity = request.getParameter("patientIdentity");
		String patientPhone = request.getParameter("patientPhone");
		String patientAdress = request.getParameter("patientAdress");
		String patientMoney = request.getParameter("patientMoney");
		PatientAction patientAction = new PatientAction();
		Date nowTime = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String cardSalesTime = dateFormat.format(nowTime);

		boolean flag = patientAction.addUser(cardID, patientName, patientAge, patientWeek, patientSexy, patientBirthPlace, patientIdentity, patientPhone, patientAdress, patientMoney, loginName, cardSalesTime);

		if (flag)
		{
			CardAction cardAction = new CardAction();
			TbCard card = cardAction.getSaleCard(loginID);
			request.setAttribute("card", card);
			request.setAttribute("msg", "成功");
			request.setAttribute("loginName", loginName);
			request.setAttribute("loginID", loginID);
			request.setAttribute("patientMoney", patientMoney);
			request.getRequestDispatcher("jsp/Back-SaleCard.jsp").forward(request, response);
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
		request.getRequestDispatcher("jsp/Back-SaleCard.jsp").forward(request, response);

	}
}
