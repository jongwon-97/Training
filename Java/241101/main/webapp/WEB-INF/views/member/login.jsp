<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<link rel="stylesheet" href="${pageContext.request.contextPath}/css/login.css">
<%--
    el 표현식을 이용한 쿠키 출력
    ${cookie} ==> Map객체
    ${cookie.키이름} ==> cookie 객체
    ${cookie.키이름.value} ==> String 객체, 쿠키에 저장한 value 값

    ${cookie.uid.value} ==> 쿠키에 저장한 아이디 값
--%>
<div class="login-wrap">
    <h2>로그인</h2>
    <form name="loginF" action="login" method="post">
        <table>
            <tr>
                <td>아이디</td>
                <td>
                    <input type="text" name="userId"
                        value="${cookie.uid.value}"
                    id="userId" placeholder="아이디" required>
                </td>
            </tr>
            <tr>
                <td>비밀번호</td>
                <td>
                    <input type="password" name="userPw" id="userPw" placeholder="비밀번호" required>
                </td>
            </tr>
            <tr>
                <td colspan="2">
                    <label for="saveId">
                        <input type="checkbox" name="saveId"
                        <c:if test="${cookie.uid != null}">
                            checked
                        </c:if>
                        id="saveId">
                        아이디 저장
                    </label>
                    <button type="submit">로그인</button>
                </td>
            </tr>
        </table>
    </form>
</div>
