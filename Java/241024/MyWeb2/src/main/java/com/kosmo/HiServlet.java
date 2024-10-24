package com.kosmo;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class HiServlet
 */
@WebServlet("/hi2.do")
public class HiServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		res.setContentType("text/html; charset=utf-8");

		// 응답보낼 웹문서의 컨텐트 타입을 지정
		PrintWriter out = res.getWriter();

		// res ==> 브라우저 ==> 브라우저에 출력할 스트림(PrintWriter)

		out.println("<h1>HelloServlet</h1>");
		out.println("<h2>안녕서블릿</h2>");
		out.close();
		// 클라이언트가 보낸 요청 데이터를 받아보자 req.getParameter로 받는다.
		String name = req.getParameter("name");
		out.println("<h2 style='color:green'>환영합니다" + name + "님~~</h2>");

	}

}
