<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title></title>

    <script type="text/javascript">

        function validateOrders(){
            $("#orders tr").each(function(){
                var amount = parseInt($(this).find(".amount").text());
                var available = parseInt($(this).find(".available").text());
                var confirmButton = $(this).find(".confirm");
                if(amount>available){
                    $(confirmButton).attr("disabled", true).addClass("ui-button-disabled ui-state-disabled");
                    canPurchaseAll = false;
                } else {
                    $(confirmButton).attr("disabled", false).removeClass("ui-button-disabled ui-state-disabled")
                }
            });
        }
    </script>

    <script type="text/javascript">
        validateOrders();

        $("#orders .confirm")
                .unbind("click")
                .click(function () {
                    var selectedRecordId = $(this).attr("name");
                    var url = "/order/confirm/" + selectedRecordId;
                    $.post(url, function (result) {
                        $("#record_" + selectedRecordId).remove();
                        $("#order_view").load("/order/processing/${userId}");
                    });
                })
                .button();

        $("#orders .deny")
                .unbind("click")
                .click(function () {
                    var selectedRecordId = $(this).attr("name")
                    var url = "/order/deny/" + selectedRecordId;
                    $.post(url, function (result) {
                        $("#record_" + selectedRecordId).remove();
                    }, 'json');
                })
                .button();


    </script>
</head>

<body>
<div id="#order_view">
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
                Creation date
            </th>
            <th>
                State
            </th>
        </tr>

        <c:forEach items="${orders}" var="order">
            <tr id="record_${order.id}">
                <td>${order.offer.product.name}</td>
                <td>${order.offer.price}</td>
                <td class="available">${order.offer.amount}</td>
                <td class="amount">${order.amount}</td>
                <td class="total">${order.amount * order.offer.price}</td>
                <td>${order.creationDate}</td>
                <td class="state">${order.state}</td>
                <td>
                    <button id="confirm_${order.id}" name="${order.id}" class="confirm">Confirm</button>
                </td>
                <td>
                    <a id="deny_${order.id}" name="${order.id}" class="deny">Deny</a>
                </td>
            </tr>
        </c:forEach>

    </table>
</div>
</body>
</html>