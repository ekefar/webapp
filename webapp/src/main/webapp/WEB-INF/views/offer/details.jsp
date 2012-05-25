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
                <td>Наименование:</td>
                <td>
                    ${offer.product.name}
                </td>

            </tr>
            <tr>
                <td>Категория:</td>
                <td>
                    ${offer.product.category.name}
                </td>

            </tr>
            <tr>
                <td>Ед. измерения:</td>
                <td>
                    ${offer.product.unit.name}
                </td>
            </tr>
            <tr>
                <td>Цена:</td>
                <td>
                    ${offer.price}
                </td>
            </tr>
            <tr>
                <td>Количество:</td>
                <td>
                    ${offer.amount}
                </td>
            </tr>
            <tr>
                <td>Сумма:</td>
                <td>
                    ${offer.price * offer.amount}
                </td>
            </tr>
            <tr>
                <td>Описание:</td>
                <td>
                    ${offer.description}
                </td>
            </tr>
        </table>
    </fieldset>

</form>
</body>
</html>