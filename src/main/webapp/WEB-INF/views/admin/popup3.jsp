<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Nanum+Gothic+Coding&display=swap" rel="stylesheet">
<link rel="stylesheet" href="/script/style.css" type="text/css?ver=2">
<link rel="stylesheet" type="text/css" href="<c:url value='/resources/css/admin/popup3.css?ver=2'/>" />  
<title>레이어팝업</title>
<style type="text/css">
@import
	url('https://fonts.googleapis.com/css2?family=Nanum+Gothic+Coding&display=swap')
	;

body {
	font-family: 'Nanum Gothic Coding', monospace;
}

div#layerPopup0 {
	background: #f6f1ee;
	width: 250px;
	font-size: 15px;
}

p#title {
	padding-left: 10px;
	padding-bottom: 14px;
	/* padding: 10px; */
	font-size: medium;
	font-weight: bold;
	border-bottom: 1px solid #efa885;
	color: #5583a6;
}

p#content {
	padding-left: 10px;
	line-height: 36px;
	color: #5583a6;
	font-size: 14px;
}

div#akrvks {
    position: absolute;
    bottom: 5px;
    right: 0;
    padding-bottom: 4px;
}
div#ekerl {
	background-color: #fbdcce;
	padding: 7px;
	font-size: 13px;
	border-radius: 10px;
}
</style>
<head>
<Script language="javascript">
 function getCookie( name ){
  var nameOfCookie = name +
	"=";
		var x = 0;
		while (x <= document.cookie.length) {
			var y = (x + nameOfCookie.length);
			if (document.cookie.substring(x, y) == nameOfCookie) {
				if ((endOfCookie = document.cookie.indexOf(";", y)) == -1)
					endOfCookie = document.cookie.length;
				return unescape(document.cookie.substring(y, endOfCookie));
			}

			x = document.cookie.indexOf(" ", x) + 1;

			if (x == 0)
				break;
		}
		return "";
	}

	function setCookie(name, value, expiredays) {
		var todayDate = new Date();
		todayDate.setDate(todayDate.getDate() + expiredays);
		document.cookie = name + "=" + escape(value) + "; path=/; expires="
				+ todayDate.toGMTString() + ";"
	}

	function closeWin(get_Key) {
		get_Pop = eval("document.all.Popup" + get_Key);
		get_Pop_id = "Popup" + get_Key;

		if (get_Pop.checked) {
			setCookie(get_Pop_id, "done", 1);
		}

		get_layerPopup = eval("document.all.layerPopup" + get_Key);
		get_layerPopup.style.display = 'none';
	}
</Script>
 
<script language="JavaScript1.2">
var x =0
var y=0
drag = 0
move = 0
window.document.onmousemove = mouseMove
window.document.onmousedown = mouseDown
window.document.onmouseup = mouseUp
window.document.ondragstart = mouseStop
function mouseUp() {
move = 0
}
function mouseDown() {
if (drag) {
 //alert(parseInt(dragObj.style.left));
clickleft = window.event.clientX - parseInt(dragObj.style.left);
clicktop =window.event.clientY - parseInt(dragObj.style.top);
dragObj.style.zIndex += 1
move = 1
}
 
}
function mouseMove() {
if (move) {
dragObj.style.left = window.event.x - clickleft;
dragObj.style.top = window.event.y - clicktop;
}
 
}
function mouseStop() {
window.event.returnValue = false
}
function Show(divid)
{
divid.filters.blendTrans.apply();
divid.style.visibility = "visible";
divid.filters.blendTrans.play();
}
function Hide(divid) {
divid.filters.blendTrans.apply();
divid.style.visibility = "hidden";
divid.filters.blendTrans.play();
}
</script>
<!--//팝업-->
</head>
 
 
 
<body>
<!-- 레이어팝업1 시작 -->
<div id="layerPopup0" style="padding-top: 20px; top:328px; left:90%;margin:0 0 0 -245px; position:absolute ; display:none; z-index:999; border-radius: 10px;">
<div>
 <!-- ## Contents Start(++)--------------------------------------------->
  <div width="403px"  border="0" cellspacing="0" cellpadding="0"> 
   <c:if test="${!empty vo }">
	      	<p id="title">${title}</p>
	      	<p id="content">${content}</p>	
 </c:if>
 </div>
 
 <!-- ## Contents End(++)--------------------------------------------->
</div>
 <div class="link_w"  id="ekerl">
 <input type="checkbox" name="Popup0" id="Popup0"  style="cursor:hand">&nbsp;<span style="color:#fff;">하루동안 열지 않음</span>
 <div class="link_w" id="akrvks" ><span onClick="javascript:closeWin('0');" style="cursor:pointer; text-align:right; color:#fff;"><b>Close &nbsp;</b></span></div>
 </div>
</div>
 
<script>
 if ( getCookie( "Popup0" ) != "done" ) {
  document.getElementById("layerPopup0").style.display = "block";
 }
</script>
<!-- 레이어팝업1 끝 -->
 </body>
</html>