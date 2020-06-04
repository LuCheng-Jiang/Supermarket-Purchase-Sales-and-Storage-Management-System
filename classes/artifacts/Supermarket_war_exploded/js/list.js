window.onload=function(){
	function change(){
		if(document.getElementById("flag").innerText=='false'){//取得的是字符串
			document.getElementById("page").innerHTML=null;
		}
	}
	change();
}