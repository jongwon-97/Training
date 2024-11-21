package com.kosmo.nexus.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor  //기본 생성자
//@AllArgsConstructor //인자 생성자 (거의 안씀)
@Setter //setxxx()
@Getter //getxxx()
@ToString //toString() 오버라이드
public class MemberDTO {
    private String member_id;           //아이디
    private String member_rank;         //직급
    private int num;                    //사번
    private String member_name;         //이름
    private String member_department;   //부서
    private String member_phone;        //전화번호
    private String member_birth;        //생년월일
    private String member_gender;       //성별
    private String member_email;        //이메일
    private String member_address;      //주소
    private String member_start_date;   //회원생성일
    private String member_img;          //이미지
    private String member_role;         //권한
    private String member_status;       //상태
    private String member_last_date;    //마지막 접속일
    private int company_id;             //회사 ID
    private int member_sns;             //sns 번호 (api 연동용 아닐때 0 구글 1 네이버 2 카카오 3)
    private int member_postcode;        //우편번호  (api 연동용 우편번호 int)

}
