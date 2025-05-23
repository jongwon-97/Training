<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>글수정</title>
        <!-- CKEditor5 -------------------------------------------- -->
            <link rel="stylesheet" href="https://cdn.ckeditor.com/ckeditor5/43.3.0/ckeditor5.css" />
        <!-- ----------------------------------------------------------- -->
         <script type="importmap">
                    {
                        "imports": {
                            "ckeditor5": "https://cdn.ckeditor.com/ckeditor5/43.3.0/ckeditor5.js",
                            "ckeditor5/": "https://cdn.ckeditor.com/ckeditor5/43.3.0/"
                        }
                    }
                </script>

                <script type="module">
                    import {
                        ClassicEditor,
                        Essentials,
                        Bold,
                        Italic,
                        Font,
                        Paragraph
                    } from 'ckeditor5';

                    ClassicEditor
                        .create( document.querySelector( '#content' ), {
                            plugins: [ Essentials, Bold, Italic, Font, Paragraph ],
                            toolbar: [
                                'undo', 'redo', '|', 'bold', 'italic', '|',
                                'fontSize', 'fontFamily', 'fontColor', 'fontBackgroundColor'
                            ]
                        } )
                        .then( /* ... */ )
                        .catch( /* ... */ );
                </script>

        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/boardWrite.css">
    </head>
    <body>
        <div class="boardWrap">
            <h1>KOTOKI 게시판 글 수정</h1>

            <!-- board/form

            파일업로드 할때 주의사항
                method ="post"로 지정
                enctype="multipart/form-data"

            post방식일 때 인코딩 타입

                - application/www-form-urlencoded(디폴트):파일데이터를 서버에
                    전송하지 못함.파일명(파라미터 값)만 전송함

                - multipart/form-data : 파일 데이터도 함께 전송함
                    여러 파트로 폼데이터를 구분(구분선)해서 보낸다
                    첨부된 파일 데이터 시작과 끝을 구분선으로 구분해준다.
            -->

            <form name="boardF" action="../user/write" method="post"
                enctype="multipart/form-data" >

                <!-- mode 값: 글쓰기(write), 글수정(edit) -->

                <input type="hidden" name="mode" value="edit">
                <input type="hidden" name="num" value="<c:out value='${board.num}'/>">

                <!-- ---------------------------------- -->
                <ul class="boardUl">
                    <li>제목</li>
                    <li>
                        <input type="text" name="title"
                        value="<c:out value='${board.title}'/>"
                        id="title" placeholder="Title">
                    </li>

                    <li>글쓴이</li>
                    <li>
                        <input type="text" name="userId" id="userId"
                        readonly placeholder="User ID" value="${loginUser.userId}">
                    </li>

                    <li>글내용</li>
                    <li>
                        <textarea rows="10" cols="50" name="content" id="content" placeholder="Content">
                        ${board.content}
                        </textarea>
                    </li>

                    <li>첨부파일</li>
                    <li>
                        <c:out value="${board.originFileName}" />
                        [<c:out value="${board.fileSize}"/> bytes]
                        <input type="hidden" name="old_FileName" value-"${board.fileName}">
                        <input type="file" name="mfileName">

                    </li>

                    <li>비밀번호</li>
                    <li>
                        <input type="password" name="passwd" id="passwd" placeholder="Password">
                    </li>
                </ul>
                <div class="divBtn">
                    <button>글수정</button>
                    <button type="reset">다시쓰기</button>
                </div>
            </form>
        </div>
    </body>
</html>
