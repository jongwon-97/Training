<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>게시글 상세 정보</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/boardView.css">

<style>
/* 전체 배경 및 컨테이너 스타일 */
.wrap {
    width: 90%;
    max-width: 800px;
    margin: 40px auto;
    padding: 20px;
    background-color: #ffffff;
    border-radius: 12px;
    box-shadow: 0 4px 10px rgba(0, 0, 0, 0.1);
    font-family: Arial, sans-serif;
}

/* 제목 스타일 */
.wrap h2 {
    font-size: 2rem;
    color: #ff6f61;
    text-align: left;
    margin-bottom: 20px;
    font-weight: bold;
}

/* 테이블 스타일 */
.boardTable {
    width: 100%;
    border-collapse: collapse;
    margin-bottom: 20px;
}

.boardTable th, .boardTable td {
    padding: 15px;
    border: 1px solid #ddd;
}

.boardTable th {
    background-color: #f9f9f9;
    color: #333;
    font-weight: bold;
    text-align: left;
    width: 20%;
}

.boardTable td {
    background-color: #fafafa;
    color: #555;
}

.boardTable tr:nth-child(odd) td {
    background-color: #f5f5f5;
}

/* 버튼 컨테이너 및 버튼 스타일 */
.buttons {
    text-align: right;
    margin-top: 20px;
}

.buttons button {
    padding: 10px 20px;
    margin: 5px;
    border: none;
    border-radius: 8px;
    background-color: #ff6f61;
    color: white;
    font-size: 1rem;
    font-weight: bold;
    cursor: pointer;
    transition: background-color 0.3s ease;
}

.buttons button:hover {
    background-color: #e55c50;
}

/* 비밀번호 입력 폼 스타일 */
#divPasswd {
    width: 100%;
    margin: auto;
    padding: 12px;
    text-align: center;
    margin-top: 1em;
    display: none;
    background-color: #f9f9f9;
    border: 1px solid #ddd;
    border-radius: 8px;
}

#divPasswd label {
    font-weight: bold;
    color: #555;
}

#divPasswd input {
    padding: 8px;
    margin-left: 10px;
    border: 1px solid #ddd;
    border-radius: 4px;
    font-size: 0.9rem;
}

#btn1, #btn2 {
    border: none;
    padding: 8px 16px;
    border-radius: 8px;
    margin: 5px;
    font-weight: bold;
    cursor: pointer;
}

#btn1 {
    background-color: #ff6f61;
    color: white;
}

#btn2 {
    background-color: #ccc;
    color: #333;
}

#btn1:hover {
    background-color: #e55c50;
}

#btn2:hover {
    background-color: #b3b3b3;
}

/* 반응형 디자인 */
@media (max-width: 600px) {
    .wrap h2 {
        font-size: 1.8rem;
    }

    .boardTable th, .boardTable td {
        padding: 10px;
    }

    .buttons button {
        padding: 8px 16px;
        font-size: 0.9rem;
    }
}
</style>
</head>
<body>
<div class="wrap">
    <h2>Board View</h2>
    <table class="boardTable">
        <tr>
            <th>글번호</th>
            <td><c:out value="${board.num}"/></td>
            <th>작성일자</th>
            <td><fmt:formatDate value="${board.wdate}" pattern="yy-MM-dd"/></td>

        </tr>
        <tr>
            <th>글쓴이</th>
            <td><c:out value="${board.userId}"/></td>
            <th>조회수</th>
            <td><c:out value="${board.readnum}"/></td>


        </tr>
        <tr>
            <th>첨부파일</th>
            <td colspan="3">
                <a href ="/board_upload/${board.fileName}" download>
                    <c:out value="${board.originFileName}"/>
                </a>
                    [<c:out value = "${board.fileSize}"/> bytes]
            </td>
        </tr>
        <tr>
            <th>제목</th>
            <td colspan="3">
            <c:out value="${board.title}"/>
            </td>
        </tr>
        <tr>
            <th>글내용</th>
            <td colspan="3">
            ${board.content}
            </td>
        </tr>
    </table>
    <form id="frm">
        <input type = "hidden" name ="num"
        value="<c:out value='${board.num}'/>">
        <!-- 수정 또는 삭제할 글번호 : hidden data -->
            <div id = "divPasswd">
                <label for = "passwd"> 비밀번호 </label>
                    <input type="password" name = "passwd" id = "passwd"
                    placeholder = "Password" required>
                        <button id = "btn1"></button>
                        <button id = "btn2">취소</button>
            </div>
    </form>


    <div class="buttons">
        <!-- 로그인 한 사람이 글을 쓴 사람이 라면  -->

        <c:if test="${loginUser.userId == board.userId}">
            <button onclick="goEdit()">수정</button>
            <button onclick="goDel()">삭제</button>
        </c:if>
        <button onclick="location.href='/board/list'">목록으로 돌아가기</button>
    </div>
    <script>
        const goEdit =function(){
            const frm =document.getElementById("frm");
            const div=document.getElementById('divPasswd');
            const btn1 = document.getElementById('btn1');
            frm.action="/user/edit";
            frm.method="post";
            div.style.display="block";
             btn1.innerText='글수정';

        }
        const goDel =function(){
            let yn = confirm('게시글을 삭제할까요?');
            if (!yn) return;
                const frm =document.getElementById("frm");
                frm.action ="/user/delete";
                frm.method ="post";

                //비밀번호 입력 폼 보여주기
                const div=document.getElementById('divPasswd');
                div.style.display = 'block';

                //글 삭제 버튼 만들기
                const btn1 = document.getElementById('btn1');
                btn1.innerText='글삭제';

        }
    </script>
</div>
</body>
</html>
