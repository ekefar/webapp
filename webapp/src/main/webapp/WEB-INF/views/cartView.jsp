<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title></title>
</head>
<body>
<table id="offer_table" border="1">

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
    </tr>

    <c:forEach items="${details}" var="cartRecord">
        <tr>
            <td>${cartRecord.offer.product.name}</td>
            <td>${cartRecord.offer.price}</td>
            <td>${cartRecord.offer.amount}</td>
            <td>${cartRecord.amount}</td>
            <td>
                <a id="details_${cartRecord.id}" name="${cartRecord.id}" class="edit">Edit record</a>
            </td>
        </tr>
    </c:forEach>

</table>
</body>
</html>