<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title></title>
    <link type="text/css" href="../../resources/css/flick/jquery-ui-1.8.18.custom.css" rel="stylesheet"/>
    <link type="text/css" href="../../resources/css/style.css" rel="stylesheet"/>
    <script type="text/javascript" src="../../resources/js/jquery-1.7.1.min.js"></script>
    <script type="text/javascript" src="../../resources/js/jquery-ui-1.8.18.custom.min.js"></script>

    <script type="text/javascript">
        $(document).ready(function () {
            var selectedRecordId;

            $("#orders .confirm")
                    .click(function () {
                        selectedRecordId = $(this).attr("name")
                        var url = "/order/confirm/" + selectedRecordId;
                        $.post(url, function(result){
                            $("#record_"+selectedRecordId + " .state").html(result.state);
                        }, 'json');
                    })
                    .button();

            $("#orders .deny")
                    .click(function () {
                        selectedRecordId = $(this).attr("name")
                        var url = "/order/deny/" + selectedRecordId;
                        $.post(url, function(result){
                            $("#record_"+selectedRecordId + " .state").html(result.state);
                        }, 'json');
                    })
                    .button();

        });
    </script>
</head>

<body>
<table id="orders" border="1">

    <tr>
        <th>
            Product
        </th>
        <th>
            Price
        </th>
        <th>
            Available
        </th>
        <th>
            Purchased
        </th>
        <th>
            Total
        </th>
        <th>
            State
        </th>
    </tr>

    <c:forEach items="${orders}" var="order">
        <tr id="record_${order.id}">
            <td>${order.offer.product.name}</td>
            <td>${order.offer.price}</td>
            <td>${order.offer.amount}</td>
            <td class="amount">${order.amount}</td>
            <td class="total">${order.amount * order.offer.price}</td>
            <td class="state">${order.state}</td>
            <td>
                <a id="confirm_${order.id}" name="${order.id}" class="confirm">Confirm</a>
            </td>
            <td>
                <a id="deny_${order.id}" name="${order.id}" class="deny">Deny</a>
            </td>
        </tr>
    </c:forEach>
    <input id="cartId" type="hidden" name="id" value="${cart.id}">
</table>
</body>
</html>