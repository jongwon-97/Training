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
        <li class="bheader">글 번호</li>
        <li class="bheader">글 제목</li>
        <li class="bheader">작성자</li>
        <li class="bheader">작성일</li>
        <li class="bheader">조회수</li>

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

                <c:if test="${board.newImg < 1}">
                    <span class="new">New</span>
                </c:if>

                 <c:if test="${board.fileName != null}">
                    <img src="/images/attatch.png" style="width:16px">
                 </c:if>

                 <a href="/board/view?num=<c:out value='${board.num}'/>">
                    <c:out value="${board.title}"/>
                 </a>

                </li>

                <li class="board-userId">
                    <c:out value="${board.userId}"/>
                </li>

                <li class="board-wdate">
                    <fmt:formatDate value="${board.wdate}" pattern="yy-MM-dd"/>
                </li>

                <li class="board-readnum">
                    <c:out value="${board.readnum}"/>

                </li>
            </c:forEach>
        </c:if>
    </ul>
    <div class="pagination-container">
        <div class="divTotal">
            <span>총 게시글 수: ${totalCount}</span>&nbsp;&nbsp;&nbsp;&nbsp;
            <span>${paging.pageNum} page / ${paging.pageCount} pages</span>
        </div>
        <div class="pagination">
            <c:forEach var="i" begin="1" end="${paging.pageCount}">
                <a href="list?pageNum=${i}"><c:out value="${i}"/></a>
            </c:forEach>
        </div>
    </div>
</div>
</body>
</html>
