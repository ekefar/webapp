<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Aurelius | A premium Business and Portfolio theme</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />

    <!-- Stylesheets -->
    <link rel="stylesheet" href="../resources/css/reset.css" />
    <link rel="stylesheet" href="../resources/css/styles.css" />

    <!-- Scripts -->
    <script type="text/javascript" src="../resources/js/jquery-1.7.1.min.js"></script>
    <script type="text/javascript" src="../resources/js/jquery.roundabout-1.0.min.js"></script>
    <script type="text/javascript" src="../resources/js/jquery.easing.1.3.js"></script>
    <script type="text/javascript">
        $(document).ready(function() { //Start up our Featured Project Carosuel
            $('#featured ul').roundabout({
                easing: 'easeOutInCirc',
                duration: 600
            });
        });
    </script>

    <!--[if IE 6]>
    <script type="text/javascript" src="../resources/js/DD_belatedPNG_0.0.8a-min.js"></script>
    <script type="text/javascript">
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
        <li><a href="/contact"><span class="meta">Есть предложеня? Пишите!</span><br />Обратная связь</a></li>
        <li><a href="/about"><span class="meta">О проекте</span><br />О нас</a></li>
        <li><a href="/login"><span class="meta">Вход/Регистрация</span><br />Аккаунт</a></li>
        <li><a href="/catalog"><span class="meta">Каталог товаров</span><br />Каталог</a></li>
        <li><a href="/home" class="current"><span class="meta">Главная</span><br />Главная</a></li>
    </ul>

    <div class="hr grid_12">&nbsp;</div>
    <div class="clear"></div>

    <!-- Featured Image Slider -->
    <div id="featured" class="clearfix grid_12">
        <ul>
            <li>
                <a href="portfolio_single.html">
                    <img src="images/600x300.gif" alt="" />
                </a>
            </li>
            <li>
                <a href="portfolio_single.html">
                    <img src="../resources/images/600x300.gif" alt="" />
                </a>
            </li>
            <li>
                <a href="portfolio_single.html">
                    <img src="images/600x300.gif" alt="" />
                </a>
            </li>
            <li>
                <a href="portfolio_single.html">
                    <img src="images/600x300.gif" alt="" />
                </a>
            </li>
            <li>
                <a href="portfolio_single.html">
                    <img src="images/600x300.gif" alt="" />
                </a>
            </li>
        </ul>
    </div>
    <div class="hr grid_12 clearfix">&nbsp;</div>

    <!-- Caption Line -->
    <h2 class="grid_12 caption clearfix">Добро пожаловать на <span>Aurelius</span>. Здесь Вам доступен огромный каталог сельхозпродукции по лучшим ценам.
        <u><a href="/login">Войдите</a></u> в систему и получите полный доступ ко всем возможностям системы!</h2>

    <div class="hr grid_12 clearfix quicknavhr">&nbsp;</div>
    <div id="quicknav" class="grid_12">
        <a class="quicknavgrid_3 quicknav alpha" href="/catalog">
            <h4 class="title ">Просмотреть каталог</h4>
            <p>Загляните в каталог предлагаемых товаров.</p>
            <p style="text-align:center;"><img alt="" src="images/Art_Artdesigner.lv.png" /></p>

        </a>
        <a class="quicknavgrid_3 quicknav" href="/signup">
            <h4 class="title ">Создать аккаунт</h4>
            <p>Сосдайте аккаунт и получите доступ ко всем возможностям системы. </p>
            <p style="text-align:center;"><img alt="" src="images/info.png" /></p>

        </a>
        <a class="quicknavgrid_3 quicknav" href="/about">
            <h4 class="title ">О нас</h4>
            <p>Загляните на страницу с информацией о компании.</p>
            <p style="text-align:center;"><img alt="" src="images/Blog_Artdesigner.lv.png" /></p>

        </a>
        <a class="quicknavgrid_3 quicknav" href="/contact">
            <h4 class="title ">Обратная связь</h4>
            <p>Есть вопросы либо пожелания? Напишите нам прямо сейчас!</p>
            <p style="text-align:center;"><img alt="" src="images/hungry_bird.png" /></p>
        </a>
    </div>
    <div class="hr grid_12 clearfix">&nbsp;</div>
    <!-- Footer -->
    <p class="grid_12 footer clearfix">
        <span class="float"><b>&copy; Copyright</b> Alexander Serebriyan</span>
    </p>

</div><!--end wrapper-->
</body>
</html>
