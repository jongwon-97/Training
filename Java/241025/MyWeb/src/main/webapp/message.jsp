<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%-- EL표현식: ${변수}
  request에 저장한 키값을 출력=>${requestScope.키값}
  session저장한 키값 출력=> ${sessionScope.키값}
  --%>
<script>
	alert('${msg}');
	location.href='${loc}';
</script>
    