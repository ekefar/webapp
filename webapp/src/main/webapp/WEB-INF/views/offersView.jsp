<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title></title>
    <link type="text/css" href="../resources/css/flick/jquery-ui-1.8.18.custom.css" rel="stylesheet"/>
    <link type="text/css" href="../resources/css/style.css" rel="stylesheet"/>
    <script type="text/javascript" src="../resources/js/jquery-1.7.1.min.js"></script>
    <script type="text/javascript" src="../resources/js/jquery-ui-1.8.18.custom.min.js"></script>

    <script type="text/javascript">
        $(document).ready(function () {
            $("#offerDescription").dialog(
                    {
                        autoOpen:false,
                        modal:true,
                        resizable:false,
                        draggable:false,
                        position:top,
                        buttons:{
                            "Close":function () {
                                $(this).dialog("close");
                            }
                        }
                    }
            );

            $("#offerForm").dialog(
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
                                var postData = $("#offerForm form").serialize();
                                $.post("/offer/add", postData, function (result) {
                                    $("#offer_table").append(
                                            "<tr>" +
                                                    "<td>" + result.product.name + "</td>" +
                                                    "<td>" + result.price + "</td>" +
                                                    "<td>" + result.amount + "</td>" +
                                                    "<td><a id='" + result.id + "'>View details</a></td>" +
                                                    "</tr>"
                                    );
                                    $("#"+result.id).button();
                                }, 'json');

                                $(this).dialog("close");
                            }
                        }
                    });

            $("#add_btn")
                    .click(
                    function () {
                        var url = "/offer/add";
                        var dialogDiv = $("#offerForm");
                        dialogDiv.load(url, function () {
                            dialogDiv.dialog("open");
                        });
                    })
                    .button();

            $("#offer_table a")
                    .live("click", function () {
                        var url = "/offer/view/" + $(this).attr("id");
                        var dialogDiv = $("#offerDescription");
                        dialogDiv.load(url, function () {
                            dialogDiv.dialog("open");
                        });
                    })
                    .button();
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
    </tr>

    <c:forEach items="${offers}" var="offer">
        <tr>
            <td>${offer.product.name}</td>
            <td>${offer.price}</td>
            <td>${offer.amount}</td>
            <td>
                <a id="${offer.id}">View details</a>
            </td>
        </tr>
    </c:forEach>

</table>

<div>
    <button id="add_btn" type="button">Add new</button>
</div>

<div id="offerDescription" title="Offer info">

</div>

<div id="offerForm" title="New offer">
</div>

</body>
</html>