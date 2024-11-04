package com.kosmo.user.service;

import com.kosmo.user.domain.MemberDTO;
import com.kosmo.user.exception.NoMemberException;
import com.kosmo.user.mapper.MemberMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

//컨트롤러 계층에는 @Controller,@RestController
//서비스 계층에는 @Service 을 붙인다
//영속성(perisistence)계층 : @Mapper(MyBatis) / @ Repository(JPA)
@Service
public class MemberServiceImpl implements MemberService{

    private final MemberMapper userMapper;

    @Autowired //=> 생략해도 자동으로 된다
    public MemberServiceImpl(MemberMapper userMapper) {
        this.userMapper=userMapper;
    }
    @Override
    public int insertMember(MemberDTO user) {
        return userMapper.insertMember(user);
        //스프링은 namespace 가 com.kosmo.user.mapper.MemberMapper로 되어 있는
        //xml파일을 찾는다. 찾았다면 id 가 insertMember로 되어 있는 sql문을 찾아 실행시킨다.
    }

    @Override
    public List<MemberDTO> listMember() {
        return userMapper.listMember();
    }

    @Override
    public MemberDTO findMemberByUserId(String userId) {
        return userMapper.findMemberByUserId((userId));
    }

    @Override
    public MemberDTO findMemberByIdx(int idx) {
        return userMapper.findMemberByIdx(idx);
    }

    @Override
    public int deleteMemberByIdx(int idx) {
        return userMapper.deleteMemberByIdx(idx);
    }

    @Override
    public int updateMember(MemberDTO user) {
        return userMapper.updateMember(user);
    }

    @Override
    public boolean idCheck(String userId) {
        MemberDTO user = userMapper.findMemberByUserId(userId);

        if(user != null){
            return false;
        }

        return true;
    }

    @Override
    public MemberDTO loginCheck(MemberDTO tmpUser) throws NoMemberException {

        MemberDTO member = userMapper.findMemberByUserId(tmpUser.getUserId());
        // 회원이 없거나 비밀번호가 일치하지 않으면 예외 발생
        if (member == null || !member.getUserPw().equals(tmpUser.getUserPw())) {
            throw new NoMemberException("아이디 또는 비밀번호가 틀렸습니다.");
        }
        return member; // 로그인 성공 시 MemberDTO 반환
    }
}
