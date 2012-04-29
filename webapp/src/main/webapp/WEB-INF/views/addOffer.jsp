<%--
  Created by IntelliJ IDEA.
  User: alexander.serebriyan
  Date: 24.04.12
  Time: 18:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tags" %>

<html>
<head>


    <title>
        Add offer
    </title>

    <link type="text/css" href="../resources/css/flick/jquery-ui-1.8.18.custom.css" rel="stylesheet"/>
    <link type="text/css" href="../resources/css/style.css" rel="stylesheet"/>

    <script type="text/javascript" src="../resources/js/jquery-1.7.1.min.js"></script>
    <script type="text/javascript" src="../resources/js/jquery-ui-1.8.18.custom.min.js"></script>

</head>
<body>
<div id="profile_settings">
    <tags:offerForm formSend="/offer/add" offer="${offer}" categories="${categoryList}" user="${user}"/>
</div>

</body>
</html>