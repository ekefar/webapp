<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Aurelius | Страница пользователя</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>

    <!-- Stylesheets -->
    <link rel="stylesheet" href="../../resources/css/reset.css"/>
    <link rel="stylesheet" href="../../resources/css/styles.css"/>
    <link type="text/css" href="../../resources/css/flick/jquery-ui-1.8.18.custom.css" rel="stylesheet"/>
    <link type="text/css" href="../../resources/css/table_style.css" rel="stylesheet"/>
    <link rel="stylesheet" href="../../resources/css/flexigrid.pack.css">

    <!-- Scripts -->
    <script type="text/javascript" src="../../resources/js/jquery-1.7.1.min.js"></script>
    <script type="text/javascript" src="../../resources/js/jquery-ui-1.8.18.custom.min.js"></script>
    <script type="text/javascript" src="../../resources/js/jquery.validate.min.js" charset="utf-8"></script>
    <script type="text/javascript" src="../../resources/js/jquery.form.js" charset="utf-8"></script>
    <script type="text/javascript" src="../../resources/js/flexigrid.pack.js"></script>

    <!--[if IE 6]>
    <script type="text/javascript" src="../../resources/js/DD_belatedPNG_0.0.8a-min.js"></script>
    <script type="text/javascript">
        /* EXAMPLE */
        DD_belatedPNG.fix('.button');

        /* string argument can be any CSS selector */
        /* .png_bg example is unnecessary */
        /* change it to what suits you! */
    </script>
    <![endif]-->

    <script type="text/javascript">

        $(document).ready(function () {
            function makeCurrent(element) {
                $("#users_btn").removeClass("current");
                $("#offers_btn").removeClass("current");
                $("#products_btn").removeClass("current");
                $(element).addClass("current");

            }

            $("#users_btn")
                    .click(function () {
                        $("#content").load("/admin/users");
                        makeCurrent($(this));
                    });

            $("#offers_btn")
                    .click(function () {
                        $("#content").load("/admin/offers");
                        makeCurrent($(this));
                    });

            $("#products_btn")
                    .click(function () {
                        $("#content").load("/admin/products");
                        makeCurrent($(this));
                    });

            users_btn.click();

        });

    </script>

</head>

<body>

<div id="wrapper" class="container_12 clearfix">

    <!-- Text Logo -->
    <h1 id="logo" class="grid_4">Aurelius</h1>

    <!-- Navigation Menu -->
    <ul id="navigation" class="grid_8">

        <li><a id="users_btn" href="#users">Пользователи</a></li>
        <li><a id="offers_btn" href="#offers">Предложения</a></li>
        <li><a id="products_btn" href="#products">Товары</a></li>
        <li><a id="profile_btn" href="/admin/userProfile/${user.id}">user</a></li>

    </ul>

    <div class="hr grid_12 clearfix">&nbsp;</div>
    <!-- Column 1 / Content -->

    <div id="content" class="grid_11">

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
