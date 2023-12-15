<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<c:set var="path" value="${pageContext.request.contextPath}"/>
<jsp:include page="/WEB-INF/views/common/header.jsp">
	<jsp:param name="title" value="에러 페이지"/>
</jsp:include>
<section id="content">
	<img src="https://img.freepik.com/premium-vector/simple-vector-prohibition-sign-no-access-for-pedestrian-at-gray-background_9834-1404.jpg"
		width="200px">
	<h3 style="color:red">서비스를 이용할 권한이 없습니다 :( </h3>
</section>
<jsp:include page="/WEB-INF/views/common/footer.jsp"/>