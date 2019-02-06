<%--
  Created by IntelliJ IDEA.
  User: brunocesar
  Date: 06/02/19
  Time: 10:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Página pai</title>
    <script type="text/javascript" src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
</head>
<body>

<h1>Página pai , load com jQuery</h1>
<input type="button" value="Carregar página" onclick="carregar();">

<div id="mostrarPaginaFilha"><%--Local de carregamento da página filha, nessa div--%>

</div>

</body>

<script type="text/javascript">

    function carregar() {
        $("#mostrarPaginaFilha").load('paginaFilha.jsp'); //load página com jQuery
    }

</script>

</html>
