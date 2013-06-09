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
                var date = new Date(new Date(row.creationDate));
                var formattedDate = date.getDate() + ' / ' + date.getMonth() + ' / ' + date.getFullYear();
                rows.push({
                    id:row.id,
                    cell:[row.offer.product.name,
                        addCommas(row.offer.price.toFixed(2)),
                        addCommas(row.offer.amount),
                        addCommas(row.amount),
                        addCommas((row.amount * row.offer.price).toFixed(2)),
                        formattedDate,
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
                {display:'Цена(грн)', name:'offer.price', width:35, sortable:true, align:'left'},
                {display:'Доступно(кг)', name:'offer.amount', width:50, sortable:true, align:'left', colClass:'available'},
                {display:'Заказано(кг)', name:'amount', width:50, sortable:true, align:'left', colClass:'amount'},
                {display:'Сумма(грн)', width:50, sortable:false, align:'left'},
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
            height:530,
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
                        $("#row" + result.id).remove();
                    }, 'json');
                });


    </script>


</head>

<body>

<table id="orders" style="display: none"></table>

</body>
</html>