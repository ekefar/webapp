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

    </script>
</head>

<body>
<sf:form method="POST" modelAttribute="offer">
    <fieldset>
        <table>
            <tr>
                <td>
                    Price:
                </td>
                <td>
                    <input name="user.id" value="${offer.user.id}" type="hidden">
                    <input name="id" value="${offer.id}" type="hidden">
                    <input name="product.id" value="${offer.product.id}" type="hidden">
                    <sf:input path="price" size="10" />
                </td>
            </tr>
            <tr>
                <td>
                    Amount:
                </td>
                <td>
                    <sf:input path="amount" size="10"/>
                </td>
            </tr>
            <tr>
                <td>
                    Description:
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