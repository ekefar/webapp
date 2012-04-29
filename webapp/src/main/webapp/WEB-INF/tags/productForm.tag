<%@ attribute name="formSend" required="true" %>
<%@ attribute name="product" required="true" type="com.teamdev.webapp1.model.product.Product" %>
<%@ attribute name="categories" required="true" type="java.util.List<com.teamdev.webapp1.model.product.Category>" %>
<%@ attribute name="units" required="true" type="java.util.List<com.teamdev.webapp1.model.product.Unit>" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

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

