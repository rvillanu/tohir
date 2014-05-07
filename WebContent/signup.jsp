<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Signup</title>
</head>
<body>
	<form action="SignupServlet" method="POST">
		First Name: <input type="text" name="firstName" required/><br>
		Last Name: <input type="text" name="lastName" required/><br>
		Email: <input type="text" name="email" required/><br>
		Username: <input type="text" name="username" required/><br>
		Password: <input type="password" name="password" required/><br> 
		Confirm password: <input type="password" name="passwordConfirm" required/><br>
		<input type="submit" value="Sign up"/><br>	 
	</form>
</body>
</html>