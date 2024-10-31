package com.kosmo.spring_mybatis_demo.basic.ioc.exo2;

import org.springframework.context.ApplicationContext; // 올바른 임포트
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class HelloSpringApp2 {
    public static void main(String[] args) {
        //스프링 컨테이너 ApplicationContext
        //xml 설정 ==>

        ApplicationContext ctx
                =new AnnotationConfigApplicationContext(AppConfig.class);

        Emp e1 = ctx.getBean("e1", Emp.class);
        System.out.println("e1 :"+e1);

        Emp e2 = ctx.getBean("empInfo", Emp.class);
        System.out.println("e2 :"+e2);

        //ServiceImpl 객체를 lookup 해서 info()메서드를 호출해보세요
        ServiceImpl svc = (ServiceImpl)ctx.getBean("service", Service.class);
        svc.info();

    }

}
