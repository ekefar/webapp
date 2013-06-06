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
                rows.push({
                    id:row.id,
                    cell:[row.product.name,
                            row.product.category.name,
                        row.price,
                        row.amount,
                        "<a name='" + row.id + "' class='details button'>Подробнее</a> " +
                                "<a name='" + row.id + "' class='cart button'>Добавить в корзину</a>"]});
            });

            return {
                total:data.total,
                page:data.page,
                rows:rows
            };
        }

        $("#offers").flexigrid({
            url:'/offer/all/paging/${userId}',
            dataType:'json',
            preProcess:convertData,
            colModel:[
                {display:'Товар', name:'product.name', width:200, sortable:true, align:'center'},
                {display:'Категория', name:'product.category.name', width:200, sortable:true, align:'center'},
                {display:'Цена', name:'price', width:100, sortable:true, align:'left'},
                {display:'В наличии(кг)', name:'amount', width:100, sortable:true, align:'left'},
                {display:'Действия', name:'action', width:300, sortable:true, align:'left'}

            ],
            searchitems:[
                {display:'Товар', name:'product'},
                {display: 'Категория', name:'category'}
            ],
            sortname:"product.name",
            sortorder:"ASC",
            usepager:true,
            useRp:true,
            rp:15,
            width:980,
            height:520,
            singleSelect: true
        });

    </script>

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


        $("#offers .details")
                .die()
                .live("click", function () {
                    var url = "/offer/view/" + $(this).attr("name");
                    var dialogDiv = $("#offerDescription");
                    dialogDiv.load(url, function () {
                        dialogDiv.dialog("open");
                    });
                });

        $("#offers .cart")
                .die()
                .live("click", function () {
                    var url = "/cart/add/" + $(this).attr("name");
                    var dialogDiv = $("#cartForm");
                    dialogDiv.load(url, function () {
                        dialogDiv.dialog("open");
                    });
                });

    </script>
</head>
<body>


<!-- Caption Line -->
<h2 class="grid_12 caption">Каталог предложений</h2>

<table id="offers" style="display: none"></table>

<div id="offerDescription" title="Offer info"></div>

<div id="cartForm" title="Add to cart"></div>

</body>
</html>