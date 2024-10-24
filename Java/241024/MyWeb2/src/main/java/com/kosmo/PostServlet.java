package com.kosmo;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/post.do")
public class PostServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		//1. 사용자가 입력한 값(userid,userpw)
		req.setCharacterEncoding("utf-8"); 
		String id = req.getParameter("userid"); //input name
		String pw = req.getParameter("userpw");
		
		res.setContentType("text/html; charset=utf-8");
		PrintWriter out = res.getWriter();
		//2.유효성체크
		if( id==null|| pw == null|| id.trim().isEmpty()|| pw.trim().isEmpty()) {
			//trim 공백을 제거 isEmpty/isBlank (공백인지 확인)
			out.println("<script>");
			out.println("alert('아이디,비밀번호를 입력해야합니다')");
			out.println("history.back()");
			out.println("</script>");
			return; //return 값을 안해주면 다음로직으로 넘어감
		}
		//3.DB관련 로직처리 ===> Model에게 넘긴다
		out.println("<h1>아이디 : " +id+ "</h1>");
		out.println("<h1>비밀번호: " +pw+ "</h1>");
		
		out.close(); // 닫아주기 
	}

}
