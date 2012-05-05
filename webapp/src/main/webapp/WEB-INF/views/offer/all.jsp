<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title></title>

    <script type="text/javascript">

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
                            var postData = $("#cartForm *").serialize() + "&cart.id=" +${cartId};
                            $.post("/cart/add", postData);
                            $(this).dialog("close");
                        }
                    }
                }
        );


        $("#offer_table .details")
                .die()
                .live("click", function () {
                    var url = "/offer/view/" + $(this).attr("name");
                    var dialogDiv = $("#offerDescription");
                    dialogDiv.load(url, function () {
                        dialogDiv.dialog("open");
                    });
                })
                .button();

        $("#offer_table .cart")
                .die()
                .live("click", function () {
                    var url = "/cart/add/" + $(this).attr("name");
                    var dialogDiv = $("#cartForm");
                    dialogDiv.load(url, function () {
                        dialogDiv.dialog("open");
                    });
                })
                .button();


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
                <a id="details_${offer.id}" name="${offer.id}" class="details">View details</a>
            </td>
            <td>
                <a id="cart_${offer.id}" name="${offer.id}" class="cart">Add to cart</a>
            </td>

        </tr>
    </c:forEach>

</table>

<div id="offerDescription" title="Offer info"></div>

<div id="cartForm" title="Add to cart"></div>

</body>
</html>