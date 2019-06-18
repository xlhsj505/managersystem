package com.picaas.manager.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.picaas.manager.entity.UserInfo;
import com.picaas.manager.service.UserService;


/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/manager/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	     
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		response.getWriter().append("Served at: ").append(request.getContextPath());
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
			HttpSession session = request.getSession();
		// 接收客户端的请求数据
				String userName = request.getParameter("username") == null ? "" : request.getParameter("username");
				userName = new String(userName.getBytes("iso-8859-1"), "UTF-8");
				String password = request.getParameter("pwd");
//				System.out.println(userName+"           "+password);
				UserService userService = new UserService();
				List<UserInfo> list = userService.login(userName, password);
				
				if (list != null && list.size() != 0) {
					UserInfo u = list.get(0);
					session.setAttribute("userName", u.getUserName());
					session.setAttribute("userIUID", u.getIsEnable());
					session.setAttribute("UserInfo", u);
					if (u.getIUID().equals("1")) {
						System.out.println("系统管理员");
						request.getRequestDispatcher("index.jsp").forward(request, response);
					} else if (u.getIUID().equals("2")) {
						System.out.println("患者");
						request.getRequestDispatcher("take.jsp").forward(request, response);
					} else if (u.getIUID().equals("3")) {
						System.out.println("其他");
						request.getRequestDispatcher("specialist.jsp").forward(request, response);
					}
					
				} else {
					response.sendRedirect(request.getContextPath()+"/login.jsp");
				}
	}

}
