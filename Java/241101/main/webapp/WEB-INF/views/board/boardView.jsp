<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>게시글 상세 정보</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/boardView.css">

<style>

.wrap {
    width: 80%;
    margin: 0 auto;
}

.boardTable {
    width: 100%;
    border-collapse: collapse;
    margin-bottom: 20px;
}

.boardTable th, .boardTable td {
    padding: 10px;
    border: 1px solid #ddd;
    text-align: left;
}

.boardTable th {
    background-color: #f3f3f3;
    width: 20%;
}

.buttons {
    text-align: right;
}

.buttons button {
    margin-left: 10px;
    padding: 8px 16px;
    background-color: #eee;
    border: 1px solid #ccc;
    cursor: pointer;
}

</style>
</head>
<body>
<div class="wrap">
    <h2>게시글 상세 정보</h2>
    <table class="boardTable">
        <tr>
            <th>글번호</th>
            <td>${board.num}</td>
            <th>조회수</th>
            <td>${board.readnum}</td>
        </tr>
        <tr>
            <th>글쓴이</th>
            <td colspan="3">${board.userId}</td>
        </tr>
        <tr>
            <th>첨부파일</th>
            <td colspan="3">
                <c:if test="${not empty board.fileName}">
                    <a href="/board/download?fileName=${board.fileName}">${board.originFileName}</a>
                    (${board.fileSize} bytes)
                </c:if>
                <c:if test="${empty board.fileName}">
                    첨부파일 없음
                </c:if>
            </td>
        </tr>
        <tr>
            <th>제목</th>
            <td colspan="3">${board.title}</td>
        </tr>
        <tr>
            <th>글내용</th>
            <td colspan="3">${board.content}</td>
        </tr>
    </table>

    <div class="buttons">
        <!-- 수정 삭제는 구현해야함 -->
        <button onclick="location.href='/board/edit?num=${board.num}'">글수정</button>
        <button onclick="location.href='/board/delete?num=${board.num}'">글삭제</button>
        <button onclick="location.href='/board/list'">목록으로 돌아가기</button>
    </div>
</div>
</body>
</html>
