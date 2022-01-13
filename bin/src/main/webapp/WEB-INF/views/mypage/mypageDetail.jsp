<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<style type="text/css">	
	#info, #desc{
		width:700px;		
	}	
	#viewImg{
		float:left;
		padding:10px 30px 30px 30px;
		width:30%;				
	}
	#viewPd{
		float:left;
		padding:0 10px 30px 20px;		
	}
	#desc{
		clear:both;
		padding:10px 0 5px 0;		
	}
	
	.line2{
		border-bottom:solid silver 2px;
		padding:0 0 10px 7px;
	}
	.line
	{
		border-bottom:solid 1px silver;
	}
	.boldF{
		font-size:1.2em;
		font-weight:bold;

	}
	.center{
		text-align:center;
	}
	span{
		font-size:0.9em;
	}
	.sp1{
		width:50%;
		float:left;		
	}
	
	form{
		width:350px;
	}
	
		
	p{
		padding:8px;
		font-size:1.0em;
	}
	
	input[type="text"]{
		width:20%;
	}
</style>
<script type="text/javascript">
	$(function(){
		$('#viewImg a').click(function(){
			open("<c:url value='/mypage/mypageDetail'/>","img",
			"width=400,height=500,left=0,top=0,location=yes,resizable=yes");
		});
	});
</script>
<h2>프로필 상세 보기</h2>
<div id="info">
	<div id="viewImg">
		<!-- 상품 이미지 -->
		<p class="center">	
		</p>
		<p class="center">
		<a href="#">
		 	큰이미지 보기</a></p>
	</div>
	<div id="viewPd">
		<form name="frmPd">			
			<!-- 상품명 -->
			<p class="line2">
				<span class="boldF">
					${vo.Name }
				</span>
			</p>
			<p class="line"><span class="sp1">
			 이름</span>				
				<span><fmt:formatNumber value="${vo.name }" 
					pattern="#,###"/>원  </span>
			</p>
			<p class="line"><span class="sp1">
			 이메일</span>
				<span><fmt:formatNumber value="${vo.email }" 
					pattern="#,###"/>원</span>
			</p>
			<p class="line"><span class="sp1">
			 전화번호</span>
				<span>${vo.tel }</span>
			</p>
		
			<p class="line"><span class="sp1">
			주소</span>
				<label for="qty">
				<input type="text" name="qty" id="qty" value="1" ></label>
			</p>
			<p class="center">
				<input type="button" value="수정하기" >
				<input type="button" value="나가기">
			</p>
		</form>
	</div>
</div>
<div id="desc">
	<span style="font-size:12pt;font-weight:bold">
		상품상세정보</span>
	<br><br>
	<p>${vo.explains}</p>
	<p>${vo.description}</p>	
</div>

<%@ include file="../../mypageinc/mypageDetail.jsp"%>