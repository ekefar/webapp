<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title></title>

     <script type="text/javascript">

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
                        var url = "/offer/add";
                        $.post(url, postData, function (result) {
                            $("#offer_table").append(
                                    "<tr id='offer_" + result.id+ "'>" +
                                            "<td>" + result.product.name + "</td>" +
                                            "<td>" + result.price + "</td>" +
                                            "<td>" + result.amount + "</td>" +
                                            "<td>" + result.description + "</td>" +
                                            "<td><a id='edit_" + result.id + "' name='" + result.id + "' class='edit'>Edit</a></td>" +
                                            "<td><a id='remove_" + result.id + "' name='" + result.id + "' class='remove'>Remove</a></td>" +
                                            "</tr>"
                            );
                            $("#edit_" + result.id).button();
                            $("#remove_" + result.id).button();
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
            .unbind("click")
            .click(
            function () {
                var url = "/offer/add/" + ${userId};
                var dialogDiv = $("#offerNew");
                dialogDiv.load(url, function () {
                    dialogDiv.dialog("open");
                });
            })
            .button();


    $("#offer_table .edit")
            .die()
            .live("click", function () {
                var url = "/offer/edit/" + $(this).attr("name");
                var dialogDiv = $("#offerEdit");
                dialogDiv.load(url, function () {
                    dialogDiv.dialog("open");
                });
            })

            .button();

    $("#offer_table .remove")
            .die()
            .live("click", function () {
                var id = $(this).attr("name");
                var url = "/offer/deactivate";
                var postData = "id=" + id;
                $.post(url,postData, function(result){
                    $("#offer_" + result).remove();
                }) ;
            })

            .button();

    $("#cartView").button();

</script>
</head>
<body>
<div>
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
            <tr id="offer_${offer.id}">
                <td>${offer.product.name}</td>
                <td>${offer.price}</td>
                <td>${offer.amount}</td>
                <td>${offer.description}</td>

                <td>
                    <a id="edit_${offer.id}" name="${offer.id}" class="edit">Edit</a>
                </td>
                <td>
                    <a id="remove_${offer.id}" name="${offer.id}" class="remove">Remove</a>
                </td>

            </tr>
        </c:forEach>
    </table>
    <button id="add_btn" type="button">Add new</button>
</div>

<div id="offerNew" title="New offer"></div>

<div id="offerEdit" title="Edit Offer"></div>
<br>
<br>
<br>

<table id="flex1" style="display: none"></table>

<script type="text/javascript">
    $("#flex1").flexigrid({
        url: '/offer/own/json/${userId}',
        dataType: 'json',
        colModel : [
            {display: 'id', name : 'id', width : 40, sortable : true, align: 'center'},
            {display: 'product', name : 'product.name', width : 40, sortable : true, align: 'center'},
            {display: 'price', name : 'price', width : 180, sortable : true, align: 'left'},
            {display: 'amount', name : 'amount', width : 120, sortable : true, align: 'left'},
            {display: 'description', name : 'description', width : 130, sortable : true, align: 'left'}
        ],
        searchitems : [
            {display: 'product', name : 'product.name'},
            {display: 'price', name : 'price'}
        ],
        sortname: "price",
        sortorder: "ASC",
        usepager: true,
        title: 'Offers',
        useRp: true,
        rp: 15,
        showTableToggleBtn: true,
        width: 700,
        height: 200
    });


</script>
</body>
</html>