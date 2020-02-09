package com.great.servlet;

import com.great.db.CardAction;
import com.great.db.WorkPersonAction;
import com.great.javabean.TbPatient;
import com.great.javabean.TbSchedule;
import com.great.javabean.Tool;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;


@WebServlet(name = "FrontTakeServlet", urlPatterns = "/FrontTakeServlet")
public class FrontTakeServlet extends HttpServlet

{
	private WorkPersonAction workPersonAction;

	public FrontTakeServlet()
	{
		workPersonAction = new WorkPersonAction();
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		String xz = request.getParameter("xz");
		System.out.println(xz);
		String card = request.getParameter("card");
		String cardHead = card.replaceAll("\\d", "");
		String cardNum = card.replaceAll("[a-zA-Z]", "");
		if (null != cardHead && cardHead.length() > 0 && null != cardNum && cardNum.length() > 0)


		{
			CardAction cardAction = new CardAction();
			TbPatient patient = cardAction.readCard(cardHead, cardNum, null, null);
			WorkPersonAction workPersonAction = new WorkPersonAction();
			List<TbSchedule> list = workPersonAction.getTake(cardHead, cardNum);

			request.setAttribute("list", list);
			request.setAttribute("patient", patient);
			request.getRequestDispatcher("jsp/Front-take.jsp").forward(request, response);
		}


	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{


		request.getRequestDispatcher("jsp/Front-take.jsp").forward(request, response);

	}
}
