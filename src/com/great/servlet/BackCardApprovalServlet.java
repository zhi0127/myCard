package com.great.servlet;

import com.great.db.CollaCardAction;
import com.great.javabean.TbPageSearch;
import com.great.javabean.TbSelectType;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet(name = "BackCardApprovalServlet", urlPatterns = "/BackCardApprovalServlet")
public class BackCardApprovalServlet extends HttpServlet

{

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{

		String loginID = request.getParameter("loginID");
		String loginName = request.getParameter("loginName");
		String applyPersonID = request.getParameter("applyPersonID");
		String auditState = request.getParameter("auditState");
		String startTime = request.getParameter("startTime");
		String endTime = request.getParameter("endTime");
		int startPage = Integer.valueOf(request.getParameter("startPage"));
		CollaCardAction collaCardAction = new CollaCardAction();
		TbSelectType selectType = collaCardAction.getSelectType();
		TbPageSearch psu = collaCardAction.PageSearch(applyPersonID, auditState, startTime, endTime, startPage);
		request.setAttribute("applyPersonID", applyPersonID);
		request.setAttribute("loginID", loginID);
		request.setAttribute("loginName", loginName);
		request.setAttribute("auditState", auditState);
		request.setAttribute("startTime", startTime);
		request.setAttribute("endTime", endTime);
		request.setAttribute("selectType", selectType);
		request.setAttribute("msg", psu);
		request.getRequestDispatcher("jsp/Back-CardApproval.jsp").forward(request, response);
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		String loginID = request.getParameter("loginID");
		String loginName = request.getParameter("loginName");
		CollaCardAction collaCardAction = new CollaCardAction();
		TbSelectType selectType = collaCardAction.getSelectType();
		TbPageSearch psu = collaCardAction.PageSearch(null, null, null, null, 1);
		request.setAttribute("loginID", loginID);
		request.setAttribute("loginName", loginName);
		request.setAttribute("selectType", selectType);
		request.setAttribute("msg", psu);
		request.getRequestDispatcher("jsp/Back-CardApproval.jsp").forward(request, response);

	}
}
