<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title></title>


    <script type="text/javascript">
        function convertData(data) {

            var rows = Array();

            $.each(data.rows, function (i, row) {
                var date = new Date(new Date(row.creationDate));
                var formattedDate = date.getDate() + ' / ' + date.getMonth() + ' / ' + date.getFullYear();
                rows.push({
                    id:row.id,
                    cell:[row.offer.product.name,
                        addCommas(row.offer.price.toFixed(2)),
                        addCommas(row.amount),
                        addCommas((row.amount * row.offer.price).toFixed(2)),
                        formattedDate,
                        statusFormatter(row.state),
                        "<a class='remove button' name='" + row.id + "'>Удалить</a>"]});
            });

            return {
                total:data.total,
                page:data.page,
                rows:rows
            };
        }

        $("#orders").flexigrid({
            url:'/order/purchase/past/paging/${userId}',
            dataType:'json',
            preProcess:convertData,
            colModel:[
                {display:'Товар', name:'offer.product.name', width:150, sortable:true, align:'center'},
                {display:'Цена(грн)', name:'offer.price', width:35, sortable:true, align:'left'},
                {display:'Заказано(кг)', name:'amount', width:50, sortable:true, align:'left'},
                {display:'Cумма(грн)', width:50, sortable:false, align:'left'},
                {display:'Дата создания', name:'creationDate', width:100, sortable:true, align:'left'},
                {display:'Статус', name:'state', width:70, sortable:true, align:'left'},
                {display:'Действие', name:'state', width:170, sortable:true, align:'left'}
            ],
            searchitems:[
                {display:'Товар', name:'offer.product.name'}
            ],
            sortname:"creationDate",
            sortorder:"ASC",
            usepager:true,
            title:'Завершенные покупки',
            useRp:true,
            rp:15,
            width:705,
            height:530,
            singleSelect:true
        });


        $("#orders .remove")
                .die()
                .live("click", function () {
                    var selectedRecordId = $(this).attr("name");
                    var url = "/order/delete/" + selectedRecordId;
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