package com.kosmo.user.controller;

import com.kosmo.user.domain.MemberDTO;
import com.kosmo.user.exception.NoMemberException;
import com.kosmo.user.service.MemberService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@Slf4j
//@RequiredArgsConstructor
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
    public String loginProcess(MemberDTO tmpUser, HttpSession ses,
                               @RequestParam(name="saveId",defaultValue = "false")boolean saveId,
                               HttpServletResponse res)
        throws NoMemberException
        {
        log.info("SaveId:{}",saveId);
        log.info("tmpUser:{}",tmpUser);
        //1.유효성 체크 (userId,userPw)빈문자열 체크
        // "/login" 으로 redirect 이동
        if (tmpUser.getUserId() == null || tmpUser.getUserId().trim().isBlank()||
                tmpUser.getUserPw() == null || tmpUser.getUserPw().trim().isBlank()) {
            return "redirect:/login"; // 로그인 페이지로 다시 이동
        }
        //2.userService의 loginCheck(tmpUser)호출
            // 회원이 맞다면 ==> MemberDTO반환
            MemberDTO loginUser = userService.loginCheck(tmpUser);
            if(loginUser != null){

                ses.setAttribute("loginUser", loginUser);

                Cookie ck = new Cookie("uid",loginUser.getUserId());

                if(saveId){
                    //쿠키 유효시간 설정 ==> 7일간 유효 하도록 설정
                    ck.setMaxAge(7*24*60*60); //7일간 유효 하도록 설정
                }else{//체크 안한경우 =>쿠키 삭제
                    ck.setMaxAge(0);//유효시간을 0으로 설정하면 쿠키 삭제
                }
                ck.setPath("/");
                //ck.setDomain (특정 도메인);

                //응답 객체에 쿠키 끼워 넣기
                res.addCookie(ck);
        }
        return "redirect:main"; //홈으로 이동
    }
    @GetMapping("/logout")
    public String logout(HttpSession ses){
        ses.invalidate();
        return "redirect:main";
    }
    /* 스프링의 예외 처리 방법
        [1] @ExceptionHandler 어노테이션을 붙인 예외 처리 메서드 구성
        [2] @ControllerAdvice 어노테이션을 붙인 클래스를 구성 ==> 예외처리 메서드 모음
        ==>권장사항
    */
    /*@ExceptionHandler(NoMemberException.class)
    public String exceptionHandler(Exception ex,Model m){
        m.addAttribute("msg",ex.getMessage());
        m.addAttribute("loc","javascript:history.back()");
        return "message";
    }*/
}
