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
    div#board-container{width:400px; margin:0 auto; text-align:center;}
    div#board-container input,div#board-container button{margin-bottom:15px;}
    div#board-container label.custom-file-label{text-align:left;}
    </style>
<section id="content">

 <div id="board-container">
        <input type="text" class="form-control" value="${board.boardTitle }" name="boardTitle" id="boardTitle" readonly  required>
        <input type="text" class="form-control" name="boardWriter" value="${board.writer.userId }" readonly required>
<!-- 		첨부파일 -->
		<c:if test="${board.files.size()>0 }">
			<c:forEach var="file" items="${board.files }">
	            <button type="button" 
	                    class="btn btn-outline-success btn-block"
	                    onclick="">
	                    ${file.originalFilename }
	            </button>
            </c:forEach>
        </c:if>
        
        
        <textarea class="form-control" name="boardContent" readonly required>${board.boardContent }</textarea>
    </div>



</section>
<jsp:include page="/WEB-INF/views/common/footer.jsp"/>