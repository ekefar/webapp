<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<title>Login Page</title>
    <link type="text/css" href="../resources/css/flick/jquery-ui-1.8.18.custom.css" rel="stylesheet"/>
    <script type="text/javascript" src="../resources/js/jquery-1.7.1.min.js"></script>
    <script type="text/javascript" src="../resources/js/jquery-ui-1.8.18.custom.min.js"></script>

    <script type="text/javascript">
        $(document).ready(function(){
           $(":submit, :button").button();
        }) ;
    </script>
</head>
<body >
<div style="width: 400px; margin: 0px auto;" align="center">
	<h2>Sign in, Stranger! </h2>

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
					value="Sign in" style="width: 150px"/>
				</td>
                <td><a href="Register"><input id="regBtn" name="reset" value="Sign up" type="button" style="width: 150px"/> </a>
                </td>
			</tr>

		</table>

	</form>

</div>
</body>
</html>