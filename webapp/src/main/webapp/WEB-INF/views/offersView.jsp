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

            $("#cartForm").dialog(
                    {
                        autoOpen:false,
                        modal:true,
                        resizable:false,
                        draggable:false,
                        position:top,
                        buttons:{
                            "Close":function () {
                                $(this).dialog("close");
                            },
                            "Add":function () {
                                var postData = $("#cartForm *").serialize() + "&cart.id=" +${cart.id};
                                $.post("/cart/add", postData);
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
                                                    "<td><a id='details_" + result.id + "' name='"+ result.id  +"' class='details'>View details</a></td>" +
                                                    "<td><a id='cart_" + result.id + "' name='"+ result.id  +"' class='cart'>Add to cart</a></td>" +
                                                    "</tr>"
                                    );
                                    $("#details_"+result.id).button();
                                    $("#cart_"+result.id).button();
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

            $("#offer_table .details")
                    .live("click", function () {
                        var url = "/offer/view/" + $(this).attr("name");
                        var dialogDiv = $("#offerDescription");
                        dialogDiv.load(url, function () {
                            dialogDiv.dialog("open");
                        });
                    })
                    .button();

            $("#offer_table .cart")
                    .live("click", function () {
                        var url = "/cart/add/" + $(this).attr("name");
                        var dialogDiv = $("#cartForm");
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
                <a id="details_${offer.id}" name="${offer.id}" class="details">View details</a>
            </td>
            <td>
                <a id="cart_${offer.id}" name="${offer.id}" class="cart">Add to cart</a>
            </td>
        </tr>
    </c:forEach>

</table>

<div>
    <button id="add_btn" type="button">Add new</button>
    <a href="/cart/view/${cart.id}">View cart</a>
</div>

<div id="offerDescription" title="Offer info"></div>

<div id="offerForm" title="New offer"></div>

<div id="cartForm" title="Add to cart"></div>

</body>
</html>