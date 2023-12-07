<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<c:set var="path" value="${pageContext.request.contextPath}"/>
<jsp:include page="/WEB-INF/views/common/header.jsp">
	<jsp:param name="title" value="Demotest"/>
</jsp:include>

<style>
div#demo-container{
		width:50%;
		padding:15px;
		margin:0 auto;
		border:1px solid lightgray;
		border-radius:10px;
	}
</style>
<section id="content">

<div id="demo-container">
		<h2 style="text-align:center">Controller 파라미터 테스트</h2>
		<form id="devFrm" method="post">
			<div class="form-group row">
			<label for="devName" class="col-sm-2 col-form-label">이름</label>
				<div class="col-sm-10">
					<input type="text" class="form-control" id="devName" name="devName">
				</div>
			</div>
			<div class="form-group row">
			<label for="devAge" class="col-sm-2 col-form-label">나이</label>
			<div class="col-sm-10">
				<input type="number" class="form-control" id="devAge" name="devAge">
			</div>
			</div>
			<div class="form-group row">
			<label for="devEmail" class="col-sm-2 col-form-label">이메일</label>
				<div class="col-sm-10">
					<input type="email" class="form-control" id="devEmail" name="devEmail">
				</div>
			</div>
			<div class="form-group row">
				<label class="col-sm-2 col-form-label">성별</label>
				<div class="col-sm-10">
					<div class="form-check form-check-inline">
					<input class="form-check-input" type="radio" name="devGender" id="devGender0" value="M">
					<label class="form-check-label" for="devGender0">남</label>
					</div>
					<div class="form-check form-check-inline">
					<input class="form-check-input" type="radio" name="devGender" id="devGender1" value="F">
					<label class="form-check-label" for="devGender1">여</label>
					</div>
				</div>
			</div>
			
			<div class="form-group row">
			<label for="birthDay" class="col-sm-2 col-form-label">생년월일</label>
				<div class="col-sm-10">
					<input type="date" class="form-control" id="birthDay" name="birthDay">
				</div>
			</div>
			
			
			<div class="form-group row">
				<label class="col-sm-2 col-form-label">개발언어</label>
				<div class="col-sm-10">
					<div class="form-check form-check-inline">
					<input class="form-check-input" type="checkbox" name="devLang" id="devLang0" value="Java">
					<label class="form-check-label" for="devLang0">Java</label>
					</div>
					<div class="form-check form-check-inline">
					<input class="form-check-input" type="checkbox" name="devLang" id="devLang1" value="C">
					<label class="form-check-label" for="devLang1">C</label>
					</div>
						<div class="form-check form-check-inline">
							<input class="form-check-input" type="checkbox" name="devLang" id="devLang2" value="Javascript">
							<label class="form-check-label" for="devLang2">Javascript</label>
						</div>
				</div>
				
				<div class="form-group row">
					<label for="zipcode" class="col-sm-2 col-form-label">zipCode</label>
					<div class="col-sm-10">
						<input type="text" class="form-control" id="devName" name="zipCode">
					</div>
				</div>
				<div class="form-group row">
					<label for="city" class="col-sm-2 col-form-label">시,도</label>
					<div class="col-sm-10">
						<input type="text" class="form-control" id="devName" name="city">
					</div>
				</div>
				<div class="form-group row">
					<label for="detail" class="col-sm-2 col-form-label">상세주소</label>
					<div class="col-sm-10">
						<input type="text" class="form-control" id="devName" name="detail">
					</div>
				</div>
			</div>
			<div class="form-group row">
				<div class="col-sm-12">
					<button type="button" class="btn btn-outline-primary col-sm-12"
						onclick="requestTest('/demo1.do')">
						서블릿처럼 이용하기
					</button>
				</div>
				
				<div class="col-sm-12">
					<button type="button" class="btn btn-outline-primary col-sm-12"
						onclick="requestTest('/demo2.do')">
						1:1 매칭하기
					</button>
				</div>
				
				<div class="col-sm-12">
					<button type="button" class="btn btn-outline-primary col-sm-12"
						onclick="requestTest('/demo3.do')">
						requestParam이용하기
					</button>
				</div>
				
				<div class="col-sm-12">
					<button type="button" class="btn btn-outline-primary col-sm-12"
						onclick="requestTest('/demo4.do')">
						command 이용하기
					</button>
				</div>
				<div class="col-sm-12">
					<button type="button" class="btn btn-outline-primary col-sm-12"
						onclick="requestTest('/demo5.do')">
						Map 이용하기
					</button>
				</div>
					
				<div class="col-sm-12">
					<button type="button" class="btn btn-outline-primary col-sm-12"
						onclick="requestTest('/demo6.do')">
						추가 데이터 가져오기
					</button>
				</div>
				
				<div class="col-sm-12">
					<button type="button" class="btn btn-outline-primary col-sm-12"
						onclick="requestTest('/demo7.do')">
						ModelAndView 이용하기
					</button>
				</div>
				<div class="col-sm-12">
					<button type="button" class="btn btn-outline-primary col-sm-12"
						onclick="requestTest('insertDemo.do')">
						demo 저장하기
					</button>
				</div>
				
			
		</form>
	</div>


</section>


<script>

	const requestTest=(url)=>{
		const form = document.querySelector("#devFrm");
// 		form.method="GET";
		form.action="${path}/demo/"+url;
		form.submit();
		
	}

</script>

<jsp:include page="/WEB-INF/views/common/footer.jsp"/>