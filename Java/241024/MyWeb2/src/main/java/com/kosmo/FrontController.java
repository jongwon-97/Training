package com.kosmo;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("*.me")
public class FrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	//get방식
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		process(request,response);

	}
	//post방식
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		process(request,response);

	}
	private void process(HttpServletRequest req, HttpServletResponse res) 
			throws ServletException, IOException
	{
		// 서블릿 :컨트롤러 역할 , 클라이언트의 요청을 분석해서 로직을 수행하는 모델로 해당요청처리를 넘기고
		// 모델이 반환한 결과를 받아 view페이지로 페이지 이동을 한다
		String uri =req.getRequestURI();
		System.out.println("uri :"+ uri); 
		if(uri == null) {
			return;
		}
		String viewPage = "index.html";
		if(uri.equals("/login.me")) {
			//form.jsp로 페이지 이동
			viewPage = "form2.jsp";
		}
		else if(uri.equals("/loginEnd.me")) {
			//로그인 처리 결과를 보여주는 페이지로 이동
			//로그인 처리 로직은 Model이 수행
			//후 처리 결과를 req/session 등에 저장을 한다
			req.setAttribute("message", "환영합니다. 회원인증 처리 Success");
			req.setAttribute("loc", "index.html");
			
			viewPage = "loginResult.jsp";
			
			//redirect로 이동 테스트
//			res.sendRedirect(viewPage);
//			return;
		}
		else if(uri.equals("/join.me")) {
			//회원가입 페이지로 이동
			
			viewPage = "join.jsp";
		}
		else if(uri.equals("/joinEnd.me")) {
			req.setAttribute("message", "회원가입 처리 성공-로그인 페이지로 이동합니다.");
			req.setAttribute("loc", "index.html");
			viewPage = "joinResult.jsp";
		}
		//페이지이동
		//[1] redirect방식으로 이동하는방법 <== 20% :res.sendRedirect("뷰페이지");
		//[2] forward방식으로 이동하는방법  <== 80% :RequestDispatcher의 forward()메서드를 이용
		
		RequestDispatcher disp = req.getRequestDispatcher(viewPage);
		disp.forward(req, res); //forward 방식으로 이동 (서버내부에서 이동). req,res를 가져가서 
		//viewPage(jsp)와 공유한다.
		
		
	}
	

}
