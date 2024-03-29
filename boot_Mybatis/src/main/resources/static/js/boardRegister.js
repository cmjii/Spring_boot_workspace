console.log("in");

document.getElementById('trigger').addEventListener('click',()=>{
	document.getElementById('files').click();
});

const regExp = new RegExp("\.(exe|sh|bat|js|dll|msi|jar)$"); //실행파일 막기
const regExpImg = new RegExp("\.(jpg|jpeg|png|bmp|gif)$");
const maxSize = 1024*1024*20;

function fileValidation(fileName, fileSize){
	if(regExp.test(fileName)){
		return 0;
	}else if(fileSize > maxSize){
		return 0;
	}else{
		return 1;
	}
};

document.addEventListener('change',(e)=>{
	if(e.target.id=='files'){
		const fileObj = document.getElementById('files').files; //multipe 배열로 들어옴
		console.log(fileObj);
		document.getElementById('btn').disabled=false;
		const div = document.getElementById('fileZone');
		//이전에 업로드 했던 파일들이 있다면 제거
		div.innerHTML="";
		let ul =`<ul class="list-group list-group-flush">`;
		
		let isok =1; //여러 파일에 대한 값 확인에 대한 값
		for(let file of fileObj){
			let validResult = fileValidation(file.name, file.size);
			isok *= validResult;//하나씩 모든 파일에 대한 확인
			ul+=`<li class="list-group-itme">`;
			ul+=`<div class="ms-2 me-auto">`;
			ul+=`${validResult ? '<div class="fw-bold">업로드 가능' : '<div class="fw-bold text-danger">업로드 불가' }</div>`;
			ul+=`${file.name}</div>`;
   		    ul += `<span class="badge text-bg-${validResult ? 'success' : 'danger'}">${file.size}Byte</span>`;
			ul+=`</li>`;
		}
		
		ul+= `</ul>`;
		div.innerHTML=ul;
		
		if(isok ==0){
			//파일 중 valide 결과에 맞지 않는 값이 있다는 뜻
			document.getElementById('btn').disabled=true;
		}
	}
})