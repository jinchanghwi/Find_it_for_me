<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
</head>
<body>
<!-- Offcanvas Sidebar -->
<div class="offcanvas offcanvas-bottom" id="demo" style="--bs-offcanvas-height:350px;">
  <div class="offcanvas-header">
    <h6 class="offcanvas-title">아이템창</h6>
    <button type="button" class="btn-close text-reset" data-bs-dismiss="offcanvas"></button>
  </div>
  <div class="offcanvas-body">
	<c:forEach items="${itemList}" var="list" varStatus="status">
	</c:forEach>
  </div>
</div>
</body>