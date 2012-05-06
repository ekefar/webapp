<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title></title>
    <script type="text/javascript">


        function validateCart(){
            var canPurchaseAll = true;
            $("#cart_records tr").each(function(){
                var amount = parseInt($(this).find(".amount").text());
                var available = parseInt($(this).find(".available").text());
                var purchaseButton = $(this).find(".purchase");
                if(amount>available){
                    $(purchaseButton).attr("disabled", true).addClass("ui-button-disabled ui-state-disabled");
                    canPurchaseAll = false;
                } else {
                    $(purchaseButton).attr("disabled", false).removeClass("ui-button-disabled ui-state-disabled")
                }
            });

            var purchaseAllButton =  $("#purchase_all_btn");

            if(!canPurchaseAll){
               $(purchaseAllButton).attr("disabled", true).addClass("ui-button-disabled ui-state-disabled");
            }   else {
                $(purchaseAllButton).attr("disabled", false).removeClass("ui-button-disabled ui-state-disabled");
            }
        }
    </script>

    <script type="text/javascript">

        var selectedRecordId;

        $("#cartEdit").dialog(
                {
                    autoOpen:false,
                    modal:true,
                    resizable:false,
                    draggable:false,
                    position:top,
                    width:400,
                    buttons:{
                        "Close":function () {
                            $(this).dialog("close");
                        },
                        "Save":function () {
                            var postData = $("#cartEdit form").serialize();
                            $.post("/cart/edit", postData, function (result) {
                                $("#record_" + selectedRecordId + " .amount").html(result.amount);
                                var total = parseInt(result.offer.price) * parseInt(result.amount);
                                $("#record_" + selectedRecordId + " .total").html(total);
                                validateCart();
                            }, 'json');

                            $(this).dialog("close");
                        }
                    }
                }
        );

        $("#cart_records .edit")
                .die()
                .live("click", function () {
                    var url = "/cart/edit/" + $(this).attr("name");
                    selectedRecordId = $(this).attr("name");
                    var dialogDiv = $("#cartEdit");
                    dialogDiv.load(url, function () {
                        dialogDiv.dialog("open");
                    });
                })
                .button();

        $("#cart_records .remove")
                .die()
                .live("click", function () {
                    var recordId = $(this).attr("name");
                    var url = "/cart/remove";
                    var postData = "id=" + recordId;
                    $.post(url, postData);
                    $("#record_" + recordId).remove();
                })
                .button();

        $("#cart_records .purchase")
                .die()
                .live("click", function () {
                    var recordId = $(this).attr("name");
                    var url = "/cart/purchase";
                    var postData = "id=" + recordId;
                    $.post(url, postData);
                    $("#record_" + recordId).remove();
                })
                .button();

        $("#purchase_all_btn")
                .unbind("click")
                .click(function () {
                    var postData = "cartId=" + ${cart.id};
                    $.post("/cart/purchaseAll", postData);
                    $("#cart_records").html("Cart is empty");
                })
                .button();

        validateCart();
    </script>


</head>

<body>
<table id="cart_records" border="1">

    <c:if test="${cart.items == null}" >
        <tr>cart is empty</tr>
    </c:if>

    <tr>
        <th>
            Product
        </th>
        <th>
            Price
        </th>
        <th>
            Available
        </th>
        <th>
            Purchased
        </th>
        <th>
            Total
        </th>
    </tr>

    <c:forEach items="${cart.items}" var="cartRecord">
        <tr id="record_${cartRecord.id}">
            <td>${cartRecord.offer.product.name}</td>
            <td>${cartRecord.offer.price}</td>
            <td class="available">${cartRecord.offer.amount}</td>
            <td class="amount">${cartRecord.amount}</td>
            <td class="total">${cartRecord.amount * cartRecord.offer.price}</td>
            <td>
                <a id="edit_${cartRecord.id}" name="${cartRecord.id}" class="edit">Edit record</a>
            </td>
            <td>
                <a id="remove_${cartRecord.id}" name="${cartRecord.id}" class="remove">Remove record</a>
            </td>
            <td>
                <button id="purchase_${cartRecord.id}" name="${cartRecord.id}" class="purchase">Purchase</button>
            </td>
        </tr>
    </c:forEach>

</table>
<div>
    <button id="purchase_all_btn">Purchase all</button>
</div>
<div id="cartEdit" title="Edit cart record"></div>

</body>
</html>