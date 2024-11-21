package com.kosmo.nexus.controller;

import com.kosmo.nexus.dto.LoginDTO;
import com.kosmo.nexus.exception.NoMemberException;
import com.kosmo.nexus.service.MemberService;
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
public class LoginController {

    private final MemberService userService;
    @Autowired
    public LoginController(MemberService userService) { // 생성자 주입
        this.userService = userService;
    }
    //개인회원 로그인 페이지
    @GetMapping("/pLogin")
    public String personalLoginForm(){

        return "member/personal"; // static/member/personal.html 로그인 페이지로 이동
    }

    //개인회원 로그인 프로세스
    @PostMapping("pLogin")
    public String pLoginProcess(LoginDTO tmpUser, HttpSession ses,
                                @RequestParam(name="saveId",defaultValue = "false")boolean saveId,
                                HttpServletResponse res)
        throws NoMemberException {
        log.info("SaveId:{}",saveId);
        log.info("tmpUser:{}",tmpUser);

        //1.유효성 체크 (userId,userPw)빈문자열 체크
        // "/login" 으로 redirect 이동
        if (tmpUser.getMember_id() == null || tmpUser.getMember_id().trim().isBlank()||
                tmpUser.getMember_pw() == null || tmpUser.getMember_pw().trim().isBlank()) {
            return "redirect:/pLogin"; // 로그인 페이지로 다시 이동
        }
        //2.userService의 loginCheck(tmpUser)호출
        // 회원이 맞다면 ==> MemberDTO반환
        LoginDTO loginUser = userService.loginCheck(tmpUser);
        if(loginUser != null){

            ses.setAttribute("loginUser", loginUser);

            Cookie ck = new Cookie("uid",loginUser.getMember_id());

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
            return "redirect:/mypage";
        }
        return "redirect:/pLogin";
    }
    @GetMapping("/mypage")
    public String mypage() {
        return "member/mypage"; // mypage.html로 이동
    }

    //기업회원 로그인 페이지
    @GetMapping("/bLogin")
    public String businessLoginForm() {
        log.info("test");
        return "member/business"; // static/member/business.html
    }

    //기업회원 로그인 프로세스
    @PostMapping("bLogin")
    public String bLoginProcess(){
        return "redirect:/bLogin";
    }
}
