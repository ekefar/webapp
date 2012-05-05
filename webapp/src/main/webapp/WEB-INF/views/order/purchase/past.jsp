<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title></title>
    <script type="text/javascript">


        $("#orders .remove")
                .unbind("click")
                .click(function () {
                    var selectedRecordId = $(this).attr("name")
                    var url = "/order/delete/" + selectedRecordId;
                    $.post(url, function(result){
                        $("#record_"+selectedRecordId).remove();
                    }, 'json');
                })
                .button();


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
            <td>${order.offer.amount}</td>
            <td class="amount">${order.amount}</td>
            <td class="total">${order.amount * order.offer.price}</td>
            <td>${order.creationDate}</td>
            <td class="state">${order.state}</td>
        </tr>
    </c:forEach>
</table>

</body>
</html>