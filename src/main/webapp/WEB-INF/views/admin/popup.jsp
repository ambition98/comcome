<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="<c:url value='/resources/css/admin/popup.css'/>" />
<style type="text/css">

table {
    border: solid 1px white;
    height: 300px;
    margin-top: 50px;
}

p#title {
    color: white;
    text-align: center;
    font-size: 20px;
}

p#content {
    color: white;
    margin-top: 50px;
}

body {
    background-color: #2d4755;
}

element.style {
    margin: 0;
    padding: 0;
   
}

input[type="checkbox"] {
    background-color: #a9b5bf;
    /* color: #ed4755; */
}




element.style {
    font-size: 11px;
    color: #2d4755;
    text-align: right;
    background-color: #d9e5eb;
    width: 10%;
    font-weight: bold;
}



a {
    text-decoration: none;
    color: #2d4755;
    font-size: 10px;
}
</style>
<title>팝업 자동으로 뜨기</title>
<script language="JavaScript">
	function getCookie(name) {
		var nameOfCookie = name + "=";
		var x = 0
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
	function openCookieWin() {
		if (getCookie("ncook") != "done") {
			noticeWindow = window.open("new.html", "",
					"width=400, height=330, top=200,left=100");
			noticeWindow.opener = self;
		}
	}
	
	
	function setCookie(name,value,expiredays) {
	var todayDate = new Date();
	todayDate.setDate(todayDate.getDate() + expiredays);
	document.cookie = name + "=" + escape( value ) + "; path=/; expires=" + todayDate.toGMTString() + ";"
	}

	function closeWin() {
	if(document.checkClose.ncook.checked == true) {
	setCookie("ncook", "done" ,7);
	}
	self.close();
	}
	
</script>


</head>
<body style="margin:0;padding:0;background-color:#000000;">
<center> <form name="checkClose">
<table cellpadding="0" cellspacing="0">
  <tr>
    <Td colspan="2">
    <!-- 여기 바꾸기 -->
     <div id="tandc">
      <c:if test="${!empty vo }">
      	<p id="title">${vo.title}</p>
      	<p id="content">${vo.content}</p>	
      </c:if>
     </div>
    </td>
   </tr>
  <tr>
   <Td style="font-size:11px;color:#ffffff; background-color:#000;text-align:center;width:90%;">
      <input type="checkbox" name="ncook">다음부터 이 창을 띄우지 않음
  </td>
  <td style="font-size:11px;color:#ffffff;text-align:right;background-color:#000;width:10%;font-weight:bold;">
       <a href="#" onClick="closeWin()">닫기</a>&nbsp;&nbsp;
   </td>
  </tr> </form>
</table>
</body>
</html>