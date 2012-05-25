<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Vegetary | About Us</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />

    <!-- Stylesheets -->
    <link rel="stylesheet" href="../resources/css/reset.css" />
    <link rel="stylesheet" href="../resources/css/styles.css" />

    <!-- Scripts -->

    <!--[if IE 6]>
    <script src="../resources/js/DD_belatedPNG_0.0.8a-min.js"></script>

    <script type="text/javascript" src="../resources/js/jquery-ui-1.8.18.custom.min.js"></script>
    <script type="text/javascript" src="../resources/js/jquery.validate.min.js" charset="utf-8"></script>
    <script type="text/javascript" src="../resources/js/jquery.form.js" charset="utf-8"></script>

    <script type="text/javascript">
        $(document).ready(function () {
            $(":submit, :button").button();
        });
    </script>

    <script>
        /* EXAMPLE */
        DD_belatedPNG.fix('.button');

        /* string argument can be any CSS selector */
        /* .png_bg example is unnecessary */
        /* change it to what suits you! */
    </script>
    <![endif]-->

</head>

<body>

<div id="wrapper" class="container_12 content">

    <!-- Text Logo -->
    <h1 id="logo" class="grid_4">Vegetary</h1>

    <!-- Navigation Menu -->
    <ul id="navigation" class="grid_8">
        <li><a href="/login" class="current"><span class="meta">Вход/Регистрация</span><br />Вход</a></li>
        <li><a href="/contact"><span class="meta">Есть предложеня? Пишите!</span><br />Обратная связь</a></li>
        <li><a href="/about" ><span class="meta">О проекте</span><br />О нас</a></li>
        <li><a href="/catalog"><span class="meta">Каталог товаров</span><br />Каталог</a></li>
        <li><a href="/home" ><span class="meta">Главная</span><br />Главная</a></li>
    </ul>

    <div class="hr grid_12 clearfix">&nbsp;</div>

    <!-- Caption Line -->
    <h2 class="grid_12 caption">Вход в систему. <a href="/signup"><u>(Регистрация)</u></a></h2>

    <div class="hr grid_12 clearfix">&nbsp;</div>

    <!-- Column 1 / Content -->

    <div class="grid_12 center" >
        <form action="<c:url value='j_spring_security_check' />" method='post' class="form" >
            <ul>
                <li class="clearfix">
                    <label for="login">Логин:</label>
                    <input id="login" type='text' name='j_username' class="input">
                    <div class="clear"></div>
                </li>
                <li class="clearfix">
                    <label for="password">Пароль:</label>
                    <input id="password" type='password' name='j_password' class="input"/>
                    <div class="clear"></div>
                </li>

                <li class="clearfix">
                    <div id="button">
                        <input type='submit' id='send_message' class="button" value='Вход' />
                    </div>
                </li>
            </ul>
        </form>
    </div>
    <div class="hr grid_12 clearfix">&nbsp;</div>

</div><!--end wrapper-->
<!-- Footer -->
<div class="footer ">
    <b>&copy; Copyright</b> Alexander Serebriyan
</div>
</body>
</html>

