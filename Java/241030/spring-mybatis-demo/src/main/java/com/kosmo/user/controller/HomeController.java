package com.kosmo.user.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

//POJO : Plane Old Java Object
@Controller
public class HomeController {
    @RequestMapping(value="/home",method = RequestMethod.GET)
    public String home(){

        return "hello"; //뷰 네임
        //물리적 경로: "/WEB-INF/views/hello.jsp"

    }
    //"/test" url pattern ==> test.jsp를 보여주도록 메서드 구성
    @GetMapping("/test")
    public String showTest(Model model){
        //request에 저장 ==> 스프링에서는 Model 또는 ModelAndView 객체에 저장
        model.addAttribute("msg", "반가워 스프링부트");

        return "test";
    }
    //뷰네임을 반환하지 않으면 @RequestMapping/@GetMapping...
    //url에 접두어,접미어를 붙여 이동
    //WEB-INF/view/main.jsp를 찾는다
    @GetMapping("/main")
    public void main(){
        
    }
}
