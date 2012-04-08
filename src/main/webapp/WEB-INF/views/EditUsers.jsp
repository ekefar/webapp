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
    <script type="text/javascript" src="../resources/js/jquery-1.7.1.min.js"></script>
    <script type="text/javascript" src="../resources/js/jquery.dataTables.min.js"></script>
    <script type="text/javascript">
        $(document).ready(function() {
            var oTable = $('#example').dataTable( {
                "bProcessing": true,
                "sAjaxSource": "/Admin/EditUsers",
                "aoColumns": [
                    { "mDataProp": "login" },
                    { "mDataProp": "password" },
                    { "mDataProp": "email" }
                ]
            } );
        } );
    </script>
    
    
</head>
<body>

<div id="dynamic">
    <table cellpadding="0" cellspacing="0" border="0" class="display" id="example">
        <thead>
        <tr>
            <th width="20%">login</th>
            <th width="25%">password</th>
            <th width="25%">Email</th>
        </tr>
        </thead>
        <tbody>

        </tbody>
        <tfoot>
        <tr>
            <th>login</th>
            <th>password</th>
            <th>email</th>
        </tr>
        </tfoot>
    </table>
</div>

</body>
</html>