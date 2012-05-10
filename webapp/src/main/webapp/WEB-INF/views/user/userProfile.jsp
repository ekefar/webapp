<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title></title>

    <script type="text/javascript">

        $("#reset_btn")
                .click(function () {
                    var url = "/settings/profile/refresh/" + $("#profile_settings").attr("name");
                    $.getJSON(url, function (data) {
                        $("#name").val(data.companyName);
                        $("#description").val(data.companyDescription);
                        $("#contact").val(data.companyContact);

                    });

                    $("#profile_settings").show();
                })
                .button();

        $('#profile_submit')
                .click(function () {
                    var url = "/settings/profile/" + $("#profile_settings").attr("name");
                    $.post(url, $('#profile_form').serialize());
                })
                .button();

        $("#dateOfBirth").datepicker();



    /*    var updateImage = function () {
            var src = "/settings/profile/avatar?" + new Date().getTime();
            $('#avatar').attr("src", src);
            $('#upload_container').innerHTML = "";
        }


        var uploader = new qq.FileUploader({
            // pass the dom node (ex. $(selector)[0] for jQuery users)
            element:document.getElementById('upload_container'),
            // path to server-side upload script
            action:'/settings/profile/avatar',
            onComplete:updateImage
        });

        updateImage();*/
    </script>
</head>
<body>
<div id="settings_values" class="content">
    <div id="profile_settings" name="${user.id}">

        <form id="profile_form">
            <table>
                <tr>
                    <td>
                        Name:
                    </td>
                    <td>
                        <input type="text" name="name" id="name" size="50" value="${user.companyName}"/>
                    </td>
                </tr>

                <tr>
                    <td>
                        Description:
                    </td>
                    <td>
                        <textarea rows="5" cols="50" type="text" name="description" id="description" size="50" >${user.companyDescription}</textarea>
                    </td>
                </tr>

                <tr>
                    <td>
                        Contact:
                    </td>
                    <td>
                        <textarea rows="5" cols="50" type="text" name="contact" id="contact" size="50" >${user.companyContact}</textarea>
                    </td>
                </tr>

                <tr>
                    <td align="center" colspan="2">
                        <br/>
                        <button id="reset_btn" type="button" class="navigation_btn">Reset</button>
                        <button id="profile_submit" type="button" class="navigation_btn">Save</button>

                    </td>
                </tr>
            </table>
        </form>
    </div>
</body>
</html>