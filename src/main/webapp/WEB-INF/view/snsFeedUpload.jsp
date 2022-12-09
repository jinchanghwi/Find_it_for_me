<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/common.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Find it for me</title>
<script type="text/javascript">
$(document).ready(function(){
	$("#uploadFile").change(function(){
		setImageFromFile(this, '#previewImg');
	});
});
</script>
</head>
<body>
<div style="width:700px;margin:0 auto;margin-top:100px">
	<form id="mainForm" action="/uploadFeed" method="post" enctype="multipart/form-data">
		<!-- 이미지 미리보기 -->
		<div class="input-group mb-3">
			<img id="previewImg" width="400px" style="margin:0 auto;">
		</div>
		<!-- 업로드 이미지 -->
		<div class="input-group mb-3">
			<input type="file" class="form-control" id="uploadFile" name="uploadFile">
		</div>
		<!-- 아이디 -->
		<div class="input-group mb-3">
			<span class="input-group-text" id="basic-addon1" style="width:100px;">아이디</span>
			<input type="text" class="form-control" style="width:auto;" value="HwiChang" id="writerId" name="writerId">
		</div>
		<!-- 내용 -->
		<div class="input-group mb-3">
			<span class="input-group-text" style="width:100px;">내용</span>
			<textarea class="form-control" style="width:auto;" id="text" name="text"></textarea>
		</div>
		<!-- 서순
		<div class="input-group mb-3">
			<span class="input-group-text" id="basic-addon1" style="width:100px;">순서</span>
			<input type="text" class="form-control" style="width:auto;" id="showLevel" name="showLevel">
		</div> -->
		<!-- 정답 -->
		<div class="input-group mb-3">
			<span class="input-group-text" id="basic-addon1" style="width:100px;">정답</span>
			<input type="text" class="form-control" style="width:auto;" id="answer" name="answer">
		</div>
		<!-- 노출여부 -->
		<div class="form-check">
			<input class="form-check-input" type="radio" value="N" name="showYN" id="showN" checked>
			<label class="form-check-label" for="showN">
		    	화면에 안 나옴
			</label>
		</div>
		<div class="form-check">
			<input class="form-check-input" type="radio" value="Y" name="showYN" id="showY">
			<label class="form-check-label" for="showY">
				화면에 나옴
			</label>
		</div>
		<br>
		<!-- 업로드 버튼 -->
		<div style="text-align:center;">
			<button type="submit" style="width:300px;" class="btn btn-secondary" id="uploadBtn" name="uploadBtn">업로드</button>
		</div>
	</form>
</div>
</body>
</html>