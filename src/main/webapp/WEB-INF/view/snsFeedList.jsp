<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/common.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Find it for me</title>
<script type="text/javascript">
$(document).ready(function(){

	$("#uploadBtn").click(function() {
		location.href = "/snsFeedUpload";
	});

	$("#sendBtn").click(function() {
		if($("#answer").val() == ""){
			alert("정답을 입력해주세요.");
			return;
		}
		var map = {};
		map["answer"] = $("#answer").val();
		map["feedId"] = $(".table #firstFeedId").val();
		var url = "/answerCheck";

		$.ajax({
			 type : "post"
			,url : url
			,contentType : "application/json"
			,data : JSON.stringify(map)
			,dataType : "json"
			,success : function(json){
				console.log(json.resultCode);
				if(json.resultCode == "0000"){
					//정답일때
				}else{
					//오답일때
				}
			}
			,error : function(){
				alert("시스템 관리자에게 문의하세요. [통신 실패]");
			}
		});
	});
});
function deletePrcs(feedId){
	feedId = lpad(feedId,5,'0');
	$("#feedId").val(feedId);
	$("#frm").attr("action","/deleteFeed");
	$("#frm").submit();
}
</script>
</head>
<body>
<form id="frm">
	<input type="hidden" id="feedId" name="feedId" value="">
</form>
<div style="margin:20px">
	<img src="/img/mainLogo.png" width="100px" style="float:left;">
	<div style="float:right;">
		<div class="input-group">
			<input type="text" class="form-control" placeholder="정답" id="answer" name="answer">
			<button type="button" class="btn btn-info" id="sendBtn" name="sendBtn">전송</button>
			<c:if test="${grade eq 'MASTER'}">
				<button type="button" class="btn btn-info" id="uploadBtn" name="uploadBtn">업로드</button>
			</c:if>
			<!-- <button type="button" id="openModalBtn" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#myModal">모달</button>  -->
			<button class="btn btn-primary" type="button" data-bs-toggle="offcanvas" data-bs-target="#demo">아이템창</button>
		</div>
	</div>
</div>
<br><br><hr><br>
<div style="width:800px;text-align:center;margin:0 auto">
		<c:choose>
			<c:when test="${!empty list}">
				<c:forEach items="${list}" var="list" varStatus="status">
					<table class="table" border="1" style="width:800px;text-align:center;margin:0 auto">
						<tr>
							<td style="text-align:left;">
								<input type="hidden" value="${list.feedId}" name="firstFeedId" id="firstFeedId">
								<img class="rounded-circle" src="/img/${list.writerId}.png" width="25px">&nbsp;${list.writerId}
							</td>
						</tr>
						<tr>
							<td>
								<img src="/image/${list.image}" width="300px" style="margin:0 auto;">
							</td>
						</tr>
						<tr>
							<td style="text-align:left;">
								<b>${list.writerId}</b>
								<pre><c:out value="${list.text}" /></pre>
							</td>
						</tr>
					</table>
					<c:if test="${grade eq 'MASTER'}">
						<button type="button" class="btn btn-info" id="deleteBtn" name="deleteBtn" onclick="deletePrcs(${'' + list.feedId})">삭제</button>
					</c:if>
					<hr>
				</c:forEach>
			</c:when>
			<c:otherwise>
				<table class="table" border="1">
					<tr>
						<td>등록된 FEED가 없습니다.</td>
					</tr>
				</table>
			</c:otherwise>
		</c:choose>
</div>
</body>
</html>