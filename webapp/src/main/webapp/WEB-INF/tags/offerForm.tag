<%@ attribute name="formSend" required="true" %>
<%@ attribute name="offer" required="true" type="com.teamdev.webapp1.model.order.Offer" %>
<%@ attribute name="user" required="true" type="com.teamdev.webapp1.model.user.User" %>
<%@ attribute name="categories" required="true" type="java.util.List<com.teamdev.webapp1.model.product.Category>" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<script type="text/javascript">
    $(document).ready(function () {
        $("#categories").change(fillProducts);
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

<sf:form method="POST" modelAttribute="offer" action="${formSend}">
    <fieldset>
        <table>
            <tr>
                <td>Category:</td>
                <td>
                    <sf:select path="product.category.id" id="categories" items="${categories}" itemValue="id"
                               itemLabel="name">
                    </sf:select>
                    <input name="user.id" value="${user.id}" type="hidden">
                </td>
                
            </tr>
            <tr>
                <td>Product:</td>
                <td><sf:select path="product.id" id="products" itemValue="id" itemLabel="name">
                </sf:select>
                </td>
            </tr>
            <tr>
                <td>
                    Price:
                </td>
                <td>
                    <sf:input path="price" size="10"/>
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
                    Amount:
                </td>
                <td>
                    <sf:textarea path="description" size="10"/>
                </td>
            </tr>
            <tr>
                <td><input type="reset" value="Reset"/></td>
                <td><input type="submit" value="Add"/></td>
            </tr>
        </table>
    </fieldset>

</sf:form>

