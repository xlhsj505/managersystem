package com.picaas.comm;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.picaas.manager.entity.Msg;
import com.picaas.manager.entity.UserInfo;



/**
 * Servlet Filter implementation class PermissionFilter
 */
@WebFilter("/manager")
public class PermissionFilter implements Filter {

    /**
     * Default constructor. 
     */
    public PermissionFilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest)request;
		HttpServletResponse resp = (HttpServletResponse)response;
		HttpSession session = req.getSession();
		UserInfo u = (UserInfo)req.getSession().getAttribute("UserInfo");
//		System.out.println(u.getUserName());
		String url = req.getRequestURI();
		if (u==null){
			//���ص�¼
			req.getSession().removeAttribute("UserInfo");
			req.getSession().invalidate();//����session����
			//������Ϣ����û��Ȩ�ޣ����ȵ�¼����
			Msg msg = new Msg();
			msg.setFlag(0);
			msg.setContents("û��Ȩ�ޣ����ȵ�¼��");
			req.getSession().setAttribute("errorMsg", msg);
			//��ת��error.jspҳ��
			resp.sendRedirect(req.getContextPath() + "/login.jsp");
		}else {
			chain.doFilter(request, response);
		}
		
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
