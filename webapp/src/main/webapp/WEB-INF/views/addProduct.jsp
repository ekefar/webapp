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
                $.post("/product/add", $('#product_form').serialize(), 'json');
            });
        });

    </script>

</head>
<body>
<div id="profile_settings">

    <form id="product_form" action="/product/add">
        <table>
            <tr>
                <td>
                    Name:
                </td>
                <td>
                    <input type="text" name="product" id="name" size="50" value=""/>
                </td>
            </tr>

            <tr>
                <td>
                    Category:
                </td>
                <td>
                    <select id="categories" name="category">
                        <c:forEach items="${categoryList}" var="category">
                            <option>${category.name}</option>
                        </c:forEach>
                    </select>
                </td>
            </tr>

            <tr>
                <td>
                    Unit:
                </td>
                <td>
                    <select id="units" name="unit">
                        <c:forEach items="${unitList}" var="unit">
                            <option >${unit.name}</option>
                        </c:forEach>
                    </select>
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