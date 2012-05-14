<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title></title>

    <script type="text/javascript">

        $("#confirmed_btn")
                .unbind("click")
                .click(function () {
                    $("#order_view").load("/order/confirmed/${userId}");
                });

        $("#processing")
                .unbind("click")
                .click(function () {
                    $("#order_view").load("/order/processing/${userId}");
                });

        $("#processing").click();

    </script>
</head>
<body>
<div>
    <div class="grid_3">
        <h4 class="title ">Категория</h4>

        <div class="hr dotted">&nbsp;</div>
        <ul class="sidebar">
            <li><a href="#orders" id="processing">Активные</a></li>
            <li><a href="#orders" id="confirmed_btn">Завершенные</a></li>
        </ul>
    </div>
    <div id="order_view" class="grid_7 "></div>
</div>

</body>
</html>