<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title></title>
</head>
<body>
<form method="POST">
    <fieldset>
        <table>
            <tr>
                <td>Available:</td>
                <td>
                    ${record.offer.amount}

                    <input name="offer.id" value="${record.offer.id}" type="hidden">
                    <input name="id" value="${record.id}" type="hidden">
                    <input name="cart.id" value="${record.cart.id}" type="hidden">
                </td>

            </tr>
            <tr>
                <td>Price:</td>
                <td>
                    ${record.offer.price}
                </td>

            </tr>
            <tr>
                <td>Purchased:</td>
                <td>
                    ${record.amount}
                </td>
            </tr>
            <tr>
                <td>Change quantity:</td>
                <td>
                    <input type="text" name="amount">
                </td>
            </tr>
        </table>
    </fieldset>

</form>
</body>
</html>