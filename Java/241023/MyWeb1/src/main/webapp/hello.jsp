<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.Date,java.text.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="css/index.css">
</head>
<body>
	<h1>Hello JSP</h1>
	<h2>JSP - JavaServerPage의 약자,동적인 처리를 담당합니다.</h2>
	<hr>
	<p>
		<%
		//자바 코드는 scriptlet태그 안에서 작성
		for (int i = 0; i < 3; i++) {
		%>
		<img src="image/an.jpg" alt="톰캣 이미지 입니다.">
		<%
		}

		Date today = new Date();
		//out 내장객체; 브라우저에 출력하고자 할때 사용.
		out.println("<h2 class='today'>" + today + "</h2>");

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
		String todayStr = sdf.format(today);
		%>
	
	<h2 class='today2'><%=todayStr%></h2>
<!-- html 주석 -->
<%-- jsp 주석 <%=변수%>출력식,변수값을 출력함 out.println(변수);와 동일함 --%>
	</p>
</body>
</html>