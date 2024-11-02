package com.kosmo.common.interceptor;

import com.kosmo.user.domain.MemberDTO;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.io.IOException;

//HandlerInterceptorAdater 추상클래스 상속받아 구현하세요
//세션에 저장된 loginUser를 꺼내서 getMstate ()값이 9 가 아니라면
//"관리자만 이용 가능합니다 " ,"/main"으로 이동시키기
//구현후 WebConfig에 addInterceptor registry 에 등록하기
@Component
public class AdminCheckInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest req,
                             HttpServletResponse res, Object handler)
        throws ServletException, IOException
    {
        HttpSession ses = req.getSession();
        MemberDTO user=(MemberDTO) ses.getAttribute("loginUser");

        // 세션에 loginUser가 존재하고, getMstate 값이 9인지 확인
        if (user == null || user.getMstate() != 9) {

            req.setAttribute("msg", "관리자만 이용 가능합니다.");
            req.setAttribute("loc","/main");
            RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/message.jsp");
            dispatcher.forward(req,res);
            return false; // 요청 처리 중단
        }

        return true; // 요청 계속 처리
    }
    }
