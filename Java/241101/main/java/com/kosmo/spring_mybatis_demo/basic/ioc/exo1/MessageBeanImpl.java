package com.kosmo.spring_mybatis_demo.basic.ioc.exo1;

import lombok.Setter;

import java.util.Date;
import java.util.Random;
@Setter //setter 메서드 구성
public class MessageBeanImpl implements MessageBean{

    //property field , 멤버변수
    private String greeting;
    private String name;

    private Date today;
    private Random random;
    //프로퍼티 값을 설정파일(appContext.xml)에서 넣어줄 예정(injection)
    //setter injection constructor injection

    @Override
    public void sayHello(String... names) {
        System.out.println(greeting);
        if(names != null){
            for(String nm:names){
                System.out.println(nm+",|");
            }//for -----------------
        }//if-----------------

    }//------------------

    @Override
    public void sayHi() {
        System.out.println(greeting+""+name+"^^");
        System.out.println("오늘 날짜는 :" +today.toString());
        System.out.println("행운의 숫자 :"+ random.nextInt(100));
    }
}
