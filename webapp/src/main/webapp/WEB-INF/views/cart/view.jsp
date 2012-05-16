<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title></title>

    <script type="text/javascript">
        var selectedRecordId;

        function validateCart() {
            var canPurchaseAll = true;
            $("#cart tr").each(function () {
                var amount = parseInt($(this).find(".amount").text());
                var available = parseInt($(this).find(".available").text());
                var purchaseButton = $(this).find(".purchase");
                if (amount > available) {
                    $(purchaseButton).attr("disabled", true).addClass("disabled").removeClass("button");
                    canPurchaseAll = false;
                } else {
                    $(purchaseButton).attr("disabled", false).removeClass("disabled").addClass("button");
                }
            });

            var purchaseAllButton = $("#purchase_all_btn");

            if (!canPurchaseAll) {
                $(purchaseAllButton).attr("disabled", true).addClass("disabled").removeClass("button");
            } else {
                $(purchaseAllButton).attr("disabled", false).removeClass("disabled").addClass("button");
            }
        }

        function convertData(data) {

            var rows = Array();

            $.each(data.rows, function (i, row) {

                rows.push({
                    id:row.id,
                    cell:[row.offer.product.name,
                        row.offer.price,
                        row.offer.amount,
                        row.amount,
                        row.amount * row.offer.price,
                        "<button class='edit button' name='" + row.id + "'>Изменить</button>" +
                                "<button class='remove button' name='" + row.id + "'>Удалить</button>" +
                                "<button class='purchase button' name='" + row.id + "'>Заказать</button>"]});
            });

            return {
                total:data.total,
                page:data.page,
                rows:rows
            };
        }


        $("#cart").flexigrid({
            url:'/cart/view/paging/${cartId}',
            dataType:'json',
            preProcess:convertData,
            colModel:[
                {display:'Товар', name:'offer.product.name', width:150, sortable:true, align:'center'},
                {display:'Цена', name:'offer.price', width:50, sortable:true, align:'left'},
                {display:'Доступно', name:'offer.amount', width:70, sortable:true, align:'left', colClass:'available'},
                {display:'Заказано', name:'amount', width:70, sortable:true, align:'left', colClass:'amount'},
                {display:'Сумма', width:100, sortable:false, align:'left'},
                {display:'Действие', name:'state', width:300, sortable:true, align:'left'}

            ],
            searchitems:[
                {display:'Товар', name:'offer.product.name'}
            ],
            sortname:"amount",
            sortorder:"ASC",
            usepager:true,
            title:'',
            useRp:true,
            rp:15,
            width:980,
            height:530,
            singleSelect:true,
            onSuccess:function () {
                $("#cart").each(function () {
                    $("td[abbr='amount']").addClass("amount");
                    $("td[abbr='offer.amount']").addClass("available");
                });
                validateCart();
            }
        });

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
                                $("#cart").flexReload();
                            }, 'json');

                            $(this).dialog("close");
                        }
                    }
                }
        );

        $("#cart .edit")
                .die()
                .live("click", function () {
                    var url = "/cart/edit/" + $(this).attr("name");
                    selectedRecordId = $(this).attr("name");
                    var dialogDiv = $("#cartEdit");
                    dialogDiv.load(url, function () {
                        dialogDiv.dialog("open");
                    });
                });

        $("#cart .remove")
                .die()
                .live("click", function () {
                    var recordId = $(this).attr("name");
                    var url = "/cart/remove";
                    var postData = "id=" + recordId;
                    $.post(url, postData, function (result) {
                        $("#row" + result.id).remove();
                    }, 'json');
                });

        $("#cart .purchase")
                .die()
                .live("click", function () {
                    var recordId = $(this).attr("name");
                    var url = "/cart/purchase";
                    var postData = "id=" + recordId;
                    $.post(url, postData, function (result) {
                        $("#cart").flexReload();
                    }, 'json');
                });

        $("#purchase_all_btn")
                .unbind("click")
                .click(function () {
                    var postData = "cartId=" + ${cartId};
                    $.post("/cart/purchaseAll", postData, function (result) {
                        $("#cart").flexReload();
                    }, 'json');

                });
    </script>

</head>

<body>

<!-- Caption Line -->
<h2 class="grid_12 caption">Корзина заказов</h2>
<table id="cart" style="display: none"></table>
<div>
    <button id="purchase_all_btn" class="button right">Заказать все</button>
</div>

<div id="cartEdit" title="Edit cart record"></div>

</body>
</html>