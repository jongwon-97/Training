<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<link rel="stylesheet" href="${pageContext.request.contextPath}/css/login.css">
<script>
    // 서버에서 전달된 오류 메시지가 있을 경우 alert 창을 띄웁니다.
    <c:if test="${not empty error}">
        alert('${error}');
    </c:if>
</script>

<div class="login-wrap">
    <h2>로그인</h2>
    <form name="loginF" action="login" method="post">
        <table>
            <tr>
                <td>아이디</td>
                <td>
                    <input type="text" name="userId" id="userId" placeholder="아이디" required>
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
                        <input type="checkbox" name="saveId" id="saveId">
                        아이디 저장
                    </label>
                    <button type="submit">로그인</button>
                </td>
            </tr>
        </table>
    </form>
</div>
