package com.kosmo.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet("/logout.do")
public class LogoutController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doGet(HttpServletRequest req, HttpServletResponse res) 
			throws ServletException, IOException {
		//1 특정 세션 변수를 삭제 session.reamoveAttribute("세션변수")
		//2 모든 세션 변수를 삭제 session.invalidate():
		HttpSession session = req.getSession();
		session.invalidate();
		res.sendRedirect("index.do");
	
	}

}
