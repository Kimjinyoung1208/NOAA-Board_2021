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
	<a href="/fileDownload?bno=${detail.bno}"><button id="downloadBtn">다운로드</button></a>
	<button id="updateBtn">수정</button>
	<button id="deleteBtn">삭제</button>
	<a href="/?num=1"><button>목록</button></a>
	
	<ul>
<!-- 		<li> -->
<!-- 			<div> -->
<!-- 				<p>첫번째 댓글 작성자</p> -->
<!-- 				<p>첫번째 댓글</p> -->
<!-- 			</div> -->
<!-- 		</li> -->
<!-- 		<li> -->
<!-- 			<div> -->
<!-- 				<p>두번째 댓글 작성자</p> -->
<!-- 				<p>두번째 댓글</p> -->
<!-- 			</div> -->
<!-- 		</li> -->
<!-- 		<li> -->
<!-- 			<div> -->
<!-- 				<p>세번째 댓글 작성자</p> -->
<!-- 				<p>세번째 댓글</p> -->
<!-- 			</div> -->
<!-- 		</li> -->
	</ul>
	
	<div>
		<p>
			<label>댓글 작성자</label>
			<input type="text" />
		</p>
		<p>
			<textarea row="5" cols="50"></textarea>
		</p>
		<p>
			<button type="button">댓글 작성</button>
		</p>
	</div>

</body>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script type="text/javascript">

	$(document).ready(function() {
		$.ajax({
			url: '/detailContent',
			type: 'GET',
			async: true,
			dataType: 'json',
			contentType: 'application/json',
			data: {
				bno: ${detail.bno}
			},
			success: function(data) {
				console.log(data);
				
				$('#detailList').append(
						'<label>제목 : ' + data.title + '</label>' 
						+ '<label>작성자 : ' + data.writer + '</label>' 
						+ '<label>내용 : ' + data.contents + '</label>' 
						+ '<label>파일 원본명 : ' + data.org_fname + '</label>' 
						+ '<label>파일 저장명 : ' + data.save_fname + '</label>' 
						+ '<label>파일 경로 : ' + data.fpath + '</label>');
				
				if (!data.result) {
					$("#updateBtn").hide();
					$("#deleteBtn").hide();
				}
			},
			error: function(e) {
				console.log(e);
				alert("불러오기 실패");
			}
		});
	});
		
	$.ajax({
		url: '/viewCount',
		type: 'GET',
		async: true,
		data: {
			bno: ${detail.bno}
		},
		success: function(data) {
		},
		error: function(e) {
			console.log(e);
			alert("조회수 실패");
		}
	});
	
	$("#updateBtn").click(function() {
		location.href="/update?bno=${detail.bno}";
	});
	
	$("#deleteBtn").click(function() {
		location.href="/delete?bno=${detail.bno}";
	});
	
</script>

</html>