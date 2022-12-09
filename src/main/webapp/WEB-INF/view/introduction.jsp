<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/common.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Find it for me</title>
<script type="text/javascript">
$(document).ready(function(){
	$("#okBtn").click(function() {
		location.href="/snsFeedList";
	});
});
</script>
</head>
<body>
<div style="width:800px;margin:0 auto;margin-top:100px;text-align:center;">
	본 게임의 저작권은 진창휘 에게 있습니다.<br>
	본 게임의 무단 배포를 금지합니다.<br>
	<br>
	당신은 휘창이에게 납치된 진창휘 군을 구해야 합니다.<br>
	범인은 SNS를 통해 당신에게 메시지를 남길것입니다.<br>
	당신은 범인의 SNS를 확인하여 우측 상단 '정답' 칸에 범인이 원하는 메시지를 보내야합니다.<br>
	만약 범인이 <b>과일을 물어본다면</b> 정답칸에 <b>사과</b> 라고 적으면 됩니다.<br>
	본 화면을 캡쳐하여 원활한 플레이 하시길 권장합니다.<br>
	<br>
	준비가 되셨으면 아래 확인 버튼을 눌러주십시오.<br>
	<br>
	<div style="text-align:center;">
		<button type="button" style="width:300px;" class="btn btn-secondary" id="okBtn" name="okBtn">확인</button>
	</div>
</div>
</body>
</html>