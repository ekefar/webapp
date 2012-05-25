<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tags" %>
<html>
<head>
    <title></title>
</head>
<body>
<sf:form method="POST" modelAttribute="product">
    <fieldset>
        <table>
            <tr>
                <td>Категория:</td>
                <td><sf:select path="category.id"  id="categories" items="${categories}" itemValue="id" itemLabel="name" >
                </sf:select></td>
            </tr>
            <tr>
                <td>Единица измерения:</td>
                <td><sf:select path="unit.id" items="${units}" itemValue="id" itemLabel="name">
                </sf:select>
                </td>
            </tr>
            <tr>
                <td>Наименование:</td>
                <td><sf:input path="name" size="10"/></td>
            </tr>
        </table>
    </fieldset>
</sf:form>
</body>
</html>