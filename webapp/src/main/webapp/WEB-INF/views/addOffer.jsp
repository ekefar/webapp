<%--
  Created by IntelliJ IDEA.
  User: alexander.serebriyan
  Date: 24.04.12
  Time: 18:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>


    <title>
        Add offer
    </title>

    <link type="text/css" href="../resources/css/flick/jquery-ui-1.8.18.custom.css" rel="stylesheet"/>
    <link type="text/css" href="../resources/css/style.css" rel="stylesheet"/>

    <script type="text/javascript" src="../resources/js/jquery-1.7.1.min.js"></script>
    <script type="text/javascript" src="../resources/js/jquery-ui-1.8.18.custom.min.js"></script>

    <script type="text/javascript">
        $(document).ready(function () {

            $("#add_btn").click(function () {
                $.post("/product/addOffer", $('#product_form').serialize());
            });

            $("#categories").change(fillProducts);

            fillCategories();
            fillProducts();
        });

        function fillCategories() {
            $.getJSON("/product/listCategories", function (result) {
                var categories = $("#categories");
                $.each(result, function () {
                    categories.append($("<option />").text(this.name));
                });
            });
        }

        function fillProducts() {
            var postData = "category=" + $("#categories").val();
            $.post("/product/listProducts", postData, function (result) {
                var products = $("#products");
                products.html("");
                $.each(result, function () {
                    products.append($("<option />").text(this.name));
                });
            }, 'json');
        }

    </script>

</head>
<body>
<div id="profile_settings">

    <form id="product_form" action="/product/addOffer">
        <table>
            <tr>
                <td>
                    Category:
                </td>
                <td>
                    <select name="category" id="categories" size="50"></select>
                </td>
            </tr>

            <tr>
                <td>
                    Product:
                </td>
                <td>
                    <select name="product" id="products" size="50"></select>
                </td>
            </tr>

            <tr>
                <td>
                    Price:
                </td>
                <td>
                    <input type="text" id="price" name="price"/>
                </td>
            </tr>
            <tr>
                <td>
                    Price:
                </td>
                <td>
                    <input type="text" id="amount" name="amount"/>
                </td>
            </tr>
            <tr>
                <td>
                    Price:
                </td>
                <td>
                    <textarea id="description" name="description" cols="50" rows="7"></textarea>
                </td>
            </tr>
            <tr>
                <td align="center" colspan="2">
                    <br/>
                    <button id="reset_btn" type="reset" class="navigation_btn">Reset</button>
                    <button id="add_btn" type="button" class="navigation_btn">Add</button>
                </td>
            </tr>
        </table>
    </form>
</div>

</body>
</html>