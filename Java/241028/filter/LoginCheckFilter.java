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
 * 로그인 여부를 확인 하는 필터
 * 
 */
@WebFilter(urlPatterns = {"/mypage.do","/memberList.do"})
public class LoginCheckFilter extends HttpFilter implements Filter {
       
    /**
     * @see HttpFilter#HttpFilter()
     */
    public LoginCheckFilter() {
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
		// place your code here
		HttpServletRequest req=(HttpServletRequest)request; //강제 형변환 부모타입에 없기때문에 자식타입으로 형변환 해서 사용
		HttpSession session = req.getSession();
		//loginUser가 있는지 확인
		MemberDTO loginUser = (MemberDTO)session.getAttribute("loginUser");
		if(loginUser==null) {
			req.setAttribute("msg", "로그인 해야 이용 가능합니다.");
			req.setAttribute("loc", "login.do");
			RequestDispatcher disp = req.getRequestDispatcher("message.jsp");
			disp.forward(req, response);
			
		//로그인을 하지 않았다면 다음 필터로 가지 못하게 return;	
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
