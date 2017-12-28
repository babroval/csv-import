<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>CSV - view</title>
<style>
	body {font-family: Arial; text-align: center;}
	table {border-collapse: collapse; margin: auto;}
	td {border: 1px solid #000; padding: 15px; width: 150px; text-align: left;}
	tr:nth-child(1) {background: navy; color: white;}
	tr:nth-child(1) td {text-align: center;}
	select {margin-right: 20px; padding: 2px 5px;}
</style>
</head>

<body onload="setSelect()">

	<h1>Users Table</h1>

		<form action="MainController" method="post">
			<p>
			<b>Number Users on Page :</b>
			<select id="numUsers" name="numUsers" onchange="select()">
				<option value="2">2</option>
				<option value="3">3</option>
				<option value="4">4</option>
			</select>

			<b>Sort by : </b><select id="sort" name="sort" onchange="select()">
				<option value="name">Name</option>
				<option value="surname">Surname</option>
				<option value="email">Email</option>
				<option value="login">Login</option>
				<option value="tel">Phone</option>
			</select>
			</p>
			
			<table>
				<tr>
					<td>Name</td>
					<td>Surname</td>
					<td>Login</td>
					<td>Email</td>
					<td>Phone</td>
				</tr>
				<c:forEach var="user" items="${users}">
					<tr>
						<td>${user.name}</td>
						<td>${user.surname}</td>
						<td>${user.login}</td>
						<td>${user.email}</td>
						<td>${user.tel}</td>
					</tr>
				</c:forEach>
			</table>
			<p>Pages :
				<c:forEach var="pageNum" items="${pageCount}">
					<input type="submit" name="number" value="${pageNum}">
				</c:forEach>
			</p>
			<a href="menu.htm">Back to Menu</a>
		</form>
	
	<script type="text/javascript">
		function setSelect() {
			var select = document.getElementById("numUsers");
			select.value = '${usersCount}';
			var select = document.getElementById("sort");
			select.value = '${sort}';
		}
		function select() {
			var form = document.forms[0];
			form.submit();
		}
	</script>
</body>
</html>