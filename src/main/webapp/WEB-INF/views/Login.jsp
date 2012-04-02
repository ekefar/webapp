<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<title>Login Page</title>
</head>
<body >
	<h3>Sign in </h3>

	<form name='f' action="<c:url value='j_spring_security_check' />"
		method='POST'>

		<table>
			<tr>
				<td>User:</td>
				<td><input type='text' name='j_username' value=''>
				</td>
			</tr>
			<tr>
				<td>Password:</td>
				<td><input type='password' name='j_password' />
				</td>
			</tr>
			<tr>
				<td><input name="submit" type="submit"
					value="submit" />
				</td>
                <td><a href="Register"><input id="regBtn" name="reset" value="Signup" type="button" /> </a>
                </td>
			</tr>

		</table>

	</form>
</body>
</html>