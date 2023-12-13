<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<c:set var="path" value="${pageContext.request.contextPath}"/>
<jsp:include page="/WEB-INF/views/common/header.jsp">
	<jsp:param name="title" value=""/>
</jsp:include>
<section id="content">

	<h3>ko : ${greetingKr }</h3>
	<h3>us : ${greetingUs }</h3>
	<h3>Kr : ${testKr}</h3>
	<h3>us : ${testUs}</h3>

</section>
<jsp:include page="/WEB-INF/views/common/footer.jsp"/>