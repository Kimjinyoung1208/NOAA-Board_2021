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
<%-- 	<label>제목: ${detail.title}</label> --%>
<%-- 	<label>작성자: ${detail.writer}</label> --%>
<%-- 	<label>내용: ${detail.contents}</label> --%>
<%-- 	<label>파일 원본명: ${detail.org_fname}</label> --%>
<%-- 	<label>파일 저장명: ${detail.save_fname}</label> --%>
<%-- 	<label>파일 경로: ${detail.fpath}</label> --%>
	<a href="/fileDownload?bno=${detail.bno}"><button type="button" id="downloadBtn">다운로드</button></a>
	<a href="/update?bno=${detail.bno}"><button>수정</button></a>
	<a href="/delete?bno=${detail.bno}"><button>삭제</button></a>

</body>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script type="text/javascript">

	$(document).ready(function() {
		$.ajax({
			url: '/detailList',
			type: 'POST',
			dataType: 'json',
			//contentType: "application/json",
			async: true,
			data: {
				bno: ${detail.bno}
			},
			success: function(data) {
				console.log(data);
			},
			error: function(e) {
				console.log(e);
			}
		});
	});
	
</script>

</html>