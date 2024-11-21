package com.kosmo.nexus.service;

import com.kosmo.nexus.dto.LoginDTO;
import com.kosmo.nexus.exception.NoMemberException;
import com.kosmo.nexus.mapper.MemberMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberServiceImpl implements MemberService{

    private final MemberMapper userMapper;
    //private final PasswordEncoder passwordEncoder;

    @Autowired
    public MemberServiceImpl(MemberMapper userMapper) {
        this.userMapper=userMapper;
        //this.passwordEncoder = passwordEncoder;
    }


    @Override
    public List<LoginDTO> listMember() {
        return List.of();
    }

    @Override
    public LoginDTO findMemberByUserId(String userId) {
        return null;
    }

    @Override
    public boolean idCheck(String member_id) {
        LoginDTO user = userMapper.findMemberByUserId(member_id);

        if(user != null){
            return false;
        }

        return true;
    }

    @Override
    public LoginDTO loginCheck(LoginDTO tmpUser) throws NoMemberException {
        LoginDTO member = userMapper.findMemberByUserId(tmpUser.getMember_id());
        // 회원이 없거나 비밀번호가 일치하지 않으면 예외 발생
        if (member == null || !member.getMember_pw().equals(tmpUser.getMember_pw())) {
            throw new NoMemberException("아이디 또는 비밀번호가 틀렸습니다.");
        }
        return member;
    }
}
