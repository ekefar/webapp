<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title></title>
    <link type="text/css" href="../../resources/css/flick/jquery-ui-1.8.18.custom.css" rel="stylesheet"/>
    <link type="text/css" href="../../resources/css/style.css" rel="stylesheet"/>
    <script type="text/javascript" src="../../resources/js/jquery-1.7.1.min.js"></script>
    <script type="text/javascript" src="../../resources/js/jquery-ui-1.8.18.custom.min.js"></script>

    <script type="text/javascript">
        $(document).ready(function () {

            $("#offerNew").dialog(
                    {
                        autoOpen:false,
                        modal:true,
                        resizable:false,
                        draggable:false,
                        position:top,
                        width:400,
                        buttons:{
                            "Cancel":function () {
                                $(this).dialog("close");
                            },
                            "Add":function () {
                                var postData = $("#offerNew form").serialize();
                                $.post("/offer/add", postData, function (result) {
                                    $("#offer_table").append(
                                            "<tr>" +
                                                    "<td>" + result.product.name + "</td>" +
                                                    "<td>" + result.price + "</td>" +
                                                    "<td>" + result.amount + "</td>" +
                                                    "<td>" + result.description + "</td>" +
                                                    "<td><a id='details_" + result.id + "' name='" + result.id + "' class='edit'>Edit</a></td>" +
                                                    "</tr>"
                                    );
                                    $("#details_" + result.id).button();
                                    $("#cart_" + result.id).button();
                                }, 'json');

                                $(this).dialog("close");
                            }
                        }
                    });

            $("#offerEdit").dialog(
                    {
                        autoOpen:false,
                        modal:true,
                        resizable:false,
                        draggable:false,
                        position:top,
                        width:400,
                        buttons:{
                            "Cancel":function () {
                                $(this).dialog("close");
                            },
                            "Save":function () {
                                var postData = $("#offerEdit form").serialize();
                                $.post("/offer/add", postData, function (result) {

                                }, 'json');

                                $(this).dialog("close");
                            }
                        }
                    });


            $("#add_btn")
                    .click(
                    function () {
                        var url = "/offer/add";
                        var dialogDiv = $("#offerNew");
                        dialogDiv.load(url, function () {
                            dialogDiv.dialog("open");
                        });
                    })
                    .button();



            $("#offer_table .edit")
                    .live("click", function () {
                        var url = "/offer/edit/" + $(this).attr("name");
                        var dialogDiv = $("#offerEdit");
                        dialogDiv.load(url, function () {
                            dialogDiv.dialog("open");
                        });
                    })
                    .button();

            $("#cartView").button();
        });

    </script>
</head>
<body>

<table id="offer_table" border="1">

    <tr>
        <th>
            Product
        </th>
        <th>
            Price
        </th>
        <th>
            Amount
        </th>
        <th>
            Description
        </th>
    </tr>

    <c:forEach items="${offers}" var="offer">
        <tr>
            <td>${offer.product.name}</td>
            <td>${offer.price}</td>
            <td>${offer.amount}</td>
            <td>${offer.description}</td>

            <td>
                <a id="cart_${offer.id}" name="${offer.id}" class="edit">Edit</a>
            </td>

        </tr>
    </c:forEach>

</table>

<div>
    <button id="add_btn" type="button">Add new</button>
</div>

<div id="offerNew" title="New offer"></div>

<div id="offerEdit" title="Edit Offer"></div>

</body>
</html>