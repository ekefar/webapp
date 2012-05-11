<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="UTF-8"/>
    <title>User profile</title>

    <link type="text/css" href="../../resources/css/flick/jquery-ui-1.8.18.custom.css" rel="stylesheet"/>
    <link type="text/css" href="../../resources/css/style.css" rel="stylesheet"/>
    <link type="text/css" href="../../resources/css/table_style.css" rel="stylesheet"/>
    <link href="../../resources/css/fileuploader.css" rel="stylesheet" type="text/css"/>
    <link rel="stylesheet" href="../../resources/css/flexigrid.pack.css">

    <script type="text/javascript" src="../../resources/js/jquery-1.7.1.min.js"></script>
    <script type="text/javascript" src="../../resources/js/jquery-ui-1.8.18.custom.min.js"></script>
    <script src="../../resources/js/fileuploader.js" type="text/javascript"></script>
    <script type="text/javascript" src="../../resources/js/jquery.validate.min.js" charset="utf-8"></script>
    <script type="text/javascript" src="../../resources/js/jquery.form.js" charset="utf-8"></script>
    <script type="text/javascript" src="../../resources/js/flexigrid.pack.js"></script>

    <script type="text/javascript">
        $(document).ready(function () {

            $("#users_btn")
                    .click(function () {
                        $("#content").load("/admin/users");

                    })
                    .button();

            $("#offers_btn")
                    .click(function () {
                        $("#content").load("/admin/offers");
                    })
                    .button();

            $("#products_btn")
                    .click(function () {
                        $("#content").load("/admin/products");
                    })
                    .button();

            $("#profile_btn")
                    .button();

            users_btn.click();

        });

    </script>


</head>
<body>

<div class="container">

    <div id="navigation" class="navigation_vertical">
        <div>
            <a id="users_btn" class="navigation_btn" href="#users">Users</a>
        </div>

        <div>
            <a id="offers_btn" class="navigation_btn" href="#offers">Offers</a>
        </div>

        <div>
            <a id="products_btn" class="navigation_btn" href="#products">Products</a>
        </div>

        <div>
            <a id="profile_btn" class="navigation_btn" href="/admin/userProfile/${user.id}">User profile</a>
        </div>
    </div>

    <div id="content" class="content"></div>

</div>

</body>
</html>