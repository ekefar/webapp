<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tags" %>

<html>
<head>
    <title>
        Add offer
    </title>

    <script type="text/javascript">
        $(document).ready(function () {
            $("#categories").change(fillProducts);
            $("#categories").change();

        });


        function fillProducts() {
            var postData = "id=" + $("#categories").val();
            $.post("/offer/listProducts", postData, function (result) {
                var products = $("#products");
                products.html("");
                $.each(result, function () {
                    products.append($("<option />")
                            .attr("value", this.id)
                            .text(this.name));
                });
            }, 'json');
        }

    </script>
</head>

<body>
    <sf:form method="POST" modelAttribute="offer">
        <fieldset>
            <table>
                <tr>
                    <td>Категория:</td>
                    <td>
                        <sf:select path="product.category.id" id="categories" items="${categoryList}" itemValue="id"
                                   itemLabel="name">
                        </sf:select>
                        <input name="user.id" value="${user.id}" type="hidden">
                    </td>

                </tr>
                <tr>
                    <td>Продукт:</td>
                    <td><sf:select path="product.id" id="products" itemValue="id" itemLabel="name">
                    </sf:select>
                    </td>
                </tr>
                <tr>
                    <td>
                        Цена:
                    </td>
                    <td>
                        <sf:input path="price" size="10" />
                    </td>
                </tr>
                <tr>
                    <td>
                        Количество:
                    </td>
                    <td>
                        <sf:input path="amount" size="10"/>
                    </td>
                </tr>
                <tr>
                    <td>
                        Описание:
                    </td>
                    <td>
                        <sf:textarea path="description" size="10"/>
                    </td>
                </tr>
            </table>
        </fieldset>
    </sf:form>
</body>
</html>