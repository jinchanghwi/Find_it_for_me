<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<%@ include file="/common/itemWindow.jsp" %>
<%@ include file="/common/modal.jsp" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<!-- 제이쿼리 -->
<script type="text/javascript" src="/js/jquery-3.6.1.min.js"></script>
<!-- 부트스트랩js -->
<script type="text/javascript" src="/js/bootstrap.min.js"></script>
<!-- 부트스트랩css -->
<link href="/css/bootstrap.min.css" rel="stylesheet" type="text/css">
<script type="text/javascript">
$(document).ready(function(){
	$("#myModal").show();
	$("#modalBody").text("모달입니다.");
	$("#modalCloseBtn").click(function() {
		console.log("모달닫기버튼 클릭")
	});

});
function comMsg(text){
	$("#modalBody").text("모달입니다.");
}
function setImageFromFile(input, expression) {
    if (input.files && input.files[0]) {
        var reader = new FileReader();
        reader.onload = function (e) {
            $(expression).attr('src', e.target.result);
        }
        reader.readAsDataURL(input.files[0]);
    }
}
</script>
</head>
</html>