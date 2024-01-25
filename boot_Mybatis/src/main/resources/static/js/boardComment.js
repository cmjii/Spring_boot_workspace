console.log("comment in");
console.log(bno);

document.getElementById('cmtPostBtn').addEventListener('click',()=>{
	const cmtText = document.getElementById('cmtText').value;
	 if(cmtText==null || cmtText ==''){
		 alert("댓글을 입력해주세요.")
		 cmtText.focus();
		 return false;
	 }else{
		 let cmtData = {
			 bno:bno,
			 writer:document.getElementById('cmtWriter').innerText,
			 content:cmtText
		 };
		 console.log(cmtData);
	 }
	 commentToServer(cmtData).then(result=>{
		 if(result ==="1"){
			 alert("댓글 등록 완료");
			 cmtText='';
		 }
	 })
});

async function commentToServer(cmtData){
	try{
		const url = "/comment/post"
		const config ={
			method : "post",
			headers : {
			'content-type':'application/json; charset=UTF-8'
			},
			body: JSON.stringify(cmtData)
		};
		const resp = await fetch(url,config);
		const result = await resp.text();
		return result;
	}catch(error){
		console.log(error);
	}
}