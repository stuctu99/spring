<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.Arrays" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<c:set var="path" value="${pageContext.request.contextPath}"/>

<jsp:include page="/WEB-INF/views/common/header.jsp">
	<jsp:param name="title" value="Demo 수정"/>
</jsp:include>
<style>
	table#tbl-dev{
		margin:0 auto;
		width:50%;
	}
</style>
<section id="content">
	<form action="${path }/demo/updateDemoend.do" method="post">
		<input type="hidden" name="devNo" value="${demo.devNo }"/>
		<table class="table" id="tbl-dev">
			<tr>
				<th scope="col">이름</th>
				<td>
					<input type="text" value="${demo.devName}" name="devName"/>
				</td>
			<tr>
			<tr>
				<th>나이</th>
				<td>
					<input type="number" value="${demo.devAge}" name="devAge"/>
				 </td>
			</tr>
			<tr>
				<th>이메일</th>
				<td>
					<input type="text" value="${demo.devEmail}" name="devEmail"/>
				 </td>
			</tr>
			<tr>
				<th>성별</th>
				<td>
					<input type="radio" name="devGender" value="M" ${demo.devGender eq "M"?"checked":""}/>남
					<input type="radio" name="devGender" value="F" ${demo.devGender eq "F"?"checked":""}/>여
				 </td>
			</tr>
			<tr>
				<th>개발가능언어</th>
				<td>
					<label><input type="checkbox" name="devLang" value="Java" ${langList.stream().anyMatch(e->e.trim().equals("Java")).get()?"checked":"" } />Java</label>
					<label><input type="checkbox" name="devLang" value="C"  ${langList.stream().anyMatch(e->e.trim().equals("C")).get()?"checked":"" }/>C</label>
					<label><input type="checkbox" name="devLang" value="Javascript"  ${langList.stream().anyMatch(e->e.trim().equals("Javascript")).get()?"checked":"" }/>JavaScript</label>
				</td>
			</tr>
			<tr>
				<td colspan="2" style="text-align:center">
					<button class="btn btn-outline-success">수정</button>
				</td>
			</tr>
		</table>
	</form>
</section>
<jsp:include page="/WEB-INF/views/common/footer.jsp"/>