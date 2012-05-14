<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title></title>

    <script type="text/javascript">

        function validateOrders() {
            $("#orders tr").each(function () {
                var amount = parseInt($(this).find(".amount").text());
                var available = parseInt($(this).find(".available").text());
                var confirmButton = $(this).find(".confirm");
                if (amount > available) {
                    $(confirmButton).attr("disabled", true).addClass("disabled").removeClass("button");
                } else {
                    $(confirmButton).attr("disabled", false).removeClass("disabled").addClass("button");
                }
            });
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
                        row.creationDate,
                        "<button class='confirm button' name='" + row.id + "'>Принять</button>" +
                                "<button class='deny button' name='" + row.id + "'>Отклонить</button>"]});
            });

            return {
                total:data.total,
                page:data.page,
                rows:rows
            };
        }

        $("#orders").flexigrid({
            url:'/order/active/paging/${userId}',
            dataType:'json',
            preProcess:convertData,
            colModel:[
                {display:'Товар', name:'offer.product.name', width:130, sortable:true, align:'center'},
                {display:'Цена', name:'offer.price', width:35, sortable:true, align:'left'},
                {display:'Доступно', name:'offer.amount', width:50, sortable:true, align:'left', colClass:'available'},
                {display:'Заказано', name:'amount', width:50, sortable:true, align:'left', colClass:'amount'},
                {display:'Сумма', width:50, sortable:false, align:'left'},
                {display:'Дата создания', name:'creationDate', width:100, sortable:true, align:'left'},
                {display:'Действие', name:'state', width:210, sortable:true, align:'left'}

            ],
            searchitems:[
                {display:'Товар', name:'offer.product.name'}
            ],
            sortname:"creationDate",
            sortorder:"ASC",
            usepager:true,
            title:'Активные заказы',
            useRp:true,
            rp:15,
            width:705,
            height:520,
            singleSelect:true,
            onSuccess:function () {
                $("#orders").each(function () {
                    $("td[abbr='amount']").addClass("amount");
                    $("td[abbr='offer.amount']").addClass("available");
                });
                validateOrders();
            }
        });

        $("#orders .confirm")
                .die("click")
                .live("click", function () {
                    var selectedRecordId = $(this).attr("name");
                    var url = "/order/confirm/" + selectedRecordId;
                    $.post(url, function (result) {
                        $("#orders").flexReload();
                    }, 'json');
                });

        $("#orders .deny")
                .die("click")
                .live("click", function () {
                    var selectedRecordId = $(this).attr("name");
                    var url = "/order/deny/" + selectedRecordId;
                    $.post(url, function (result) {
                        $("#orders").flexReload();
                    }, 'json');
                });


    </script>


</head>

<body>

<table id="orders" style="display: none"></table>

</body>
</html>