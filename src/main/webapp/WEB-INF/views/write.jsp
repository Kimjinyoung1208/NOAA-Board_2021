<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<style>
	form label { display: block; margin-bottom: 10px; }
</style>
<title>게시물 작성</title>
</head>
<body>
	
	<form method="post">
		<label>제목: 
			<input type="text" name="title" />
		</label>
		<label>작성자: 
			<input type="text" name="writer" />
		</label>
		<label>내용: 
			<textarea name="contents" placeholder="내용 작성"></textarea>
		</label>
		<input type="submit" value="작성" />
	</form>
	
</body>
</html>