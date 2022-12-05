<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/common.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Find it for me</title>
<script type="text/javascript">
$(document).ready(function(){
	$("#loginBtn").click(function() {
		var userId = $("#userId").val();
		var userPw = $("#userPw").val();

		if(userId == "jinch" && userPw == "1234"){
			alert("환영합니다.");
			location.href="/snsList";
		}else{
			alert("아이디와 패스워드를 확인해주세요");
		}
	});

	$("#userId").keypress(function(key) {
		if(key.keyCode == 13){
			$("#loginBtn").trigger("click");
		}
	});

	$("#userPw").keypress(function(key) {
		if(key.keyCode == 13){
			$("#loginBtn").trigger("click");
		}
	});
});
</script>
</head>
<body>
<div style="width:300px;margin:0 auto;margin-top:100px">
	<div style="text-align:center;">
		<img src="/img/mainLogo.png" width="300px">
	</div>
	<div class="input-group mb-3">
	  <span class="input-group-text" id="basic-addon1" style="width:100px;">아이디</span>
	  <input type="text" class="form-control" placeholder="ID" id="userId" name="userId">
	</div>
	<div class="input-group mb-3">
	  <span class="input-group-text" id="basic-addon1" style="width:100px;">패스워드</span>
	  <input type="text" class="form-control" placeholder="PASSWORD" id="userPw" name="userPw">
	</div>
	<div style="text-align:center;">
		<button type="button" style="width:300px;" class="btn btn-secondary" id="loginBtn" name="loginBtn">Login</button>
	</div>
</div>
</body>
</html>