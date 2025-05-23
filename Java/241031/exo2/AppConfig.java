package com.kosmo.spring_mybatis_demo.basic.ioc.exo2;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

//xml이 아닌 java 클래스로 빈 설정을 해보자
@Configuration //현재 AppConfig 클래스를 환경 설정으로 사용하겠다는 의미
public class AppConfig {

    @Bean(name="e1") // <bean id ="e1" class="...ex02.Emp/>
    //스프링은 기본적으로 Bean(객체)를 단일 객체로 생성해 관리한다 =>singleton
    @Scope(value = "prototype") //디폴트 value="singleton","prototype"으로 설정하면 매번
                                //다른 객체를 생성한다.
    public Emp empInfo(){
        return new Emp("Scott","Research",3000);
    }//----------------

    @Bean   //Bean의 name을 기술하지 않으면 메서드 이름이 빈의 이름이 된다
    public Emp empInfo2(){  //<bean id="empInfo2" class="....Emp"/>
        Emp e2 = this.empInfo();
        e2.setName("James");
        e2.setDept("Sales");
        e2.setSalary(5000);
        return e2;
    }//-------------

    @Bean //빈의 name: service가 된다
    public ServiceImpl service(){
        return new ServiceImpl();
    }
}

