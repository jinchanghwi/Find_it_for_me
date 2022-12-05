<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/common.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Find it for me</title>
<script type="text/javascript">
$(document).ready(function(){

});
</script>
</head>
<body>
<div style="width:500px;text-align:center;">
	<div class="input-group mb-3">
	  <span class="input-group-text" id="basic-addon1" style="width:100px;">아이디</span>
	  <input type="text" class="form-control" placeholder="ID" id="userId" name="userId">
	</div>
	<div class="input-group mb-3">
	  <span class="input-group-text" id="basic-addon1" style="width:100px;">패스워드</span>
	  <input type="text" class="form-control" placeholder="PASSWORD" id="userPw" name="userPw">
	</div>
	<div style="text-align:center;">
		<button type="button" style="width:500px;" class="btn btn-secondary">Login</button>
	</div>
</div>
</body>
</html>