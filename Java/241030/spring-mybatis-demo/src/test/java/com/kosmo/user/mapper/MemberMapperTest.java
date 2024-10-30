package com.kosmo.user.mapper;

import com.kosmo.spring_mybatis_demo.SpringMybatisDemoApplication;
import com.kosmo.user.domain.MemberDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest(classes = SpringMybatisDemoApplication.class)
@Transactional //테스트 후 DB에 롤백울 한다
@Rollback(false)//false를 줘야 DB에 데이터가 들어갔는지 확인할 수 있다.
class MemberMapperTest {

    @Autowired //byType으로 객체를 주입하는 이노테이션 MemberMapper타입의 객체를 주입
    private MemberMapper userMapper;

    @Test
    public void testInsertMember(){
        //Given - When - Then
        //GIven
        MemberDTO user = new MemberDTO();
        user.setName("최봄");
        user.setUserId("choi2");
        user.setUserPw("1111");
        user.setEmail("ko@naver.com");
        user.setMstate(1);
        //When
        int result;
        result = userMapper.insertMember(user);

        //Then 검증
        Assertions.assertEquals(1,result);

    }

}