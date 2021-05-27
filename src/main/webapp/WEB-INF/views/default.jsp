<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>

</head>
<body>
	<div id="xp-container">
		<div id="route-container" style="border: 1px solid red; float: left; width: 80%;">
			<router-view></router-view>
		</div>
	</div>
</body>
	<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/model/vue/board-controller.min.js"></script>
</html>