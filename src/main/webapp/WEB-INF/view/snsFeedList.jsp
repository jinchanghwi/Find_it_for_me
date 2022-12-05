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
<div style="margin:20px">
	<img src="/img/mainLogo.png" width="100px" style="float:left;">
	<div style="float:right;">
		<div class="input-group">
			<input type="text" class="form-control" placeholder="정답" id="" name="">
			<button class="btn btn-info">전송</button>
			<button class="btn btn-secondary">구글맵</button>
		</div>
	</div>
</div>
<br><br><hr><br>
<div style="width:600px;text-align:center;margin:0 auto">
	<table class="table" border="1">
		<c:choose>
			<c:when test="${!empty list}">
				<c:forEach items="${list}" var="list" varStatus="status">
					<tr>
						<td>${list.feedId}</td>
					</tr>
				</c:forEach>
			</c:when>
			<c:otherwise>
				<tr>
					<td>등록된 FEED가 없습니다.</td>
				</tr>
			</c:otherwise>
		</c:choose>
	</table>
</div>
</body>
</html>