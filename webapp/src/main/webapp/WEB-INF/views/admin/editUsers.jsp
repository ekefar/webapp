<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>

    <script type="text/javascript">

        var showUsers = function () {
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
        }

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

        $("button").button();


    </script>


</head>
<body>

<div>
    <button id="update">Refresh</button>
    <br/><br/>
</div>
<div id="outputDiv">
    <table id="output" border="1" style="font-size: large; width: 800px">

    </table>
</div>

</body>
</html>