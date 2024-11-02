<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>글 목록</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/boardList.css">
</head>
<body>
<div class="wrap">
    <h1>KOTOKI Board List</h1>
    <ul class="boardList">
        <!-- 헤더 부분 -->
        <li class="header">글 번호</li>
        <li class="header">글 제목</li>
        <li class="header">작성자</li>
        <li class="header">작성일</li>
        <li class="header">조회수</li>

        <!-- 게시글 목록 -->
        <c:if test="${boardList == null || empty boardList}">
            <li class="empty" colspan="5">데이터가 없습니다</li>
        </c:if>
        <c:if test="${boardList != null && not empty boardList}">
            <c:forEach var="board" items="${boardList}">
                <li class="board-number">
                <c:out value="${board.num}"/>
                </li>
                <li class="board-title">
                <a href="/board/view?num=<c:out value='${board.num}'/>"><c:out value="${board.title}"/></a>
                </li>
                <li class="board-userId">
                <c:out value="${board.userId}"/>
                <li class="board-wdate">
                <fmt:formatDate value="${board.wdate}" pattern="yy-MM-dd"/>
                </li>
                <li class="board-readnum">
                <c:out value="${board.readnum}"/>
                </li>
            </c:forEach>
        </c:if>
    </ul>
    <div class="divTotal">
        <span>총 게시글 수: ${totalCount}</span>
        <span>1 / 3 pages</span>
    </div>
</div>
</body>
</html>
