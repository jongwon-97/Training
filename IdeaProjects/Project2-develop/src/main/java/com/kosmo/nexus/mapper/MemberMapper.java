package com.kosmo.nexus.mapper;

import com.kosmo.nexus.dto.LoginDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MemberMapper {

    //List<LoginDTO> listMember();
    LoginDTO findMemberByUserId(String member_id);
}
