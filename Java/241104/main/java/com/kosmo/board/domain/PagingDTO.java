package com.kosmo.board.domain;

import lombok.Data;
//페이징 처리 및 검색 기능을 모듈화 하여 재사용 가능하도록 처리하는 객체
@Data
public class PagingDTO {
    //페이징 처리 관련 프로퍼티
    private int pageNum;         // 현제 보여줄 페이지 번호
    private int oneRecordPage=5; // 페이지당 보여줄 목록개수
    private int totalCount;      // 총 게시글 수 DB에서 가져와 설정할 예정
    private  int pageCount;      // 총 게시글 수와 oneRecordPage와의 연산으로 설정

    //DB에서 레코드를 끊어오기 위한 프로퍼티
    private int start;
    private int end;

    //페이징 블럭처리를 위한 프로퍼티
    private int prevBlock;      //이전 5개 (이전블럭)
    private int nextBlock;      //이후 5개 (다음블럭)
    private int pagingBlock=5;  //한 블럭 당 보여줄 페이지 수

    //검색 관련 프로퍼티
    private String findType;   //검색 유형
    private String findKeyword;//검색어

    public void init(){
        //페이지 수 구하기
        pageCount=(totalCount-1)/oneRecordPage+1;
        if(pageNum<1){
            pageNum=1; // 1페이지를 디폴트로 설정
        }
        if(pageNum>pageCount){
            pageNum=pageCount;
        }
        //DB에서 끊어서 가져올 변수값 연산하기 (start,end)
        end=pageNum * oneRecordPage;
        start = end - (oneRecordPage-1);
        //where rn between 1 and 5
    }

}
