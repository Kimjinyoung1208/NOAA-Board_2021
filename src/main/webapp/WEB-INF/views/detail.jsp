<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<%@page import="java.text.SimpleDateFormat" %>
<%@ page import="java.sql.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<style>
	label, #downloadBtn { display: block; margin-bottom: 10px; }
	a { text-decoration: none; color: black; }
</style>
<title>게시물 상세</title>
</head>
<body>

		<div id="detailList"></div>
		<a href="/fileDownload?bno=${detail.bno}"><button type="button" id="downloadBtn">다운로드</button></a>
		<a href="/update?bno=${detail.bno}"><button>수정</button></a>
		<a href="/delete?bno=${detail.bno}"><button>삭제</button></a>
	
</body>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script type="text/javascript">

	$.ajax({
		url: '/detailList',
		type: 'POST',
		dataType: 'json',
		data: {
			bno: ${detail.bno}
		},
		success: function(data) {
			console.log(data);
			$("#detailList").append("<label>" + data.title + "</label>" + "<label>" + data.writer + "</label>" + "<label>" + data.contents + "</label>" + "<label>" + data.org_fname + "</label>" + "<label>" + data.save_fname + "</label>" + "<label>" + data.fpath + "</label>");
		},
		error: function(e) {
			console.log(e);
		}
	});

</script>
</html>