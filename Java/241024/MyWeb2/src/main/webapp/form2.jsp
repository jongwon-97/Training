<%@page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!-- login.me ==>서브밋 ==>loginEnd.me (request에저장)==>loginResult.jsp로 forward이동 -->
<div id="wrap">
	<h1>Login Page (form2.jsp)</h1>
	<form action="loginEnd.me" method="post">
		아이디: <input type="text" name="userid"> 
		<br><br> 
		비밀번호: <input type="password" name="userpw">
		<br><br>
		<button type="submit">로그인</button>
		<button type="reset">다시쓰기</button>
	</form>
</div>