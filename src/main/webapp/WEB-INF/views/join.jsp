<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page session="false" %>
<%@ page import="java.util.Date" %>
<%@page import="java.text.SimpleDateFormat" %>
<%@ page import="java.sql.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<style>
	label { display: block; margin-bottom: 10px; }
</style>
<title>회원가입</title>
</head>
<body>

		<label>아이디: 
			<input type="text" name="id" id="id" required />
		</label>
		<label>비밀번호: 
			<input type="password" name="pw" id="pw" required />
		</label>
		<label>이름: 
			<input type="text" name="name" id="name" required />
		</label>
		<label>휴대폰 번호: 
			<input type="text" name="phone" id="phone" required />
		</label>
		<label>이메일: 
			<input type="email" name="email" id="email" required />
		</label>
		<input type="button" value="작성" id="submit" />

</body>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script type="text/javascript">

	$("#submit").click(function() {
		$.ajax({
			url: '/join' ,
			//method: 'POST' ,
			type: 'POST' ,
			//dataType: 'json',
			//contentType: "application/json",
			async: true ,
			data: {
				mId: $("#id").val() ,
				mPw: $("#pw").val() ,
				mName: $("#name").val() ,
				mPhone: $("#phone").val() ,
				mEmail: $("#email").val()
			} ,
			success: function(data) {
				console.log(data);
				alert(data.resultText);
			} ,
			error: function(e) {
				console.log(e);
				alert("오류가 발생했습니다.");
			}
		});
	});
	
</script>
</html>