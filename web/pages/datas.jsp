<%--
  Created by IntelliJ IDEA.
  User: brunocesar
  Date: 07/03/19
  Time: 15:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Datas</title>

    <link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
    <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
    <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>

</head>
<body>

<h1>Datas</h1>

<form action="calcularDataFinal" method="post">

    <label>Data Inicial</label>
    <input id="data" name="data"/>

    <label>Tempo em horas</label>
    <input type="text" id="tempo" name="tempo"/>

    <input type="submit" value="calcular">

</form>

<br/>
<br/>

<label>Data Final</label>
<input type="text" id="dataFinal" name="dataFinal" readonly="readonly" value="${dataFinal}">

<label>Total de Dias</label>
<input type="text" id="dias" name="dias" readonly="readonly" value="${dias}"/>


</body>

<script type="application/javascript">

    $(function () {
        $('#data').datepicker({dateFormat: 'dd/mm/yy'});
    })


</script>

</html>
