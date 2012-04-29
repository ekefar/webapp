<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
</head>
<body>
<form method="POST">
    <fieldset>
        <table>
            <tr>
                <td>Product name:</td>
                <td>
                    ${offer.product.name}
                </td>

            </tr>
            <tr>
                <td>Product category:</td>
                <td>
                    ${offer.product.category.name}
                </td>

            </tr>
            <tr>
                <td>Product unit:</td>
                <td>
                    ${offer.product.unit.name}
                </td>
            </tr>
            <tr>
                <td>Price:</td>
                <td>
                    ${offer.price}
                </td>
            </tr>
            <tr>
                <td>Amount:</td>
                <td>
                    ${offer.amount}
                </td>
            </tr>
            <tr>
                <td>Total:</td>
                <td>
                    ${offer.price * offer.amount}
                </td>
            </tr>
            <tr>
                <td>Description:</td>
                <td>
                    ${offer.description}
                </td>
            </tr>
        </table>
    </fieldset>

</form>
</body>
</html>