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
<form action="/sendAnswer">
<div style="margin:20px">
	<img src="/img/mainLogo.png" width="100px" style="float:left;">
	<div style="float:right;">
		<div class="input-group">
			<input type="text" class="form-control" placeholder="정답" id="answer" name="answer">
			<button type="submit" class="btn btn-info">전송</button>
			<button class="btn btn-secondary">구글맵</button>
		</div>
	</div>
</div>
</form>
<br><br><hr><br>
<div style="width:600px;text-align:center;margin:0 auto">

		<c:choose>
			<c:when test="${!empty list}">
				<c:forEach items="${list}" var="list" varStatus="status">
					<table class="table" border="1">
						<tr>
							<td style="text-align:left;">
								<img src="/img/${list.writerId}.png" width="25px">&nbsp;${list.writerId}
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