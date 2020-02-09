package com.great.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(filterName = "BackLoginFilter", urlPatterns = "/BackLoginServlet")
public class BackLoginFilter implements Filter
{
	public BackLoginFilter()
	{
		super();
	}

	/**
	 * doFilter  放行
	 */
	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException
	{
		HttpServletRequest req = (HttpServletRequest) servletRequest;
		HttpServletResponse resp = (HttpServletResponse) servletResponse;
		String loginID = req.getParameter("loginID");
		String loginPass = req.getParameter("loginPass");
		if (loginID != null && loginPass != null)
		{
			System.out.println("允许通行");
			filterChain.doFilter(servletRequest, servletResponse);
		} else
		{
			System.out.println("不允许通行");
			req.setAttribute("msg", "请先登录");
			req.getRequestDispatcher("jsp/Back-login.jsp").forward(req, resp);
		}
	}

	@Override
	public void destroy()
	{
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException
	{
	}
}
