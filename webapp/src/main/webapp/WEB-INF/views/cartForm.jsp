<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>

    <script type="text/javascript">
        $(document).ready(function(){
            $("#offerDetails").load("/offer/view/" + ${offer.id});
        });
    </script>
</head>

<body>
<div id="offerDetails"></div>

<div id="cartItems">
    <input name="offer.id" type="hidden" value="${offer.id}">
    <input name="amount" type="text">
</div>


</body>
</html>