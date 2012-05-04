<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="UTF-8"/>
    <title>User profile</title>

    <link type="text/css" href="../resources/css/flick/jquery-ui-1.8.18.custom.css" rel="stylesheet"/>
    <link type="text/css" href="../resources/css/style.css" rel="stylesheet"/>
    <link href="../resources/css/fileuploader.css" rel="stylesheet" type="text/css"/>

    <script type="text/javascript" src="../resources/js/jquery-1.7.1.min.js"></script>
    <script type="text/javascript" src="../resources/js/jquery-ui-1.8.18.custom.min.js"></script>
    <script src="../resources/js/fileuploader.js" type="text/javascript"></script>
    <script type="text/javascript" src="../resources/js/jquery.validate.min.js" charset="utf-8"></script>
    <script type="text/javascript" src="../resources/js/jquery.form.js" charset="utf-8"></script>

    <script type="text/javascript">
        $(document).ready(function () {

            $("#profile_btn")
                    .click(function () {
                        $("#content").load("/settings/profile/" + $(this).attr("name"));

                    })
                    .button();

            $("#all_offer_btn")
                    .click(function () {
                        $("#content").load("/offer/all/" + $(this).attr("name"));
                    })
                    .button();

            $("#my_offer_btn")
                    .click(function () {
                        $("#content").load("/offer/own/" + $(this).attr("name"));
                    })
                    .button();

            $("#cart_btn")
                    .click(function () {
                        $("#content").load("/cart/view/" + $(this).attr("name"));
                    })
                    .button();

            $("#orders_btn")
                    .click(function () {
                        $("#content").load("/order/view/" + $(this).attr("name"));
                    }).button();

            profile_btn.click();

        });

    </script>


</head>
<body>

<div id="header" class="header">
    <div>Welcome, ${user.login}!</div>
    <div class="logout_btn">

        <a href="<c:url value=" j_spring_security_logout"/>">Logout</a>
    </div>
</div>


<div class="container">

    <div id="navigation" class="navigation_vertical">
        <div>
            <a id="profile_btn" class="navigation_btn" name="${user.id}" href="#profile">Profile</a>
        </div>

        <div>
            <a id="all_offer_btn" class="navigation_btn" name="${user.id}" href="#all_offers">All Offers</a>
        </div>

        <div>
            <a id="my_offer_btn" class="navigation_btn" name="${user.id}" href="#my_offers">My Offers</a>
        </div>

        <div>
            <a id="orders_btn" class="navigation_btn" name="${user.id}" href="#orders">Orders</a>
        </div>

        <div>
            <a id="cart_btn" class="navigation_btn" name="${user.cart.id}" href="#cart">View cart</a>
        </div>

    </div>


    <div id="content"></div>


</div>

</body>
</html>