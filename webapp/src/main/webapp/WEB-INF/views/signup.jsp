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
    <link type="text/css" href="../resources/css/style.css" rel="stylesheet"/>

    <script type="text/javascript" src="../resources/js/jquery-1.7.1.min.js"></script>
    <script type="text/javascript" src="../resources/js/jquery-ui-1.8.18.custom.min.js"></script>
    <script type="text/javascript" src="../resources/js/jquery.validate.min.js" charset="utf-8"></script>
    <script type="text/javascript" src="../resources/js/jquery.form.js" charset="utf-8"></script>

    <script type="text/javascript">

        var emailCheck = function () {
            var retResp;

            $.ajax({
                url:"/register/email/check",
                type:"GET",
                async:false,
                data:{
                    email:function () {
                        return $("#email").val()
                    }
                },
                success:function (data) {
                    retResp = data;
                }
            });

            return retResp == "true";
        }

        var loginCheck = function () {
            var retResp;

            $.ajax({
                url:"/register/login/check",
                type:"GET",
                async:false,
                data:{
                    login:function () {
                        return $("#login").val()
                    }
                },
                success:function (data) {
                    retResp = data;
                }
            });

            return retResp == "true";
        }
    </script>

    <script type="text/javascript">


        $(document).ready(function () {

            $.validator.addMethod("checkEmail", emailCheck);

            $.validator.addMethod("loginCheck", loginCheck);

            $("#regForm").validate({
                        rules:{
                            "login":{
                                required:true,
                                minlength:2,
                                maxlength:12,
                                loginCheck:true
                            },
                            "email":{
                                required:true,
                                email:true,
                                checkEmail:true
                            },
                            "password":{
                                required:true,
                                minlength:6,
                                maxlength:18
                            }
                        },
                        messages:{
                            login:{
                                loginCheck:"This login name already taken!"
                            },
                            email:{
                                checkEmail:"This email already taken!"
                            }
                        },
                        onclick:false
                    }
            );

            $("button").button();

        });

    </script>


    <title></title>
</head>
<body>

<div id="header" class="header"></div>
<div id="distance"></div>
<div id="signup" align="center" class="registration_form">
    <form id="regForm" enctype="application/x-www-form-urlencoded" method="post">

        <table>
            <tr>
                <td>
                    Login:
                </td>
                <td>
                    <input type="text" name="login" id="login" size="30"
                           class="validate[required,,maxSize[16],ajax[ajaxUserCall]] "/>
                </td>
            </tr>
            <tr>
                <td>
                    Password:
                </td>
                <td>
                    <input type="password" name="password" size="30" class="validate[required] text-input"/>
                </td>
            </tr>
            <tr>
                <td>
                    E-mail:
                </td>
                <td>
                    <input type="text" id="email" name="email" size="30"
                           class="validate[required,custom[email]] text-input"/>
                </td>
            </tr>
            <tr>
                <td align="center">
                    <button id="regButton" name="regButton" type="submit" style="width: 170px">Sign up</button>
                </td>

                <td align="center">
                    <a href="/welcome">
                        <button id="home" name="home" type="button" style="width: 170px">Home</button>
                    </a>
                </td>
            </tr>
        </table>

    </form>

</div>


<div id="footer" class="footer">2012</div>


</body>
</html>