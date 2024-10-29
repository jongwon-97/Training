package com.kosmo.user.mapper;

import com.kosmo.user.domain.MemberDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
//xxxMapper 인터페이스를 선언하면
//해당 인터페이스를 구현한 객체를 스프링 컨테이너가
//알아서 제공한다, MapperProxy
// Mapper 파일이 위치한곳 ==> application.properties를 찾아서
//해당파일(xml)을 읽어서 실행시킨
@Mapper
public interface MemberMapper {

    int insertMemeber(MemberDTO user);
    List<MemberDTO> listMember();

    int insertMember(MemberDTO user);
}
