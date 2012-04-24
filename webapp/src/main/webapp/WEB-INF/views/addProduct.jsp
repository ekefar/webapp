<%--
  Created by IntelliJ IDEA.
  User: alexander.serebriyan
  Date: 24.04.12
  Time: 18:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
            fillCategories();
            fillUnits();

            $("#add_btn").click(function () {
                $.post("/product/add", JSON.stringify($("#profile_form").serializeObject()), 'json');
                return false;
            });
        });

        $.fn.serializeObject = function () {
            var o = {};
            var a = this.serializeArray();
            $.each(a, function () {
                if (o[this.name] !== undefined) {
                    if (!o[this.name].push) {
                        o[this.name] = [o[this.name]];
                    }
                    o[this.name].push(this.value || '');
                } else {
                    o[this.name] = this.value || '';
                }
            });
            return o;
        };

        function fillCategories() {
            $.getJSON("/product/listCategories", function (result) {
                var categories = $("#categories");
                $.each(result, function () {
                    categories.append($("<option />").text(this.name));
                });
            });
        }

        function fillUnits() {
            $.getJSON("/product/listUnits", function (result) {
                var units = $("#units");
                $.each(result, function () {
                    units.append($("<option />").text(this.name));
                });
            });
        }

    </script>

</head>
<body>
<div id="profile_settings">

    <form id="profile_form">
        <table>
            <tr>
                <td>
                    Name:
                </td>
                <td>
                    <input type="text" name="name" id="name" size="50" value=""/>
                </td>
            </tr>

            <tr>
                <td>
                    Category:
                </td>
                <td>
                    <select id="categories" name="category"></select>
                </td>
            </tr>

            <tr>
                <td>
                    Unit:
                </td>
                <td>
                    <select id="units" name="unit"></select>
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