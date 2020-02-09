package com.great.servlet;

import com.great.db.UserAction;
import com.great.db.WorkPersonAction;
import com.great.javabean.TbPageSearch;
import com.great.javabean.TbSelectType;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet(name = "BackUserServlet", urlPatterns = "/BackUserServlet")
public class BackUserServlet extends HttpServlet

{

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{


		String loginID = request.getParameter("loginID");
		String loginName = request.getParameter("loginName");
		String userName = request.getParameter("userName");
		String departType = request.getParameter("departType");
		String roleType = request.getParameter("roleType");
		String stateType = request.getParameter("stateType");
		int startPage = Integer.valueOf(request.getParameter("startPage"));
		WorkPersonAction workPersonAction = new WorkPersonAction();
		TbPageSearch psu = workPersonAction.PageSearch(userName, departType, roleType, stateType, startPage);
		TbSelectType selectType = workPersonAction.getSelectType();
		request.setAttribute("loginID", loginID);
		request.setAttribute("loginName", loginName);
		request.setAttribute("userName", userName);
		request.setAttribute("departType", departType);
		request.setAttribute("roleType", roleType);
		request.setAttribute("stateType", stateType);
		request.setAttribute("msg", psu);
		request.setAttribute("selectType", selectType);
		request.getRequestDispatcher("jsp/Back-User.jsp").forward(request, response);
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{

		String loginID = request.getParameter("loginID");
		String loginName = request.getParameter("loginName");
		WorkPersonAction workPersonAction = new WorkPersonAction();
		TbPageSearch psu = workPersonAction.PageSearch(null, null, null, null, 1);
		TbSelectType selectType = workPersonAction.getSelectType();
		System.out.println(psu);
		request.setAttribute("loginID", loginID);
		request.setAttribute("loginName", loginName);
		request.setAttribute("msg", psu);
		request.setAttribute("selectType", selectType);
		request.getRequestDispatcher("jsp/Back-User.jsp").forward(request, response);

	}
}
