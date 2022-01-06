<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>보낸쪽지함 글 삭제</title>
<style type="text/css">
	body{
		padding:5px;
		margin:5px;
	 }
	.divForm{
		width:650px;
		border:1px solid #ddd;		
	}
	/* .divForm form{
		width:650px;
	} */
	.divForm div	{
		/* clear: both; */
		border:none;
		padding: 7px 0;
		margin: 0;
		overflow: auto;
	}	
	.sp{
		font-size:0.9em;
		color:#0056AC;			
	}
	.divForm fieldset	{
		border:0;
	}
</style>

<script type="text/javascript">
	$(function(){
		$('form[name=frmDelete]').submit(function(){
			if($('#pwd').val().length<1){
				alert('비밀번호를 입력하세요');
				$('#pwd').focus();
				event.preventDefault();
			}else{
				if(!confirm('삭제하시겠습니까?')){
					event.preventDefault();
				}
			}
		});
	});	
</script>
</head>
<body>
<div class="divForm">
<form name="frmDelete" method="post" 
	action="<c:url value='/messagebox/messageboxdelete.do'/>" >
	<input type="hidden" name="no" value="${param.messageno}">
	
		<fieldset>
		<legend>글 삭제</legend>
	        <div>           
	        	<span class="sp">${param.messageno}번 글을 삭제하시겠습니까?</span>                        
	        </div>
	        <div>           
	            <label for="pwd">비밀번호</label>
	            <input type="password" id="pwd" name="pwd" />   
	        </div>
	        <div class="center">
	            <input type ="submit"  value="삭제" />
	            <input type = "Button" value="글목록" 
                	OnClick="location.href='<c:url value="/messagebox/messageboxlist.do"/>'" />
	        </div>
	    </fieldset>
    </form>
</div>

</body>
</html>