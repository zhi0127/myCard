package com.great.servlet;

import java.io.IOException;
import java.lang.reflect.Method;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/BasicServlet")
public class BasicServlet extends HttpServlet
{
	private static final long serialVersionUID = 1L;

	public BasicServlet()
	{
		super();
	}

	//重写并覆盖父类的service  把service执行dogetdopost修改为执行某一个的某个方法
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		// 接收数据，封装实体 方法名称  业务名称
		String methodName = request.getParameter("methodName");
		// 1.获取当前累的全类名 -- 获取这个类
		Class clazz = this.getClass();
		try
		{
			// 2.反射机制获取这个类的方法
			Method method = clazz.getMethod(methodName, HttpServletRequest.class, HttpServletResponse.class);
			// 3.通过反射机制 使用这个方法invoke通过获取的到方法 执行方法逻辑
			String path = (String) method.invoke(this, request, response);
			// 信息转发
			if (path != null && !"".equals(path))
			{
				// 请求转发
				request.getRequestDispatcher(path).forward(request, response);
				return;
			}
		} catch (Exception e)
		{
			e.printStackTrace();
		}

	}

}
