package com.kosmo.user.domain;

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
    //property
    private int idx;
    private String name;
    private String userId;
    private String userPw;
    private String email;
    private int mstate;
    private java.sql.Date indate;
    private String mtateStr;
}
