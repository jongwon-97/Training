<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<link rel="stylesheet"  href="${pageContext.request.contextPath}/resources/css/signup.css">

<div class="signup-container">
    <form action="signup" method="post" class="signup-form">
        <h1 class="signup-header">회원가입</h1>
        <div class="form-group">
            <label for="name">이름</label>
            <input type="text" name="name" id="name" placeholder="이름을 입력하세요">
        </div>
        <div class="form-group">
            <label for="userId">아이디</label>
            <input type="text" name="userId" id="userId" placeholder="아이디를 입력하세요" readonly>
            <br>
            <button type="button" class="id-check-button" onclick="openWin()">아이디 중복체크</button>
        </div>
        <div class="form-group">
            <label for="userPw">비밀번호</label>
            <input type="password" name="userPw" id="userPw" placeholder="비밀번호를 입력하세요">
        </div>
        <div class="form-group">
            <label for="email">이메일</label>
            <input type="email" name="email" id="email" placeholder="이메일을 입력하세요">
        </div>
        <div class="button-group">
            <button class="button signup-button" type="submit">회원가입</button>
            <button class="button reset-button" type="reset">다시쓰기</button>
        </div>
    </form>
    <script>
        let win = null;
        const openWin = function(){
            //open(url,target,"속성들")
            let url ="idCheck"
            win = window.open(url,"idCheck","width=400,height=400,left=100,top=100");//팝업창 열기
        }
    </script>
</div>