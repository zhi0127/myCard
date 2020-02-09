package com.great.servlet;

import com.great.db.CardAction;
import com.great.db.CollaCardAction;
import com.great.javabean.TbPageSearch;
import com.great.javabean.TbSelectType;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "BackNewApplyServlet", urlPatterns = "/BackNewApplyServlet")
public class BackNewApplyServlet extends HttpServlet
{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
	{


		System.out.println("doPost");
		String loginID = req.getParameter("loginID");
		String loginName = req.getParameter("loginName");
		String applyDate = req.getParameter("applyDate");
		String cardHead = req.getParameter("cardHead");
		String applyNum = req.getParameter("applyNum");
		String newCardTime = req.getParameter("newCardTime");
		System.out.println("newCardTime" + newCardTime);
		CollaCardAction collaCardAction = new CollaCardAction();
		if (cardHead != null)
		{
			boolean flag = collaCardAction.addApplyCard(cardHead, "6", applyDate, applyNum, loginID, loginName);
			if (flag)
			{
				CardAction cardAction = new CardAction();
				TbSelectType selectType = cardAction.getSelectType();
				req.setAttribute("selectType", selectType);
				TbPageSearch psu = collaCardAction.PageSearch(loginID, null, null, null, 1);
				req.setAttribute("loginID", loginID);
				req.setAttribute("loginName", loginName);
				req.setAttribute("msg", psu);
				req.setAttribute("isSuccess", "提交申请成功");
				req.getRequestDispatcher("jsp/Back-CollarCard.jsp").forward(req, resp);
			} else
			{
				System.out.println("fail");
			}
		}


	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
	{
		doPost(req, resp);
	}
}
