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
                })
                .button();

        $("#processing")
                .unbind("click")
                .click(function () {
                    $("#order_view").load("/order/processing/${userId}");
                })
                .button();

        $("#processing").click();

    </script>
</head>
<body>
<div>
    <button id="processing">Processing</button>
    <button id="confirmed_btn">Confirmed</button>

</div>

<div id="order_view"></div>
</body>
</html>