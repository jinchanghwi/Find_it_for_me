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
<div style="width:500px;margin:0 auto;margin-top:100px">
	<form id="mainForm">
		<div class="input-group mb-3">
		  <span class="input-group-text" id="basic-addon1" style="width:100px;">아이디</span>
		  <input type="text" class="form-control" style="width:auto;" id="writerId" name="writerId">
		</div>
		<div class="input-group mb-3">
		  <span class="input-group-text" style="width:100px;">내용</span>
		  <textarea class="form-control" style="width:auto;" id="text" name="text"></textarea>
		</div>
		<div class="input-group mb-3">
			<input type="file" class="form-control" id="image" name="image">
		</div>
		<div style="text-align:center;">
			<button type="button" style="width:300px;" class="btn btn-secondary" id="uploadBtn" name="uploadBtn">업로드</button>
		</div>
	</form>
</div>
</body>
</html>