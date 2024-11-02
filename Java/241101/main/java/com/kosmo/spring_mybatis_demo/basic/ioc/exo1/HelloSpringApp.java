package com.kosmo.spring_mybatis_demo.basic.ioc.exo1;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

public class HelloSpringApp {
    public static void main(String[] args) {
        // 스프링컨테이너가 관리하는 객체를 이름으로 찾아 사용해보자
        String resource = "src/main/java/com/kosmo/spring_mybatis_demo/basic/ioc/exo1/appContext.xml";

        // 스프링 컨테이너
        ApplicationContext ctx = new FileSystemXmlApplicationContext(resource);

        MessageBean mb1 = (MessageBean) ctx.getBean("mb2");
        mb1.sayHi();
        mb1.sayHello("BTS","BlackPink","송하니");
    }
}
