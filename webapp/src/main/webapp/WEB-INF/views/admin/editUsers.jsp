<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>

    <script type="text/javascript">

        /* var showUsers = function () {
         var output = $("#output");

         output.append("<tr><th width='40%'>Login</th>" +
         "<th width='40%'>Password</th>" +
         "<th width='20%'>Is Enabled?</th> </tr>");


         $.getJSON("/admin/editUsers", function (data) {
         $.each(data, function () {
         var userInfo = "<tr><td>" + this.login + "</td> <td>" + this.email + "</td><td>" +
         "<input type='checkbox' id='" + this.login + "'";

         if (this.enabled)
         userInfo += "checked='checked'";

         userInfo += "/></td></tr>";

         output.append(userInfo);
         });
         });
         };


         showUsers();

         $("#update")
         .unbind("click")
         .click(function () {
         $("#output").html("");
         showUsers();
         });

         $(":checkbox")
         .die()
         .live('change', function () {
         var login = $(this).attr("id");
         var enabled = $(this).prop("checked");
         var postData = "login=" + login + "&enabled=" + enabled;
         $.post("/admin/editUsers", postData);
         });

         $("button").button();*/


        function convertData(data) {

            var rows = Array();

            $.each(data.rows, function (i, row) {

                var action = row.enabled ? "Deactivate" : "Activate";

                rows.push({
                    id:row.login,
                    cell:[row.login,
                        row.email,
                        row.enabled,
                        '<a href="javascript:void(0)" class="action" id="' + row.id + '">' + action + '</a>']});
            });

            return {
                total:data.total,
                page:data.page,
                rows:rows
            };
        }

        $("#flex1").flexigrid({
            url:'/admin/users/edit',
            dataType:'json',
            preProcess:convertData,
            colModel:[
                {display:'login', name:'login', width:40, sortable:true, align:'center'},
                {display:'email', name:'email', width:180, sortable:true, align:'left'},
                {display:'enabled', name:'enabled', width:120, sortable:true, align:'left'},
                {display:'action', name:'action', width:120, sortable:true, align:'left'}

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
            showTableToggleBtn:true,
            width:700,
            height:200
        });

        $("#flex1 .action")
                .button()
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
    <table id="output" border="1" style="font-size: large; width: 800px; display: none;">

    </table>


    <table id="flex1" style="display: none"></table>


</div>

</body>
</html>