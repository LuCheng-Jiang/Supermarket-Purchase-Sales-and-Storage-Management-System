window.onload= function(){
	function showTime(){
		var time=new Date().toLocaleString();
		document.getElementById("time").innerText=time;
		setTimeout(showTime,100);
	}
	showTime();
}