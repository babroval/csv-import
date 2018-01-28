<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<title>CSV - import</title>
<style>
	body {font-family: Arial; text-align: center;}
	h2 {color: grey;}
</style>
</head>
<body>
	 
		<h1>CSV Project Import</h1>
     
        <h2>${info}</h2>
       
		<form action="main.htm" method="post">
		     <input type="submit" name="import" value="Import from CSV file">
		</form>
   
        <p><a href="menu.htm">Back to Menu</a></p>
	
</body>
</html>