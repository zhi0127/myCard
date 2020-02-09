package com.great.servlet;

import com.great.db.CollaCardAction;
import com.great.db.WorkPersonAction;
import com.great.javabean.TbPageSearch;
import com.great.javabean.TbSelectType;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet(name = "BackFullcalendarServlet", urlPatterns = "/BackFullcalendarServlet")
public class BackFullcalendarServlet extends HttpServlet

{

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{

		String loginID = request.getParameter("loginID");
		String loginName = request.getParameter("loginName");
		WorkPersonAction workPersonAction = new WorkPersonAction();
		TbSelectType selectType = workPersonAction.getDoctorSelect();
		request.setAttribute("loginID", loginID);
		request.setAttribute("loginName", loginName);
		request.setAttribute("selectType", selectType);
		request.getRequestDispatcher("jsp/Back-Fullcalendar.jsp").forward(request, response);
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		String loginID = request.getParameter("loginID");
		String loginName = request.getParameter("loginName");
		WorkPersonAction workPersonAction = new WorkPersonAction();
		TbSelectType selectType = workPersonAction.getDoctorSelect();
		request.setAttribute("loginID", loginID);
		request.setAttribute("loginName", loginName);
		request.setAttribute("selectType", selectType);
		request.getRequestDispatcher("jsp/Back-Fullcalendar.jsp").forward(request, response);

	}
}
