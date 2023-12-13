<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<c:set var="path" value="${pageContext.request.contextPath}"/>
<jsp:include page="/WEB-INF/views/common/header.jsp">
	<jsp:param name="title" value=""/>
</jsp:include>
<style>
/*   .error{ */
/*     	color:magenta; */
/*     	font-weight:bolder; */
/*     } */
.boardheader{
	display:flex;
	justify-content:space-between;
	align-items:center;
}
</style>

<section id="board-container" class="container">
       <div class="boardheader">
	        <p>총 ${totalContents }건의 게시물이 있습니다.</p>
	        <div>
	          <button class="btn btn-outline-warning" onclick="location.assign('${path }/board/insertBoard.do')">
	        	 글쓰기
	          </button>
	        </div>
       </div>
        <table id="tbl-board" class="table table-striped table-hover">
            <tr>
                <th>번호</th>
                <th>제목</th>
                <th>작성자</th>
                <th>작성일</th>
                <th>첨부파일</th>
                <th>조회수</th>
            </tr>
            <c:if test="${not empty boards }">
	            <c:forEach var="b" items="${boards }">
	            	<tr>
	            		<td><c:out value="${b.boardNo}"/></td>
	            		<td><a href="${path}/board/boardView.do?boardNo=${b.boardNo}"><c:out value="${b.boardTitle}"/></a></td>
	            		<td><c:out value="${b.writer.userId}"/></td>
	            		<td><fmt:formatDate value="${b.boardDate}" pattern="yyyy년 MM월 dd일 E"/></td>
	            		<td>
	            			<c:if test="${b.files.size()>0 }">
	            				<img src="${path }/resources/images/file.png" width="20px">
	            				<span>${b.files.size() }</span>
	            			</c:if>
	            		</td>
	            		<td><c:out value="${b.boardReadCount}"/></td>
					</tr>
				</c:forEach>
			</c:if>      
            	
        </table> 
        
        <div>
        	${pageBar }
        </div>
</section>
<jsp:include page="/WEB-INF/views/common/footer.jsp"/>