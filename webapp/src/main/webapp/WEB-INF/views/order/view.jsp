<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title></title>

    <script type="text/javascript">

        $("#confirmed_btn")
                .unbind("click")
                .click(function () {
                    $("#order_view").load("/order/confirmed/" + $(this).attr("name"));
                })
                .button();

        $("#processing")
                .unbind("click")
                .click(function () {
                    $("#order_view").load("/order/processing/" + $(this).attr("name"));
                })
                .button();

    </script>
</head>
<body>
<div>
    <button id="confirmed_btn" name="${userId}">Confirmed</button>
    <button id="processing" name="${userId}">Processing</button>
</div>

<div id="order_view"></div>
</body>
</html>