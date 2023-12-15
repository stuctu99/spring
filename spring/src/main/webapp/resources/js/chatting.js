const server = new WebSocket("ws://localhost:8080/spring/chatting");

server.onopen =(response)=>{
		const msg = new Message("open",loginId);
		server.send(msg.convert());		
	}
	
	server.onmessage=(response)=>{
		const receiveMsg = Message.deconvert(response.data);
		
		switch(receiveMsg.type){
			case "open" : alertMessage(receiveMsg); break; 
			case "msg" : messagePrint(receiveMsg); break;
			case "attend" : addAttend(receiveMsg); break;
			
		}

	}
	
	const addAttend=(msg)=>{
		const attendPerson=Json.parse(msg.msg);
		const $attendContainer = document.querySelector("#attendContainer");
		$attendContainer.innerHTML="";
			const $ul=document.createElement("ul");
			$ul.classList.add("listcontainer");
			attendPerson.forEach(p=>{
				const $li = document.createElement("li");
				$li.innerText=p;
				$li.classList.add("listfont");
				$ul.appendChild($li);
		});
		$attendContainer.appendChild($ul);
	}
	
	const messagePrint=(msg)=>{
		//메세지 컨테이너 생성
		const $div = document.createElement("div");
		
		const $profile = document.createElement("img");
	/*	$profile.setAttribute("src",`http://${location.host}/spring/resources/upload/board/file.png`);*/
		$profile.setAttribute("src","https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRHhugmd92DQcTimktHIZqjN-SB9iu4HkAyoA&usqp=CAU");
		$profile.style.width="50px";
		$profile.style.height="50px";
		$profile.style.borderRadius="100px";
		$div.appendChild($profile);
		
		
		
		//보낸 사람 태그 생성
		const $sender = document.createElement("sup");
		$sender.innerText= msg.sender;
		//메세지 출력 태그 생성
		const $content = document.createElement("span");
		$content.innerText = msg.msg;
		//메세지 컨테이너에 보낸 사람, 메세지 추가
		$div.appendChild($sender);
		$div.appendChild($content);
		//메세지 컨테이너 디자인 추가(display flex)
		$div.classList.add("msgcontainer");
		 
		if(msg.sender==loginId){
			//오른쪽 정렬
			$div.classList.add("right")	
		}else{
			//왼쪽 정렬
			$div.classList.add("left");
		}
		document.querySelector("#chattingcontent").appendChild($div);
		 
	}

	const sendMessage=()=>{
		const msg = document.querySelector("#msg").value;
		server.send(new Message("msg",loginId,"",msg,"").convert());
	}
	
	const alertMessage=(msg)=>{
		const container=$("<div>").addClass("alertContainer");
		const content=$("<h4>").text(`${msg.sender} 님이 접속하셨습니다.`);
		container.append(content);
		$("#chattingcontent").append(container);
		
		
	}
	
	window.onload=()=>{
		document.getElementById("msg").addEventListener("keyup",e=>{
			if(e.key=='Enter'){
				document.getElementById("sendBtn").click();
			}
		})
	}
	
	
	class Message{
		constructor(type="", sender="", receiver="", msg="", room=""){
			this.type=type;
			this.sender=sender;
			this.receiver=receiver;
			this.msg=msg;
			this.room=room;
		}
		convert(){
			return JSON.stringify(this);
		}
		static deconvert(data){
			return JSON.parse(data);
		}
	}