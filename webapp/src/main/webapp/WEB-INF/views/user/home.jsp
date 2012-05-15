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

        function makeCurrent(element) {
            $("#profile_btn").removeClass("current");
            $("#all_offer_btn").removeClass("current");
            $("#my_offer_btn").removeClass("current");
            $("#cart_btn").removeClass("current");
            $("#orders_btn").removeClass("current");
            $("#my_purchases_btn").removeClass("current");
            $(element).addClass("current");

        }

        $(document).ready(function () {

            $("#profile_btn").click(function () {
                $("#content").load("/settings/profile/${user.id}");
                makeCurrent($(this));

            });

            $("#all_offer_btn").click(function () {
                $("#content").load("/offer/all/${user.id}");
                makeCurrent($(this));
            });

            $("#my_offer_btn").click(function () {
                $("#content").load("/offer/own/${user.id}");
                makeCurrent($(this));
            });

            $("#cart_btn").click(function () {
                $("#content").load("/cart/view/${user.id}");
                makeCurrent($(this));
            });

            $("#orders_btn").click(function () {
                $("#content").load("/order/view/${user.id}");
                makeCurrent($(this));
            });

            $("#my_purchases_btn").click(function () {
                $("#content").load("/order/purchase/view/${user.id}");
                makeCurrent($(this));
            });


            profile_btn.click();

        });

    </script>

</head>

<body>

<div id="wrapper" class="container_12 clearfix">

    <!-- Text Logo -->
    <h1 id="logo" class="grid_4">Aurelius</h1>

    <!-- Navigation Menu -->
    <ul id="navigation" class="grid_8">
        <li><a href="<c:url value=" j_spring_security_logout"/>"><br>Выход</a></li>
        <li><a id="cart_btn" href="#cart"><span class="meta">Корзина товаров</span><br/>Корзина</a></li>
        <li><a id="profile_btn" href="#profile"><span class="meta">Мои  настройки</span><br/>Профиль</a></li>
        <li><a id="my_offer_btn" href="#my_offers"><span class="meta">Мои предложения</span><br/>предложения</a></li>
        <li><a id="my_purchases_btn" href="#my_purchases"><span class="meta">Мои покупки</span><br/>Покупки</a></li>
        <li><a id="orders_btn" href="#orders"><span class="meta">Мои заказы</span><br/>Заказы</a></li>
        <li><a id="all_offer_btn" href="#all_offers"><span class="meta">Полный каталог</span><br/>Каталог</a></li>
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
