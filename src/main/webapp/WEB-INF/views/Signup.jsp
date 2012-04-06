<%--
  Created by IntelliJ IDEA.
  User: gar
  Date: 01.04.12
  Time: 16:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js"></script>

    <script type="text/javascript">
        $(document).ready(function () {
            $('#getBtn').click(function () {
                $.post("/Register", $('#myForm').serialize(), 'json');
            });
        });

    </script>
    <title></title>
</head>
<body>

<form id="myForm"  enctype="application/x-www-form-urlencoded" method="post">
    Login <input type="text" name="login"/> <br/>
    Password <input type="password" name="password"/>   <br/>
    E-mail <input type="text" name="email"/> <br/>

</form>
<br/>

<div id="dataDiv">
 <button id ="getBtn" name="regButton" >Sign up</button>
</div>



</body>
</html>