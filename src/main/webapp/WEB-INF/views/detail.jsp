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
	label { display: block; margin-bottom: 10px; }
</style>
<title>게시물 상세</title>
</head>
<body>

		<label>제목: ${detail.title}</label>
		<label>작성자: ${detail.writer}</label>
		<label>내용: ${detail.contents}</label>
		<input type="button" value="수정" onclick="location.href='/update?bno=${detail.bno}'" />
		<input type="button" value="삭제" />
	
</body>
</html>