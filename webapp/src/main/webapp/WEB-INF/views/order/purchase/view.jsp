<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title></title>

    <script type="text/javascript">

        $("#active_btn")
                .unbind("click")
                .click(function () {
                    $("#order_view").load("/order/purchase/active/${userId}");
                });

        $("#past_btn")
                .unbind("click")
                .click(function () {
                    $("#order_view").load("/order/purchase/past/${userId}");
                });

        $("#active_btn").click();
    </script>
</head>
<body>
<div>
    <!-- Caption Line -->

    <div class="grid_3">
        <h4 class="title ">Категория</h4>
        <div class="hr dotted">&nbsp;</div>
        <ul class="sidebar">
            <li><a href="#my_purchases" id="active_btn">Активные</a></li>
            <li><a href="#my_purchases" id="past_btn">Завершенные</a></li>
        </ul>
    </div>
    <div id="order_view" class="grid_7 "></div>
</div>


</body>
</html>