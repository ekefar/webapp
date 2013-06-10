<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title></title>

    <script type="text/javascript">

        $("#total_btn")
                .unbind("click")
                .click(function () {
                    $("#report_view").load("/report/total/${userId}");
                });

        $("#categories_btn")
                .unbind("click")
                .click(function () {
                    $("#report_view").load("/report/categories/${userId}");
                });

        $("#categories_bar_btn")
                .unbind("click")
                .click(function () {
                    $("#report_view").load("/report/categories/bar/${userId}");
                });


        $("#total_btn").click();

    </script>
</head>
<body>
<div>
    <!-- Caption Line -->

    <ul class="grid_12">
        <li><a href="#reports" id="total_btn">Общий</a></li>
        <li><a href="#reports" id="categories_btn">Все по категориям</a></li>
        <li><a href="#reports" id="categories_bar_btn">Топ категорий</a></li>
    </ul>

    <div id="report_view" class="grid_12 "></div>
</div>

</body>
</html>