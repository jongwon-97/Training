package com.kosmo.spring_mybatis_demo.basic.ioc.exo2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

public class ServiceImpl implements Service{

    @Autowired //byType 으로 주입 한다 Emp타입의 객체가 있으면 주입
    @Qualifier("empInfo2") //동일한 타입의 객체가 여러개일때 어떤 객체를 주입할지 설정 해주어야함
                    //어노테이션을 기술하여 어떤 객체인지 명시해야 함
    private Emp emp; //Emp타입의 객체가 2개 있음 (e1 ,empInfo2)
    //ServiceImpl이 Emp에 의존한다 (Emp를 사용한다)
    @Override
    public void info(){
        System.out.printf("Name: %s%n Dept: %s%n salary:%d%n",
                emp.getName(),emp.getDept(),emp.getSalary());
        

    }
}
