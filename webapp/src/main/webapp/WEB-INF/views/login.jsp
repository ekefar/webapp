<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Aurelius | About Us</title>
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

<div id="wrapper" class="container_12 clearfix">

    <!-- Text Logo -->
    <h1 id="logo" class="grid_4">Aurelius</h1>

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
    <h2 class="grid_12 caption">Здесь можно почитать о проекте.</h2>

    <div class="hr grid_12 clearfix">&nbsp;</div>

    <!-- Column 1 / Content -->

    <div class="grid_11">
        <form name='f' action="<c:url value='j_spring_security_check' />"
              method='POST'>

            <table>
                <tr>
                    <td>Login:</td>
                    <td><input type='text' name='j_username' value=''>
                    </td>
                </tr>
                <tr>
                    <td>Password:</td>
                    <td><input type='password' name='j_password'/>
                    </td>
                </tr>
                <tr>
                    <td><a href="register"><input id="regBtn" name="reset" value="Register" type="button"
                                                  style="width: 150px"/> </a>
                    </td>
                    <td><input name="submit" type="submit"
                               value="Log in" style="width: 150px"/>
                    </td>

                </tr>

            </table>

        </form>

    </div>

    <div class="hr grid_12 clearfix">&nbsp;</div>

    <!-- Footer -->
    <p class="grid_12 footer clearfix">
        <span class="float"><b>&copy; Copyright</b> Alexander Serebriyan</span>
    </p>

</div><!--end wrapper-->
</body>
</html>

