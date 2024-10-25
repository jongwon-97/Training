package com.kosmo.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kosmo.model.MemberDAO;
import com.kosmo.model.MemberDTO;
//GET /memberInfo.do?idx=10 ==> 회원정보 가져오기(select)
//POST /memberInfo.do ==> 회원정보 수정하기(update)
@WebServlet("/memberInfo.do")
public class MemberEditController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1.회원번호 받기
		String idx = request.getParameter("idx");
		//2.유효성체크
		if(idx == null || idx.trim().isBlank()) {
			response.sendRedirect("memberList.do");
			return;
		}
		//3. MemberDAO의 getMember(idx) 호출 ==> MemberDTO
		MemberDAO dao = new MemberDAO();
		MemberDTO user = dao.getMember(Integer.parseInt(idx.trim()));
		
		//4.req에 MemberDTO 객체에 저장
		request.setAttribute("user", user);
		//5.member/memberInfo.jsp 로 foward이동
		RequestDispatcher disp = request.getRequestDispatcher("member/memberInfo.jsp");
		disp.forward(request, response);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		//0. post 방식 한글처리
		req.setCharacterEncoding("utf-8");
		//1. 수정한 회원정보 받기 (name,userId,email)
		String name=req.getParameter("name");
		String userId=req.getParameter("userId");
		String email=req.getParameter("email");
		int idx = Integer.parseInt(req.getParameter("idx"));
		//2. 유효성 체크
		if(name.trim().isBlank()||userId.trim().isBlank()||email.trim().isBlank()) {
			res.sendRedirect("/memberList.do");//redirect방식으로 이동
			return;
		}
		//3.받은값을 DTO객체에 담기
		 MemberDTO user = new MemberDTO(name, userId, email);
		//4.MemberDAO객체 생성후 update(MemberDTO객체) 호출하기
		
		MemberDAO dao = new MemberDAO();
	    int result = dao.updateMember(user,idx);
		
	    //5.받은 결과값(int result)에 따라 msg, loc처리
	    String msg = (result > 0)? "수정 성공" : "수정 실패";
	    String loc = "/memberList.do";	
		
	    //6.request에 msg loc 저장   
	    req.setAttribute("msg", msg);
	    req.setAttribute("loc", loc);
		//7. message.jsp로 forward 이동
		RequestDispatcher disp=req.getRequestDispatcher("message.jsp");
		disp.forward(req, res);
	}

}
