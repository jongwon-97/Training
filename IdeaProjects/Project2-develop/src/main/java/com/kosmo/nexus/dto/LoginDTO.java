package com.kosmo.nexus.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@Getter
@Setter
@ToString
public class LoginDTO {
    private String member_id;           //아이디
    private String member_pw;           //비밀번호
    private String member_name;         //이름
    private int member_num;             //사번
    private int company_id;             //회사 ID
    private String member_phone;        //전화번호
    private String member_email;        //이메일
}
