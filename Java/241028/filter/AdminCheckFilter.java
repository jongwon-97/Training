package com.kosmo.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.kosmo.model.MemberDTO;

/**
 * Servlet Filter implementation class AdminCheckFilter
 */
@WebFilter(urlPatterns = {"/memberList.do"})
public class AdminCheckFilter extends HttpFilter implements Filter {
       
    /**
     * @see HttpFilter#HttpFilter()
     */
    public AdminCheckFilter() {
        super();
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
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) 
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		HttpServletRequest req=(HttpServletRequest)request; //강제 형변환 부모타입에 없기때문에 자식타입으로 형변환 해서 사용
		HttpSession session = req.getSession();
		
		MemberDTO loginUser = (MemberDTO)session.getAttribute("loginUser");
		if(loginUser == null) {
			return;
		}
	
		if(loginUser.getMstate() != 9) {
			req.setAttribute("msg", "관리자만 사용 가능합니다.");
			req.setAttribute("loc", "login.do");
			RequestDispatcher disp = req.getRequestDispatcher("message.jsp");
			disp.forward(req, response);
			
			return;	
		}
		// pass the request along the filter chain
		chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
