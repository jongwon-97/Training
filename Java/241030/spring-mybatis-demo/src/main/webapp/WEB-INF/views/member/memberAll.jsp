<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!-- 외부 CSS 파일 연결 -->
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/style.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/signup.css">

<div class="container">
    <h1>회원 목록 [관리자 페이지 - Admin]</h1>

    <table id="members">
        <thead>
            <tr>
                <th>번호</th>
                <th>이름</th>
                <th>아이디</th>
                <th>이메일</th>
                <th>가입일</th>
                <th>회원상태</th>
                <th>수정|삭제</th>
            </tr>
        </thead>
        <tbody>
            <c:if test="${members == null || empty members}">
                <tr>
                    <td colspan="7">데이터가 없습니다</td>
                </tr>
            </c:if>
            <c:if test="${members != null && not empty members}">
                <c:forEach var="user" items="${members}">
                    <tr>
                        <td>${user.getIdx()}</td>
                        <td>${user.getName()}</td>
                        <td>${user.userId}</td>
                        <td>${user.email}</td>
                        <td>${user.indate}</td>
                        <td class="mstate${user.mstate}">${user.mstateStr}</td>
                        <td>
                            <a href="javascript:edit('${user.idx}','edit')">수정</a>
                            <a href="javascript:remove('${user.idx}','remove')">삭제</a>
                        </td>
                    </tr>
                </c:forEach>
            </c:if>
        </tbody>
    </table>
<!-- 수정 또는 삭제 form -->
<form name="frm" method="post">
    <input type="hidden" name="idx">
</form>

   <script>
       function edit(idx, mode){
           frm.idx.value = idx;
           if(mode=='edit'){
               frm.action="memberInfo";
           }
           frm.method="post";
           frm.submit();//서버에 전송
       }//-------------------------
      function remove(idx, mode){
         //alert(idx);
         let yn=window.confirm(idx+"번 회원정보를 삭제할까요?");
         if(yn){
             frm.idx.value = idx;
             if(mode=='remove') {
                 frm.action='memberDelete';
                 frm.method='post';
                 frm.submit();
             }
         }
      }
   </script>
</div>