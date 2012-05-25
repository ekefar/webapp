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
                        row.price,
                        row.amount,
                        "<a name='" + row.id + "' class='edit button'>Изменить</a> " +
                                "<a name='" + row.id + "' class='remove button'>Удалить</a>"]});
            });

            return {
                total:data.total,
                page:data.page,
                rows:rows
            };
        }

        $("#offers").flexigrid({
            url:'/offer/own/paging/${userId}',
            dataType:'json',
            preProcess:convertData,
            colModel:[
                {display:'Товар', name:'product.name', width:200, sortable:true, align:'center'},
                {display:'Цена', name:'price', width:200, sortable:true, align:'left'},
                {display:'В наличии', name:'amount', width:200, sortable:true, align:'left'},
                {display:'Действия', name:'action', width:300, sortable:true, align:'left'}

            ],
            searchitems:[
                {display:'Товар', name:'product.name'}
            ],
            sortname:"product.name",
            sortorder:"ASC",
            usepager:true,
            useRp:true,
            rp:15,
            width:980,
            height:520,
            singleSelect:true
        });
    </script>

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
                                $("#offers").flexReload();
                            }, 'json');

                            $(this).dialog("close");
                        }
                    }
                });

        $("#productNew").dialog(
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
                            var postData = $("#productNew form").serialize();
                            var url = "/product/add";
                            $.post(url, postData);
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
                                $("#offers").flexReload();
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
                });

        $("#add_product")
                .unbind("click")
                .click(
                function () {
                    var url = "/product/add/";
                    var dialogDiv = $("#productNew");
                    dialogDiv.load(url, function () {
                        dialogDiv.dialog("open");
                    });
                });

        $("#offers .edit")
                .die()
                .live("click", function () {
                    var url = "/offer/edit/" + $(this).attr("name");
                    var dialogDiv = $("#offerEdit");
                    dialogDiv.load(url, function () {
                        dialogDiv.dialog("open");
                    });
                });

        $("#offers .remove")
                .die()
                .live("click", function () {
                    var id = $(this).attr("name");
                    var url = "/offer/deactivate";
                    var postData = "id=" + id;
                    $.post(url, postData, function (result) {
                        $("#row" + result.id).remove();
                    });
                });
    </script>
</head>
<body>
<div>
    <table id="offers" style="display: none"></table>
    <button id="add_btn" type="button" class="button right">Создать предложение</button>
    <button id="add_product" type="button" class="button right">Добавить товар</button>

</div>

<div id="offerNew" title="Новое предложение"></div>

<div id="offerEdit" title="Редактирование предложения"></div>
<div id="productNew" title="Создание товара"></div>
<br>
<br>
<br>

</body>
</html>