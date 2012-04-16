<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <meta charset="UTF-8"/>
    <title>User profile</title>

    <link type="text/css" href="../resources/css/flick/jquery-ui-1.8.18.custom.css" rel="stylesheet"/>
    <link type="text/css" href="../resources/css/style.css" rel="stylesheet"/>
    <link href="../resources/css/fileuploader.css" rel="stylesheet" type="text/css"/>

    <script type="text/javascript" src="../resources/js/jquery-1.7.1.min.js"></script>
    <script type="text/javascript" src="../resources/js/jquery-ui-1.8.18.custom.min.js"></script>
    <script src="../resources/js/fileuploader.js" type="text/javascript"></script>

    <script type="text/javascript">
        $(document).ready(function () {
            hideSettingsValues();

            $("#profile_btn, #reset_btn").click(function () {
                hideSettingsValues();
                $.getJSON("/Settings/Profile", function (data) {
                    $("#name").val(data.name);
                    $("#age").val(data.age);
                    $("#skype").val(data.skype);
                    $("#hobby").val(data.hobby);
                });

                $("#profile_settings").show();
            });

            $("#other1_btn").click(function () {
                hideSettingsValues();
                $("#other1").show();
            });

            $("#other2_btn").click(function () {
                hideSettingsValues();
                $("#other2").show();
            });

            $("#other3_btn").click(function () {
                hideSettingsValues();
                $("#other3").show();
            });

            $('#profile_submit').click(function () {
                $.post("/Settings/Profile", $('#profile_form').serialize(), 'json');
            });

            var uploader = new qq.FileUploader({
                // pass the dom node (ex. $(selector)[0] for jQuery users)
                element:document.getElementById('upload_container'),
                // path to server-side upload script
                action:'/Settings/Profile/avatar',
                onComplete:updateImage
            });

            updateImage();

        });

        function hideSettingsValues() {
            $("#profile_settings").hide();
            $("#other1").hide();
            $("#other2").hide();
            $("#other3").hide();
        }


    </script>


    <script type="text/javascript">
        $(function () {
            $("a, button").button();
        });

        var updateImage = function () {
            var src = "/Settings/Profile/avatar?" + new Date().getTime();
            $('#avatar').attr("src", src);
            $('#upload_container').innerHTML = "";
        }

    </script>

</head>
<body>


<div id="header" class="header">

    <div class="logout_btn">
        <a href="<c:url value=" j_spring_security_logout"/>">Logout</a>
    </div>
</div>


<div class="container">

    <div id="settings_navigation" class="navigation_vertical">
        <div>
            <a id="profile_btn" class="navigation_btn" href="#profile">Profile</a>
        </div>

        <div>
            <a id="other1_btn" class="navigation_btn" href="#other1">Other 1</a>
        </div>


        <div>
            <a id="other2_btn" class="navigation_btn" href="#other2">Other 2</a>
        </div>


        <div>
            <a id="other3_btn" class="navigation_btn" href="#other3">Other 3</a>
        </div>
    </div>


    <div id="settings_values" class="content">
        <div id="profile_settings">

            <form id="profile_form">
                <table>
                    <tr>
                        <td>
                            Name:
                        </td>
                        <td>
                            <input type="text" name="name" id="name" size="50" value=""/>
                        </td>
                    </tr>

                    <tr>
                        <td>
                            Age:
                        </td>
                        <td>
                            <input type="text" name="age" id="age" size="50" value=""/>
                        </td>
                    </tr>

                    <tr>
                        <td>
                            Skype:
                        </td>
                        <td>
                            <input type="text" name="skype" id="skype" size="50" value=""/>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            Hobby:
                        </td>
                        <td>
                            <input type="text" name="hobby" id="hobby" size="50" value=""/>
                        </td>

                    </tr>
                    <tr>
                        <td>
                            Avatar:
                        </td>
                        <td>
                            <img id="avatar" src="" width="200" height="200"/>
                        </td>
                    </tr>

                    <tr>
                        <td>

                        </td>
                        <td>
                            <div id="upload_container">

                            </div>
                        </td>
                    </tr>

                    <tr>



                        <td  align="center" colspan="2">
                            <br/>
                            <button id="reset_btn" type="button" class="navigation_btn">Reset</button>
                            <button id="profile_submit" type="button" class="navigation_btn">Save</button>

                        </td>
                    </tr>
                </table>
            </form>
        </div>

        <div id="other1"> Some other settings/</div>

        <div id="other2"> And another settings/</div>


        <div id="other3"> And another settings once more!</div>

    </div>

</div>

<div id="footer" class="footer">2012</div>

<!-- End demo -->


</body>
</html>