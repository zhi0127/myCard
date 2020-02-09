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

@WebServlet(name = "BackNewPersonServlet", urlPatterns = "/BackNewPersonServlet")
public class BackNewPersonServlet extends HttpServlet
{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
	{


		System.out.println("doPost");
		String loginID = req.getParameter("loginID");
		String loginName = req.getParameter("loginName");
		String newUserName = req.getParameter("newUserName");
		String newUserPass = req.getParameter("newUserPass");
		String newDepartType = req.getParameter("newDepartType");
		String newRoleType = req.getParameter("newRoleType");
		WorkPersonAction workPersonAction = new WorkPersonAction();
		boolean flag = workPersonAction.addUser(newUserName, newUserPass, newDepartType, newRoleType);
		if (flag)
		{
			System.out.println("success");
			TbPageSearch psu = workPersonAction.PageSearch(null, null, null, null, 1);
			TbSelectType selectType = workPersonAction.getSelectType();
			System.out.println(psu);
			req.setAttribute("loginID", loginID);
			req.setAttribute("loginName", loginName);
			req.setAttribute("msg", psu);
			req.setAttribute("selectType", selectType);
			req.getRequestDispatcher("jsp/Back-User.jsp").forward(req, resp);
		} else
		{
			System.out.println("fail");
		}
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
	{
		doPost(req, resp);
	}
}
