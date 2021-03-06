<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="<c:url value='/resources/css/account/sb-admin-2.min.css'/>" />
<link rel="stylesheet" type="text/css" href="<c:url value='/resources/css/account/all.min.css'/>" />

<link href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
	rel="stylesheet">
<style type="text/css">
	body{	margin:5px;padding:5px;}	
	caption{visibility:hidden;	}
	p{
		font-size: 1.0em;
	}
	.blue{
		color:#006AD5;
	}
	#divPage{
		text-align: center;
	}
	
	.divBase {
    margin: 0;
    background-color: white;
    position: absolute;
    width: 80%;
    height: 40%;
    left: 10%;
    top: 10%;
    /* margin: 0; */
    /* padding: 0; */
    text-align: center;
	box-shadow: 0 0 3rem rgb(0 0 0 / 18%) !important;
    border-radius: 0.35rem;
}

input#dong {
    border-radius: 10rem;
    width: 60%;
    margin: 0 0 1rem 7%;
    float: left;
    border: 1px solid #d1d3e2;
    color: #6e707e;
    margin-bottom: 0;
    margin-top: 5px;
}

input#submit {
	border-radius: 10rem;
	float: right;margin: 0 7% 1rem 0;
	width: 150px;
	margin-bottom: 0;
    margin-top: 5px;
}

p {
    text-align: left;
    margin-left: 5%;
    margin-bottom: 0;
    color: black;
}


input#btUse {
    border-radius: 10rem;
    width: 50%;
}

h2 {
    padding-top: 20px;
    color:#3a3b45 !important;
}

div#divZip {
    padding-bottom: 1px;
    background-color: white;
}

#divZip table{
		width: 90%;
		margin: 1px 5%;
	}
	
div#divPage {
    background-color: white;
    padding-top: ;
}

th {
    background-color: #39484F;
    color: white;
    height: 30px;
    padding-bottom: 1px;
    border: 1px solid #39484F;
}

td {
    border: 1px solid #39484F;
}

div#divPage {
    border-radius: 0 0 0.4rem 0.4rem;
    padding: 5px 0;
}
div#divCount {
	margin: 50px 0 0 0;
}
</style>
	

<script type="text/javascript" src="<c:url value='/resources/js/base/jquery-3.6.0.min.js'/>"></script>
<script type="text/javascript" src="<c:url value='/resources/js/base/paging.js'/>"></script>
	
<script type="text/javascript">
	var totalCount=0;
	
	function setZipcode(address, zipcode){
		$(opener.document).find('#zipcode').val(zipcode);
		$(opener.document).find('input[name=address]').val(address);
		self.close();
	}
	
	$(function(){
		$('#dong').focus();
		
		$('#frmZip').submit(function(){
			if($('#dong').val()==''){
				alert('???????????? ???????????????');
				$('#dong').focus();
				return false;				
			}
			
			$.send(1);
			
			event.preventDefault();	
		});
		
	});
	
	$.send = function(curPage){
		$('#currentPage').val(curPage);
		
		$.ajax({
			url : "<c:url value='/account/ajaxZipcode'/>",
			type:"post",
			data:$("#frmZip").serializeArray(),
			dataType:"json",
			success:function(res){
				totalCount=res.results.common.totalCount;
				var errorCode=res.results.common.errorCode;
				var errorMessage=res.results.common.errorMessage;
				
				if(errorCode !=0){
					alert(errorCode +" => " + errorMessage);
				}else{
					if(res!=null){
						makeList(res);
						pageMake();
					}
				}
			},
			error:function(xhr, status, error){
				alert("error : " + error);
			}
		});
	}
	
	function makeList(jsonStr){
		if(totalCount==0){
			$('#divCount').html("");
			$('#divZip').html("<p style='text-align:center'>????????? ????????? ????????????.</p>");
			
			return;	
		}
		
		$('#divCount').html("<p>????????? ?????? ?????? ?????? ("+ totalCount +"???)</p>");
		
		var str="<table class='box2'>";
		str+="<tr><th style='width:20%'>????????????</th>";
		str+="<th style='width:80%'>????????? ??????</th></tr>";
		
		$(jsonStr.results.juso).each(function(idx, item){
			var roadAddr = this.roadAddr;
			var zipNo=this.zipNo;
			
			str += "<tr>";
			str += "<td>"+zipNo+"</td>";
			str += "<td>";
			str += "<a href='#' onclick='setZipcode(\""+roadAddr+"\", \""+zipNo+"\")'>";
			str += roadAddr +"</a></td>";			
			str += "</tr>";
		});
		
		str+="</table>";
				
		$('#divZip').html(str);
	}
	
	//????????? ??????
	function pageMake(){
		var blockSize=10;		
		pagination($('#currentPage').val(), $('#countPerPage').val(),
				blockSize, totalCount);
		
		//?????? ??????
		var str="";
		if(firstPage>1){ 
			str+="<a href='#' onclick='$.send("+ (firstPage-1)+")'>";
			str+="<img src='<c:url value='/resources/img/first.JPG' />'></a>";
		}
						
		//[1][2][3][4][5][6][7][8][9][10]
		for(var i = firstPage;i<=lastPage;i++){
			if(i==currentPage){
				str+="<span style='color:blue;font-weight:bold'>"+i+"</span>";
			}else{									
				str+="<a href='#' onclick='$.send("+i+")'>["+
					i + "]</a>";
			}		
		}//for
		
		if(lastPage<totalPage){
			str+="<a href='#' onclick='$.send("+(lastPage+1)+")'>";			
			str+="<img src='<c:url value='/resources/img/last.JPG' />'></a>";
		}
		
		$("#divPage").html(str);
	}
</script>

</head>
<body class="bg-gradient-primary">
<div class="divBase">
<h2>????????? ?????? ??????</h2>
<p>???????????????, ????????? ?????? ????????? ???????????????</p>
<form name="frmZip" id="frmZip" method="post">
	<!-- <input type="text" class="form-control form-control-user"
	id="dong" placeholder="????????? ??? : ?????????(???????????? 58), ?????????(???????????????), ??????(????????? 25)" name="dong"> -->
	<!-- <label for="dong">?????????</label>
	<input type="text" name="dong" id="dong"> -->
	<!-- <input type="submit" value="??????"> -->
	<!-- <input type="submit" class="btn btn-primary btn-user btn-block" id="regbtn" value="??????"> -->
	<input type="text" name="dong" id="dong" class="form-control form-control-user" 
		 placeholder="ex) ?????????(???????????? 58), ??????(????????? 25)">
		<input type="submit" id="submit" class="btn btn-primary btn-user btn-user btnbase" value="??????"/>
	<input type="hidden" name="currentPage" id="currentPage" value="1"/>				<!-- ?????? ?????? ?????? (?????? ?????????. currentPage : n > 0) -->
  	<input type="hidden" name="countPerPage" id="countPerPage" value="6"/>			<!-- ?????? ?????? ?????? (???????????? ?????? ??????. countPerPage ?????? : 0 < n <= 100) -->
  	<input type="hidden" name="confmKey" value="U01TX0FVVEgyMDE3MTIxODE3Mzc0MTEwNzU1Njg="/> <!-- ?????? ?????? ?????? (?????????) -->
  	<input type="hidden" name="resultType" value="json"/> 
</form>


<div id="divCount"></div>
<div id="divZip"></div>
<div id="divPage"></div>

</div>
</body>
</html>