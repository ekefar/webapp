
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>Aurelius | Contact Us</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />

    <!-- Stylesheets -->
    <link rel="stylesheet" href="../resources/css/reset.css" />
    <link rel="stylesheet" href="../resources/css/styles.css" />

    <!-- Scripts -->
    <script type="text/javascript" src="../resources/js/jquery-1.7.1.min.js"></script>
    <script type="text/javascript"  src="../resources/js/contact.js"></script>
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
        <li><a href="/login"><span class="meta">Вход/Регистрация</span><br />Вход</a></li>
        <li><a href="/contact" class="current"><span class="meta">Есть предложеня? Пишите!</span><br />Обратная связь</a></li>
        <li><a href="/about"><span class="meta">О проекте</span><br />О нас</a></li>
        <li><a href="/catalog"><span class="meta">Каталог товаров</span><br />Каталог</a></li>
        <li><a href="/home" ><span class="meta">Главная</span><br />Главная</a></li>
    </ul>

    <div class="hr grid_12 clearfix">&nbsp;</div>

    <!-- Caption Line -->
    <h2 class="grid_12 caption clearfix">Есть вопросы или пожелания? Напишите нам!</h2>

    <div class="hr grid_12 clearfix">&nbsp;</div>

    <!-- Column 1 / Content -->
    <div class="grid_8">
        <p></p>
        <!-- Contact Form -->
        <form action='/mail/send' method='post' id='contact_form'>
            <h3>Заполните форму ниже</h3>
            <div class="hr dotted clearfix">&nbsp;</div>
            <ul>
                <li class="clearfix">
                    <label for="name">Name</label>
                    <input type='text' name='name' id='name' />
                    <div class="clear"></div>
                    <p id='name_error' class='error'>Insert a Name</p>
                </li>
                <li class="clearfix">
                    <label for="email">Email Address</label>
                    <input type='text' name='email' id='email' />
                    <div class="clear"></div>
                    <p id='email_error' class='error'>Enter a valid email address</p>
                </li>
                <li class="clearfix">
                    <label for="subject">Subject</label>
                    <input type='text' name='subject' id='subject' />
                    <div class="clear"></div>
                    <p id='subject_error' class='error'>Enter a message subject</p>
                </li>
                <li class="clearfix">
                    <label for="message">Message</label>
                    <textarea name='message' id='message' rows="30" cols="30"></textarea>
                    <div class="clear"></div>
                    <p id='message_error' class='error'>Enter a message</p>
                </li>
                <li class="clearfix">

                    <p id='mail_success' class='success'>Thank you. I'll get back to you as soon as possible.</p>
                    <p id='mail_fail' class='error'>Sorry, an error has occured. Please try again later.</p>
                    <div id="button">
                        <input type='submit' id='send_message' class="button" value='Submit' />
                    </div>
                </li>
            </ul>
        </form>
    </div>

    <!-- Column 2 / Sidebar -->
    <div class="grid_4 contact">

        <!-- Adress and Phone Details -->
        <h4>Адреса и телефоны</h4>
        <div class="hr dotted clearfix">&nbsp;</div>
        <ul>
            <li>
                <strong>Aurelius</strong><br />
                Харьков, ул. Улица 1,<br />
                Дом 1, 61000<br />
               <br /><br />
            </li>
            <li>(888) 888-8888</li>
            <li>(888) 888-8888</li>
        </ul>
        <br><br>
        <!-- Email Addresses -->
        <h4>Электронная почта</h4>
        <div class="hr dotted clearfix">&nbsp;</div>
        <ul>
            <li><a href="mailto:ekefar@gmail.com">aurelius@aurelius.com</a></li>
        </ul>

    </div>

    <div class="hr grid_12 clearfix">&nbsp;</div>



</div><!--end wrapper-->
<!-- Footer -->
<div class="footer ">
    <b>&copy; Copyright</b> Alexander Serebriyan
</div>
</body>
</html>