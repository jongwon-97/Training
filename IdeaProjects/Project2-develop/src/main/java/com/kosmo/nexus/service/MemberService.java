package com.kosmo.nexus.service;

import com.kosmo.nexus.dto.LoginDTO;
import com.kosmo.nexus.exception.NoMemberException;

import java.util.List;

public interface MemberService {

    List<LoginDTO> listMember();
    LoginDTO findMemberByUserId(String member_Id);
    boolean idCheck(String member_id);
    LoginDTO loginCheck(LoginDTO tmpUser) throws NoMemberException;
}
