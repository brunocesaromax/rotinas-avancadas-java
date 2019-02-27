<%--
  Created by IntelliJ IDEA.
  User: brunocesar
  Date: 27/02/19
  Time: 11:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Data-Table jQuery</title>
    <link rel="stylesheet" href="https://cdn.datatables.net/1.10.19/css/jquery.dataTables.min.css">
    <%--Primeiro biblioteca do jQuery--%>
    <script type="application/javascript" src="https://code.jquery.com/jquery-3.3.1.js"></script>
    <%--Depois biblioteca do DataTable--%>
    <script src="https://cdn.datatables.net/1.10.19/js/jquery.dataTables.min.js"></script>

</head>
<body>

<table id="minhaTabela" class="display" style="width:100%">
    <thead>
    <tr>
        <th>Name</th>
        <th>Position</th>
        <th>Office</th>
        <th>Age</th>
        <th>Start date</th>
        <th>Salary</th>
    </tr>
    </thead>
    <tbody>
    <tr>
        <td>Tiger Nixon</td>
        <td>System Architect</td>
        <td>Edinburgh</td>
        <td>61</td>
        <td>2011/04/25</td>
        <td>$320,800</td>
    </tr>
    <tr>
        <td>Garrett Winters</td>
        <td>Accountant</td>
        <td>Tokyo</td>
        <td>63</td>
        <td>2011/07/25</td>
        <td>$170,750</td>
    </tr>
    </tbody>
</table>

</body>

<script type="application/javascript">
    $(document).ready(function () {
        $('#minhaTabela').DataTable();
    });

</script>

</html>
