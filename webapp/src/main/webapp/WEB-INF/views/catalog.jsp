<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>Vegetary | Каталог предложений</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>

    <link type="text/css" href="../resources/css/table_style.css" rel="stylesheet"/>
    <link rel="stylesheet" href="../resources/css/reset.css" />
    <link rel="stylesheet" href="../resources/css/styles.css" />
    <link type="text/css" href="../resources/css/flick/jquery-ui-1.8.18.custom.css" rel="stylesheet"/>
    <link rel="stylesheet" href="../resources/css/flexigrid.pack.css">

    <!-- Scripts -->
    <script type="text/javascript" src="../resources/js/jquery-1.7.1.min.js"></script>
    <script type="text/javascript" src="../resources/js/jquery-ui-1.8.18.custom.min.js"></script>
    <script type="text/javascript" src="../resources/js/flexigrid.pack.js"></script>
    <script type="text/javascript">
        function convertData(data) {

            var rows = Array();

            $.each(data.rows, function (i, row) {

                rows.push({
                    id:row.id,
                    cell:[row.product.name,
                        row.price,
                        row.amount,
                        "<a name='" + row.id + "' class='details button'>Подробнее</a> "]});
            });

            return {
                total:data.total,
                page:data.page,
                rows:rows
            };
        }

        $(document).ready(function(){

            $("#offers").flexigrid({
                url:'/offer/all/paging',
                dataType:'json',
                preProcess:convertData,
                colModel:[
                    {display:'Товар', name:'product.name', width:200, sortable:true, align:'center'},
                    {display:'Цена', name:'price', width:200, sortable:true, align:'left'},
                    {display:'В наличии(кг)', name:'amount', width:200, sortable:true, align:'left'},
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


            $("#offers .details")
                    .die()
                    .live("click", function () {
                        var url = "/offer/view/" + $(this).attr("name");
                        var dialogDiv = $("#offerDescription");
                        dialogDiv.load(url, function () {
                            dialogDiv.dialog("open");
                        });
                    });
        });

    </script>


    <!--[if IE 6]>
    <script type="text/javascript" src="../resources/js/DD_belatedPNG_0.0.8a-min.js"></script>
    <script type="text/javascript">
        /* EXAMPLE */
        DD_belatedPNG.fix('.button');

        /* string argument can be any CSS selector */
        /* .png_bg example is unnecessary */
        /* change it to what suits you! */
    </script>
    <![endif]-->

</head>

<body>

<div id="wrapper" class="container_12 clearfix">

    <!-- Text Logo -->
    <h1 id="logo" class="grid_4">Vegetary</h1>

    <!-- Navigation Menu -->
    <ul id="navigation" class="grid_8">
        <li><a href="/login"><span class="meta">Вход/Регистрация</span><br />Вход</a></li>
        <li><a href="/contact" ><span class="meta">Есть предложеня? Пишите!</span><br />Обратная связь</a></li>
        <li><a href="/about"><span class="meta">О проекте</span><br />О нас</a></li>
        <li><a href="/catalog" class="current"><span class="meta">Каталог товаров</span><br />Каталог</a></li>
        <li><a href="/home" ><span class="meta">Главная</span><br />Главная</a></li>
    </ul>

    <div class="hr grid_12 clearfix">&nbsp;</div>

    <!-- Caption Line -->

    <!-- Caption Line -->
    <h2 class="grid_12 caption">Каталог предложений</h2>

    <!-- Column 1 / Content -->

    <div class="grid_11">
        <table id="offers" style="display: none"></table>

        <div id="offerDescription" title="Детальней о предложении"></div>
    </div>

</div>
<!--end wrapper-->
<!-- Footer -->
<div class="footer ">
    <b>&copy; Copyright</b> Alexander Serebriyan
</div>

</body>
</html>
