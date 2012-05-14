<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>

    <script type="text/javascript">
        function convertData(data) {

            var rows = Array();

            $.each(data.rows, function (i, row) {

                var action = row.enabled ? "Deactivate" : "Activate";

                rows.push({
                    id:row.login,
                    cell:[row.login,
                        row.email,
                        row.enabled,
                        '<a href="javascript:void(0)" class="action button" id="' + row.id + '">' + action + '</a>']});
            });

            return {
                total:data.total,
                page:data.page,
                rows:rows
            };
        }

        $("#users").flexigrid({
            url:'/admin/users/edit',
            dataType:'json',
            preProcess:convertData,
            colModel:[
                {display:'Логин', name:'login', width:200, sortable:true, align:'center'},
                {display:'E-mail', name:'email', width:200, sortable:true, align:'left'},
                {display:'Активен?', name:'enabled', width:200, sortable:true, align:'left'},
                {display:'Действия', name:'action', width:300, sortable:true, align:'left'}

            ],
            searchitems:[
                {display:'login', name:'login'}
            ],
            sortname:"login",
            sortorder:"ASC",
            usepager:true,
            title:'Users',
            useRp:true,
            rp:15,
            width:980,
            height:520,
            singleSelect: true
        });

        $("#users .action")
                .die()
                .live('click', function () {

                    var id = $(this).attr("id");
                    var enabled = $(this).text() == "Activate";
                    var postData = "id=" + id + "&enabled=" + enabled;
                    $.post("/admin/editUsers", postData, function (callback) {
                        var action = callback ? "Deactivate" : "Activate";
                        $("#" + id).text(action);
                    }, 'json');
                });
    </script>


</head>
<body>


<div id="outputDiv">

    <table id="users" style="display: none"></table>

</div>

</body>
</html>