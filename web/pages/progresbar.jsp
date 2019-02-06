<%--
  Created by IntelliJ IDEA.
  User: brunocesar
  Date: 06/02/19
  Time: 10:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Barra de progresso</title>

    <style type="text/css">
        #myProgress { /*fundo da barra de progresso*/
            width: 100%;
            background-color: #ddd; /*fundo da barra de progresso - cor cinza*/
        }

        #myBar {
            width: 1%;
            height: 30px;
            background-color: #4CAF50;
        }

    </style>

</head>
<body>

<h1>Exemplo com java script</h1>

<div id="myProgress"><%--Fundo da barra--%>
    <div id="myBar"><%--barra de progresso--%>
    </div>
</div>
<br/>
<button onclick="exibirBarra()">Iniciar a barra de progresso</button>

<script type="text/javascript">

    function exibirBarra() {
        var elem = document.getElementById("myBar");
        var width = 1; // Largura
        var id = setInterval(frame,25);/*Atraso da barra de progresso*/

        function frame() {
            if (width >= 100){
                clearInterval(id);
            } else{
                width++;
                elem.style.width = width + "%";
            }
        }
    }

</script>

</body>
</html>
