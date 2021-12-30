<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../mypageinc/mypagetop.jsp" %>
<%-- <script type="text/javascript" src="<c:url value='/resources/js/member.js'/>"></script> --%>
<script type="text/javascript">
	$(function(){
		$('#wr_submit').click(function(){
			if($('#pwd').val().length<1){
				alert('비밀번호를 입력하세요');
				$('#pwd').focus();
				event.preventDefault();
			}else if($('#pwd').val()!=$('#pwd2').val()){
				alert('비밀번호가 일치하지 않습니다!');
				$('#pwd2').focus();
				event.preventDefault();
			}else if(!validate_phone($('#hp2').val()) || 
					!validate_phone($('#hp3').val()) ){
				alert('전화번호는 숫자만 가능합니다.');
				$('#hp2').focus();
				event.preventDefault();			
			}
			
		});
		
	});
	
</script>

<style type="text/css">
	.width_80{
		width:80px;
	}
	.width_350{
		width:350px;
	}	
</style>
<article>
<div class="divForm">
<form name="frm1" method="post" action="<c:url value='/mypage/mypageEdit'/>">
<fieldset>
	<legend>회원 정보 수정</legend>
    <div>        
        <label for="name">성명</label>
        <span>${vo.name}</span>
    </div>
    <div>
        <label for="email">회원ID</label>
        <span>${sessionScope.email}</span>
    </div>
    <div>
        <label for="pwd">비밀번호</label>
        <input type="Password" name="pwd" id="pwd">
    </div>
    <div>
        <label for="pwd2">비밀번호 확인</label>
        <input type="Password" name="pwd2" id="pwd2">
    </div>   
    
    <div class="center">
         <input type="submit" id="wr_submit" value="수정">
    </div>
</fieldset>
        
</form>
</div>
</article>

<%@ include file="../mypageinc/bottom.jsp"%>
