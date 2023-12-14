<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<c:set var="path" value="${pageContext.request.contextPath}"/>
<jsp:include page="/WEB-INF/views/common/header.jsp">
	<jsp:param name="title" value="로그인 페이지"/>
</jsp:include>
<section id="content">
	<form action="${path }/loginend" method="post">
		<input type="text" name="userId"><br>
		<input type="password" name="pw"><br>
		<button class="btn btn-outline-primary">로그인</button>
	</form>

</section>
<jsp:include page="/WEB-INF/views/common/footer.jsp"/>