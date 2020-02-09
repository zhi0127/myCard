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

@WebServlet(name = "BackUpdatePersonServlet", urlPatterns = "/BackUpdatePersonServlet")
public class BackUpdatePersonServlet extends HttpServlet
{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
	{

		System.out.println("doPost");
		String personID = req.getParameter("hiddenUserID");
		String updateDepartType = req.getParameter("updateDepartType");
		String updateRoleType = req.getParameter("updateRoleType");
		String loginID = req.getParameter("loginID");
		String loginName = req.getParameter("loginName");
		WorkPersonAction workPersonAction = new WorkPersonAction();
		boolean flag = workPersonAction.updatePersosn(personID, updateDepartType, updateRoleType);
		System.out.println("flag=" + flag);

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
