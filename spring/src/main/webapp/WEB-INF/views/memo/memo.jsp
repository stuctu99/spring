<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="springform" uri="http://www.springframework.org/tags/form" %>
<c:set var="path" value="${pageContext.request.contextPath}"/>
<jsp:include page="/WEB-INF/views/common/header.jsp">
	<jsp:param name="title" value="메모 리스트"/>
</jsp:include>
<style>
    div#memo-container{width:60%; margin:0 auto;}
    .error{
    	color:magenta;
    	font-weight:bolder;
    }
</style>
<section id="content">
    <div id="memo-container">
        <springform:form modelAttribute="memo" 
        action="${path}/memo/insertMemo.do " class="form-inline">
            <springform:input path="memo" type="text" class="form-control col-sm-6" name="memo" placeholder="메모"/>&nbsp;
            <springform:input path="password" type="password" class="form-control col-sm-2" name="password" maxlength="4" placeholder="비밀번호"/>&nbsp;
            <button class="btn btn-outline-success" type="submit" >저장</button><br>
            <springform:errors path="memo" cssClass="error"/> <br>
            <springform:errors path="password" cssClass="error"/> <br>
        </springform:form>
    </div>

메모 작성화면


 <br />
        <!-- 메모목록 -->
        <table class="table">
            <tr>
                <th scope="col">번호</th>
                <th scope="col">메모</th>
                <th scope="col">날짜</th>
                <th scope="col">삭제</th>
            </tr>
   <c:if test="${not empty memos}">
   		<c:forEach var="m" items="${memos }">
   			<tr>
   				<td scope="col">
   					<c:out value="${m.memoNo }"/>
   				</td>
   				<td scope="col">
   					<c:out value="${m.memo }"/>
   				</td>
   				<td scope="col">
   					<c:out value="${m.memoDate }"/>
   				</td>
   				<td scope="col">
   					<button class="btn btn-outline-danger">
   						삭제하기
   					</button>
   				</td>
   			</tr>
   		</c:forEach>
   </c:if>
        </table>
      
   <div id="pageBar">
   	${pageBar }
   </div>     
       
메모 리스트


</section>
<jsp:include page="/WEB-INF/views/common/footer.jsp"/>