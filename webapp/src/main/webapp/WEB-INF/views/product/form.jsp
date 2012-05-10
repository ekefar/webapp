<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
</head>
<body>
<sf:form method="POST" modelAttribute="product" action="${formSend}">
    <fieldset>
        <table>
            <tr>
                <td>Name:</td>
                <td><sf:input path="name" size="10"/></td>
            </tr>
            <tr>
                <td>Category:</td>
                <td><sf:select path="category.id" items="${categories}" itemValue="id" itemLabel="name" >
                </sf:select></td>
            </tr>
            <tr>
                <td>Unit:</td>
                <td><sf:select path="unit.id" items="${units}" itemValue="id" itemLabel="name">
                </sf:select>
                </td>
            </tr>
            <tr>
                <td><input type="reset" value="Reset"/></td>
                <td><input type="submit" value="Add"/></td>
            </tr>
        </table>
    </fieldset>
</sf:form>
</body>
</html>