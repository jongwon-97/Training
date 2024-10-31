package com.kosmo.spring_mybatis_demo;

import com.kosmo.common.interceptor.AdminCheckInterceptor;
import com.kosmo.common.interceptor.LoginCheckInterceptor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


/*
WebMvcConfigurer 인터페이스는 Spring MVC의 설정을 확장하고 커스터마이징할 수 있도록 제공되는 인터페이스입니다.
이를 통해 Spring Boot의 기본 설정을 그대로 유지하면서, 특정 기능을 필요에 맞게 추가하거나 수정할 수 있습니다.
특히, Spring Boot에서는 기본적으로 대부분의 설정이 자동으로 적용되기 때문에, 필요한 설정만 개별적으로 적용할 때 유용합니다.
 */
@Configuration
@RequiredArgsConstructor
public class WebConfig implements WebMvcConfigurer {

    private final LoginCheckInterceptor loginCheckInterceptor;
    private final AdminCheckInterceptor adminCheckInterceptor;
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //1. 로그인 체크 인터셉터 먼저 등록
        registry.addInterceptor(loginCheckInterceptor)
                .addPathPatterns("/user/**","/admin/**") //로그인이 필요한 패턴
                .order(1);//우선 순위를 1로 설정
        //2. 관리자 체크 인터셉터 등록
        registry.addInterceptor(adminCheckInterceptor)
                .addPathPatterns("/admin/**") // 관리자 접근 경로 설정
                .order(2);
    }

}
