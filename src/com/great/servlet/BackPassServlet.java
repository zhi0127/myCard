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


@WebServlet(name = "BackPassServlet", urlPatterns = "/BackPassServlet")
public class BackPassServlet extends HttpServlet

{

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		String loginID = request.getParameter("loginID");
		String loginName = request.getParameter("loginName");
		String CardHead = request.getParameter("CardHead");
		String newCardStart = request.getParameter("newCardStart");
		String applyNum = request.getParameter("applyNum");
		String auditDate = request.getParameter("auditDate");
		String collacardid = request.getParameter("collacardid");
		CollaCardAction collaCardAction = new CollaCardAction();
		boolean flag = collaCardAction.cardApproved(collacardid, CardHead, Integer.valueOf(newCardStart), Integer.valueOf(applyNum));
		boolean flag2 = collaCardAction.approvalPass(loginID, loginName, auditDate, collacardid);
		TbSelectType selectType = collaCardAction.getSelectType();
		TbPageSearch psu = collaCardAction.PageSearch(null, null, null, null, 1);
		request.setAttribute("loginID", loginID);
		request.setAttribute("loginName", loginName);
		request.setAttribute("selectType", selectType);
		request.setAttribute("msg", psu);
		request.getRequestDispatcher("jsp/Back-CardApproval.jsp").forward(request, response);

	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{


	}
}
