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
                                $.post("/cart/edit", postData, function(result){
                                    $("#record_"+selectedRecordId + " .amount").html(result);
                                }, 'json');

                                $(this).dialog("close");
                            }
                        }
                    }
            );

            $("#cart_records .edit")
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
                    .live("click", function () {
                        var url = "/cart/add/" + $(this).attr("name");
                    })
                    .button();
        });
    </script>
</head>

<body>
<table id="cart_records" border="1">

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

    <c:forEach items="${details}" var="cartRecord">
        <tr id="record_${cartRecord.id}">
            <td>${cartRecord.offer.product.name}</td>
            <td>${cartRecord.offer.price}</td>
            <td>${cartRecord.offer.amount}</td>
            <td class="amount">${cartRecord.amount}</td>
            <td>${cartRecord.amount * cartRecord.offer.price}</td>
            <td>
                <a id="edit_${cartRecord.id}" name="${cartRecord.id}" class="edit">Edit record</a>
            </td>
            <td>
                <a id="remove_${cartRecord.id}" name="${cartRecord.id}" class="remove">Remove record</a>
            </td>
        </tr>
    </c:forEach>

</table>

<div id="cartEdit"></div>
</body>
</html>