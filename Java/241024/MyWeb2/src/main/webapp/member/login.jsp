<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div id="wrap">
	<h1>Login Page (form2.jsp)</h1>
	<form action="login.do" method="post">
		아이디: <input type="text" name="userid"> 
		<br><br> 
		비밀번호: <input type="password" name="userpw">
		<br><br>
		<button type="submit">로그인</button>
		<button type="reset">다시쓰기</button>
	</form>
</div>