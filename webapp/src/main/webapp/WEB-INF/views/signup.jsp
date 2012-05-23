<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>


    <link rel="stylesheet" href="../resources/css/reset.css"/>
    <link rel="stylesheet" href="../resources/css/styles.css"/>


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
        };

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
                                maxlength:12
                            } ,
                            password_confirm: {
                                required: true,
                                equalTo: "#password"
                            }
                        },
                        messages:{

                            login:{
                                required:"Введите логин!",
                                loginCheck:"Такой логин уже занят!",
                                minlength:"Введите хотя бы 2 символа!",
                                maxlength:"Длина не должна превышать 12 символов!"
                            },
                            password: {
                                required:"Введите пароль!",
                                minlength:"Минимум 6 символов!",
                                maxlength:"Максимум 12 символов!"
                            },
                            email:{
                                checkEmail:"Такой адрес уже занят!",
                                required:"Введите e-mail!",
                                email:"Введите корректный e-mail!"
                            },
                            password_confirm:{
                                required:"Подтвердите пароль!",
                                equalTo:"Пароли должны совпадать!"
                            }
                        },

                        onclick:false
                    }
            );

        });

    </script>


    <title></title>
</head>
<body>

<div id="wrapper" class="container_12 content">

    <!-- Text Logo -->
    <h1 id="logo" class="grid_4">Aurelius</h1>

    <!-- Navigation Menu -->
    <ul id="navigation" class="grid_8">
        <li><a href="/login" class="current"><span class="meta">Вход/Регистрация</span><br/>Вход</a></li>
        <li><a href="/contact"><span class="meta">Есть предложеня? Пишите!</span><br/>Обратная связь</a></li>
        <li><a href="/about"><span class="meta">О проекте</span><br/>О нас</a></li>
        <li><a href="/catalog"><span class="meta">Каталог товаров</span><br/>Каталог</a></li>
        <li><a href="/home"><span class="meta">Главная</span><br/>Главная</a></li>
    </ul>

    <div class="hr grid_12 clearfix">&nbsp;</div>

    <!-- Caption Line -->
    <h2 class="grid_12 caption">Вход в систему. <a href="/signup"><u>(Регистрация)</u></a></h2>

    <div class="hr grid_12 clearfix">&nbsp;</div>

    <!-- Column 1 / Content -->

    <div class="grid_12 center">
        <form id="regForm" enctype="application/x-www-form-urlencoded" method="post" class="form">
            <ul>
                <li class="clearfix">
                    <label for="login" class="label">Логин:</label>
                    <input type="text" name="login" id="login" size="50" class="input"/>
                </li>
                <li class="clearfix">
                    <label for="password" class="label">Пароль:</label>
                    <input id="password" type="password" name="password" size="50" class="input"/>
                </li>
                <li class="clearfix">
                    <label for="password_confirm" class="label">Подтверждение:</label>
                    <input id="password_confirm" type="password" name="password_confirm" size="50" class="input"/>
                </li>
                <li class="clearfix">
                    <label for="email" class="label">E-mail:</label>
                    <input id="email" type="text" name="email" size="50" class="input"/>
                </li>
                <li class="clearfix">
                    <button type="submit" id="regButton" name="regButton" class="button right">Подтвердить</button>
                </li>
            </ul>
        </form>

    </div>

    <div class="hr grid_12 clearfix">&nbsp;</div>


</div>
<!--end wrapper-->
<!-- Footer -->
<div class="footer ">
    <b>&copy; Copyright</b> Alexander Serebriyan
</div>

</body>
</html>