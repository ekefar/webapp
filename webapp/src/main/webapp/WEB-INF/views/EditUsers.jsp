<%--
Created by IntelliJ IDEA.
User: gar
Date: 02.04.12
Time: 22:48
To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>

    <link type="text/css" href="../resources/css/flick/jquery-ui-1.8.18.custom.css" rel="stylesheet"/>
    <link type="text/css" href="../resources/css/style.css" rel="stylesheet"/>

    <script type="text/javascript" src="../resources/js/jquery-1.7.1.min.js"></script>
    <script type="text/javascript" src="../resources/js/jquery-ui-1.8.18.custom.min.js"></script>
    <script type="text/javascript" src="../resources/js/jquery.jeditable.mini.js"></script>
    <script type="text/javascript">
        $(document).ready(function () {
            showUsers();

            $("#update").click(function () {
                $("#output").html("");
                showUsers();
            });

            $(":checkbox").live('change', function () {
                var login = $(this).attr("id");
                var enabled = $(this).prop("checked");
                var postData = "{login:" + login + ", enabled:" + enabled + "}";
                $.post("/Admin/EditUsers", postData, 'json');
            });

            $("button").button();
        });


        var showUsers = function () {
            var output = $("#output");

            output.append("<tr><th width='40%'>Login</th>"+
                    "<th width='40%'>Password</th>"+
                    "<th width='20%'>Is Enabled?</th> </tr>");


            $.getJSON("/Admin/EditUsers", function (data) {
                $.each(data, function () {
                    var userInfo = "<tr><td>" + this.login + "</td> <td>" + this.email + "</td><td>"+
                            "<input type='checkbox' id='" + this.login +"'";

                    if(this.enabled)
                        userInfo += "checked='checked'";

                    userInfo +="/></td></tr>";

                    output.append(userInfo);
                });
            });
        }
    </script>


</head>
<body>


<div id="header" class="header"></div>


<div >
    <button id="update">Refresh</button>   <br/><br/>
</div>
<div id="outputDiv" class="content">
    <table id="output" border="1"  style="font-size: large; width: 800px">

    </table>
</div>



<div id="footer" class="footer"></div>


</body>
</html>