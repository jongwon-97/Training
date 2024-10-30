package com.kosmo.user.controller;

import com.kosmo.user.domain.MemberDTO;
import com.kosmo.user.exception.NoMemberException;
import com.kosmo.user.service.MemberService;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@Slf4j
public class LoginController {

    private final MemberService userService; // MemberService 변수 추가

    @Autowired
    public LoginController(MemberService userService) { // 생성자 주입
        this.userService = userService;
    }
    @GetMapping("/login")
    public String loginForm() {
        return "member/login";
    }
    @PostMapping("/login")
    public String loginProcess(MemberDTO tmpUser, HttpSession ses, Model model){
        log.info("tmpUser:{}",tmpUser);
        //1.유효성 체크 (userId,userPw)빈문자열 체크
        // "/login" 으로 redirect 이동
        if (tmpUser.getUserId() == null || tmpUser.getUserId().trim().isBlank()||
                tmpUser.getUserPw() == null || tmpUser.getUserPw().trim().isBlank()) {
            model.addAttribute("error", "아이디와 비밀번호를 입력하세요.");
            return "member/login"; // 로그인 페이지로 다시 이동
        }
        //2.userService의 loginCheck(tmpUser)호출
        try {
            // 회원이 맞다면 ==> MemberDTO반환
            MemberDTO member = userService.loginCheck(tmpUser);
            // 세션에 MemberDTO 객체를 저장 ."loginUser" 키값으로 저장
            ses.setAttribute("loginUser", member);
        } catch (NoMemberException e) {
        // 아니라면 ==> NoMemberException을 발생
            model.addAttribute("error", e.getMessage());
            return "member/login"; // 로그인 페이지로 다시 이동
        }
        return "redirect:main"; //홈으로 이동
    }
}
