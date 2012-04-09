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
    <link type="text/css" href="../resources/css/flick/jquery-ui-1.8.18.custom.css" rel="stylesheet"/>
    <script type="text/javascript" src="../resources/js/jquery-1.7.1.min.js"></script>
    <script type="text/javascript" src="../resources/js/jquery-ui-1.8.18.custom.min.js"></script>


    <script type="text/javascript">
        $(document).ready(function () {
            $('#regButton').click(function () {
                $.post("/Register", $('#myForm').serialize(), 'json');
                $("#regForm").append("Check your e-mail.")
            });

            $("button").button();

        });

    </script>
    <title></title>
</head>
<body>
<div id="signup" align="center">
    <form id="regForm" enctype="application/x-www-form-urlencoded" method="post" style="width: 400px; margin: 0px auto;">
        <table>
            <tr>
                <td>
                    Login:
                </td>
                <td>
                    <input type="text" name="login" size="30"/>
                </td>
            </tr>
            <tr>
                <td>
                    Password:
                </td>
                <td>

                    <input type="password" name="password" size="30"/>
                </td>
            </tr>
            <tr>
                <td>
                    E-mail:
                </td>
                <td>
                    <input type="text" name="email" size="30"/>
                </td>
            </tr>
            <tr>
                <td align="center">
                    <button id="regButton" name="regButton" type="button" style="width: 170px">Sign up</button>
                </td>

                <td align="center">
                    <a href="/Welcome"> <button id="home" name="regButton" type="button" style="width: 170px">Home</button> </a>
                </td>
            </tr>
        </table>

    </form>

</div>



</body>
</html>