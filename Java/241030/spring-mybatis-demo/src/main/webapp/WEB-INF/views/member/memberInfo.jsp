<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Member Info</title>
    <style>
        /* 공통 레이아웃 */
        body {
            margin: 0;
            padding: 0;
            font-family: 'Noto Sans KR', Arial, sans-serif;
            background-color: #fff8f0; /* style.css의 배경 색상 */
        }

        .container {
            width: 70%;
            max-width: 800px;
            margin: 50px auto;
            padding: 2.5rem;
            background-color: #ffffff;
            border-radius: 15px;
            box-shadow: 0 6px 12px rgba(0, 0, 0, 0.1);
        }

        /* 테이블 스타일 */
        table {
            width: 100%;
            border-collapse: collapse;
            margin: 20px 0;
        }

        th, td {
            padding: 15px;
            text-align: left;
            border-bottom: 1px solid #ddd;
        }

        th {
            background-color: #f8d7da; /* style.css의 테이블 헤더 색상 */
            color: #333;
            font-weight: bold;
        }

        tr:hover {
            background-color: #f1f1f1; /* 행 호버 효과 */
        }

        tbody tr:nth-child(even) {
            background-color: #fafafa; /* 짝수 행 배경색 */
        }

        /* 상태 표시 색상 */
        span.mstate1 { color: #28a745; } /* 활동회원 */
        span.mstate0 { color: #757575; } /* 정지회원 */
        span.mstate-1 { color: #dc3545; } /* 탈퇴회원 */
        span.mstate9 { color: #388e3c; } /* 관리자 */

        /* 버튼 스타일 */
        button {
            padding: 12px 24px;
            margin: 10px;
            border: none;
            border-radius: 30px;
            font-size: 1rem;
            cursor: pointer;
            transition: background-color 0.3s, transform 0.3s;
            box-shadow: 0 5px 15px rgba(0, 0, 0, 0.2);
            color: white;
        }

        button[type="submit"] {
            background-color: #ff8a80; /* 인기 상품 버튼 색상 */
        }

        button[type="submit"]:hover {
            background-color: #ff6f61;
            transform: scale(1.1);
        }

        button[type="reset"] {
            background-color: #ff5252; /* 핑크-레드 */
        }

        button[type="reset"]:hover {
            background-color: #ff4081;
            transform: scale(1.1);
        }

        button[type="button"] {
            background-color: #2979ff; /* 선명한 파랑 */
        }

        button[type="button"]:hover {
            background-color: #2962ff;
            transform: scale(1.1);
        }

        /* 수정/삭제 버튼 스타일 */
        .edit-button, .delete-button {
            padding: 8px 15px;
            font-size: 0.9rem;
            border-radius: 5px;
            color: white;
            text-decoration: none;
            text-align: center;
            display: inline-block;
            margin: 5px 2px;
        }

        .edit-button {
            background-color: #ffab00;
        }

        .edit-button:hover {
            background-color: #ff9100;
        }

        .delete-button {
            background-color: #ff4081;
        }

        .delete-button:hover {
            background-color: #f50057;
        }

        /* 반응형 디자인 */
        @media (max-width: 600px) {
            .container {
                width: 90%;
                padding: 1rem;
            }

            input {
                font-size: 0.9rem;
            }

            button {
                width: 100%;
                margin: 5px 0;
            }
        }
    </style>
</head>
<body>
    <c:if test="${user == null}">
        <script>
            alert('해당 회원은 존재하지 않아요');
            history.back();
        </script>
    </c:if>

    <c:if test="${user != null}">
        <div class="container">
            <form action="memberUpdate" method="post">
                <table>
                    <tr>
                        <th colspan="2">
                            <h1 style="text-align: center;">Member Info</h1>
                        </th>
                    </tr>
                    <tr>
                        <th>회원번호</th>
                        <td><input type="text" name="idx" id="idx" value="${user.idx}" readonly></td>
                    </tr>
                    <tr>
                        <th>이름</th>
                        <td><input type="text" name="name" id="name" value="${user.name}" placeholder="Name"></td>
                    </tr>
                    <tr>
                        <th>아이디</th>
                        <td><input type="text" name="userId" id="userId" value="${user.userId}" placeholder="ID"></td>
                    </tr>
                    <tr>
                        <th>이메일</th>
                        <td><input type="email" name="email" id="email" value="${user.email}" placeholder="Email"></td>
                    </tr>
                    <tr>
                        <th>회원상태정보</th>
                        <td>
                            <span class="mstate${user.mstate}">${user.mstateStr}</span>
                            <br><br>
                            <label><input type="radio" name="mstate" value="1" <c:if test="${user.mstate == 1}">checked</c:if>> 활동회원</label>
                            <label><input type="radio" name="mstate" value="0" <c:if test="${user.mstate == 0}">checked</c:if>> 정지회원</label>
                            <label><input type="radio" name="mstate" value="-1" <c:if test="${user.mstate == -1}">checked</c:if>> 탈퇴회원</label>
                            <label><input type="radio" name="mstate" value="9" <c:if test="${user.mstate == 9}">checked</c:if>> 관리자</label>
                        </td>
                    </tr>
                    <tr>
                        <td colspan="2" style="text-align: center;">
                            <button type="submit">수정하기</button>
                            <button type="reset">다시쓰기</button>
                            <button type="button" onclick="location.href='memberList.do'">회원목록</button>
                        </td>
                    </tr>
                </table>
            </form>
        </div>
    </c:if>
</body>
</html>
