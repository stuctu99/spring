<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="path" value="${pageContext.request.contextPath }"/>
<jsp:include page="/WEB-INF/views/common/header.jsp">
	<jsp:param value="MainPage" name="title"/> 
</jsp:include>
<section id="content">
	<h2>Hello, Spring</h2>
	<h3>ajax통신하기</h3>
	<button class="btn btn-outline-primary" onclick="basicAjax();">
		기본 ajax요청
	</button><br>
	<button class="btn btn-outline-primary" onclick="basicAjaxPage();">
		ajax요청 page
	</button><br>
	<button class="btn btn-outline-success" onclick="converterAjax();">
		converter ajax(@ResponseBody)
	</button><br>
	<button class="btn btn-outline-dark" onclick="searchBoard();">
		BoardList
	</button><br>
	<button class="btn btn-outline-dark" onclick="insertMember();">
		회원 가입
	</button><br>
	<div id="data-container"></div>
	
	<button class="btn btn-outline-success" onclick="exceldownload();">
		demoListExcel 다운로드
	</button>
	
</section>
<script>

	const exceldownload=()=>{
		location.assign("${path}/demo/exceldownload");
		
	}

	const insertMember=()=>{
		fetch("${path}/member",{
			method:"POST",
			headers:{
				"Content-Type":"application/json"
			},body:JSON.stringify({userId:"user07",password:"1234",name:"이보연",gender:"F",
					age:28, email:"bo@bobo",phone:"01051770364",address:"부천시",
					hobby:["영화보기","여행가기","바다가기"]})
		}).then(response=>{
			if(response.status!=200) throw new Error(response.status);
			return response.json();
		}).then(result=>{
			console.log(result);
		}).catch(e=>{
			alert(e);
			console.log(e);
		})
		
	}
	
	const searchBoard=()=>{
		fetch("${path}/boards")
		.then(response=>{
			if(!response.ok) throw new Error(response.status);
			return response.json();
		}).then(data=>{
			console.log(data);
		}).catch(e=>{
			alert("요청 실패! 에러!");
			console.log(e);
		})
	}
	
	const converterAjax=()=>{
		fetch("${path}/ajax/converterajax")
		.then(response=>response.json())
		.then(data=>{
			const $ul=document.createElement("ul");
			data.forEach(d=>{
				const $li = document.createElement("li");
				$li.innerText=d;
				$ul.appendChild($li);
				
				
			});
			document.querySelector("#data-container").innerHTML="";
			document.querySelector("#data-container").appendChild($ul);
		});
		
	}
	
	const basicAjaxPage=()=>{
		
		fetch("${path}/ajax/basicajaxpage")
		.then(response=>response.text())
		.then(data=>{
			console.log(data);
			document.querySelector("#data-container").innerHTML=data;
		})
	}

	const basicAjax=()=>{
		//fetch방식으로 ajax요청 처리하기
		//js fetch("url주소"[,{요청정보설정(header,body,method방식)}])함수
		//fetch().then(response=>{응답 상태에 따른 분기처리, return 데이터}).then(data=>{})
		//뒤쪽 then이 success와 같은거!
		fetch("${path}/ajax/basicajax")//get방식
		.then(response=>{
			console.log(response); 
			console.log(response.ok);
			console.log(response.status);
			console.log(response.body);
			return response.json()}) //return 값이 뒤쪽 then의 data로 들어감
					//response.text()
			.then(data=>{
			console.log(data)
			
		});
	}
</script>
<jsp:include page="/WEB-INF/views/common/footer.jsp"/>