<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="utf-8">
<%-- <link rel="stylesheet" type="text/css" href="<c:url value='/resources/css/mainstyle.css'/>" />
<link rel="stylesheet" type="text/css" href="<c:url value='/resources/css/clear.css'/>" />
<link rel="stylesheet" type="text/css" href="<c:url value='/resources/css/formLayout.css'/>" />
<link rel="stylesheet" type="text/css" href="<c:url value='/resources/css/mystyle.css'/>" /> --%>
<style type="text/css">
	.divForm .firstDiv	{
		border-top: 2px solid #e2e2e2/* #aacc00 */;
	}
</style>
<title>회원등록</title>
<script type="text/javascript" src="<c:url value='/resources/js/jquery-3.6.0.min.js'/>"></script>
<script type="text/javascript">
	$(function(){
		$('form[name=frmWrite]').submit(function(){
			$('.infobox').each(function(idx, item){
				if($(this).val().length<1){
					alert($(this).prev().html() + "을(를) 입력하세요");
					$(this).focus();
					event.preventDefault();
					return false;  //each 탈출
				}
			});
		});
		
		/* $('#btList').click(function(){
			location.href="<c:url value='/account/register.do'/>";	
		}); */
		System.out.println("경로경로"+request.getContextPath());
	});
</script>

</head>
<body>
<div class="divForm">
<form name="frmWrite" method="post" 
	action="<c:url value='/account/register.do'/>" >
 <fieldset>
	<legend>글쓰기</legend>
        <div class="firstDiv">
            <label for="email">이메일</label>
            <input type="email" id="email" name="email" class="infobox" />
        </div>
        <div>
            <label for="pwd">비밀번호</label>
            <input type="password" id="pwd" name="pwd" class="infobox" />
        </div>
        <!-- <div>
            <label for="salt">비밀번호</label>
            <input type="password" id="salt" name="salt" class="infobox" />
        </div> -->
        <div>
            <label for="name">이름</label>
            <input type="text" id="name" name="name" class="infobox" />
        </div>
        <div>
            <label for="address">주소</label>
            <input type="text" id="address" name="address" class="infobox" />
        </div>
        <div>
            <label for="tel">연락처</label>
            <input type="text" id="tel" name="tel" class="infobox" />
        </div>
        <div>
            <label for="cardNo">카드번호</label>
            <input type="text" id="cardNo" name="cardNo" class="infobox" />
        </div>
        <div class="center">
            <input type = "submit" value="등록"/>        
        </div>
    </fieldset>
</form>
</div>    

              
</body>
</html>