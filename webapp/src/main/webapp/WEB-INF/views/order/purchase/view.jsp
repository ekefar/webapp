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
                })
                .button();

        $("#past_btn")
                .unbind("click")
                .click(function () {
                    $("#order_view").load("/order/purchase/past/${userId}");
                })
                .button();

        $("#active_btn").click();

    </script>
</head>
<body>
<div>
    <button id="active_btn" >Active</button>
    <button id="past_btn" >Completed</button>
</div>

<div id="order_view"></div>
</body>
</html>