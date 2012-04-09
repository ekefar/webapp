<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="UTF-8"/>
    <title>User profile</title>
    <link type="text/css" href="../resources/css/flick/jquery-ui-1.8.18.custom.css" rel="stylesheet"/>
    <script type="text/javascript" src="../resources/js/jquery-1.7.1.min.js"></script>
    <script type="text/javascript" src="../resources/js/jquery-ui-1.8.18.custom.min.js"></script>

    <script type="text/javascript">
        $(document).ready(function () {
            hideSettingsValues();

            $("#profile_btn").click(function () {
                hideSettingsValues();
                $.getJSON("/Settings/Profile", function (data) {
                    $("#name").val(data.name);
                    $("#age").val(data.age);
                    $("#skype").val(data.skype);
                    $("#hobby").val(data.hobby);
                });
                $("#profile_settings").show();
            });

            $("#other1").click(function () {
                hideSettingsValues();
                $("#other_settings1").show();
            });

            $("#other2").click(function () {
                hideSettingsValues();
                $("#other_settings2").show();
            });

            $("#other3").click(function () {
                hideSettingsValues();
                $("#other_settings3").show();
            });

            $('#profile_submit').click(function () {
                $.post("/Settings/Profile", $('#profile_form').serialize(), 'json');
            });

        });

        var hideSettingsValues = function () {
            $("#profile_settings").hide();
            $("#other_settings1").hide();
            $("#other_settings2").hide();
            $("#other_settings3").hide();
        }

    </script>


    <script type="text/javascript">
        $(function () {
            $("a, button").button();
        });

    </script>

</head>
<body>

<div class="user_settings" style="margin-left: 20px; margin-top: 20px;">

    <div id="settings_navigation" style="float: left; width: 200px">
        <div>
            <a id="profile_btn" style="width:200px; margin: 5px;">Profile</a>
        </div>

        <div>
            <a id="other1" style="width:200px;margin: 5px;">Other settings1</a>
        </div>

        <div>
            <a id="other2" style="width:200px;margin: 5px;">Other settings2</a>
        </div>

        <div>
            <a id="other3" style="width:200px;margin: 5px;">Other settings3</a>
        </div>

        <div>
            <a style="width:200px;margin: 5px" href="<c:url value=" j_spring_security_logout"/>">Logout</a>
        </div>

    </div>


    <div id="settings_values" style=" width: 300px; margin: 0 auto; ">
        <div id="profile_settings">

            <form id="profile_form">
                <table>
                    <tr>
                        <td>
                            Name:
                        </td>
                        <td>
                            <input type="text" name="name" id="name" size="30" value=""/>
                        </td>
                    </tr>

                    <tr>
                        <td>
                            Age:
                        </td>
                        <td>
                            <input type="text" name="age" id="age" size="30" value=""/>
                        </td>
                    </tr>

                    <tr>
                        <td>
                            Skype:
                        </td>
                        <td>
                            <input type="text" name="skype" id="skype" size="30" value=""/>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            Hobby:
                        </td>
                        <td>
                            <input type="text" name="hobby" id="hobby" size="30" value=""/>
                        </td>
                    </tr>

                    <tr>
                        <td colspan="2" align="center">
                            <br/>
                            <button id="profile_submit" type="button" style="width: 250px">Save</button>

                        </td>
                    </tr>
                </table>
            </form>
        </div>

        <div id="other_settings1">
            Other settings 1
        </div>


        <div id="other_settings2">
            Other settings 2
        </div>


        <div id="other_settings3">
            Other settings 3
        </div>

    </div>

</div>


<!-- End demo -->


</body>
</html>