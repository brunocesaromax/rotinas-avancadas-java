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

    <%--Para trabalhar com os temas do jQuery deve-se usar o script e o style do mesmo--%>
    <%--Tema do jQuery--%>

    <link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
    <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
    <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>


    <%------------------------------------------------------------------------------%>
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

        /*Sobrescrever uma classe de CSS que ja existe no jQuery*/
        .ui-progressbar {
            position: relative;
        }

        .progress-label {
            position: relative;
            left: 50%;
            top: 4px;
            font-weight: bold;
            text-shadow: 1px 1px 0 #fff;
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

<br/>
<h1>Barra de progresso com jQuery</h1>

<div id="progressbar">
    <div class="progress-label">
        Carregando...
    </div>
</div>

<script type="text/javascript">

    var val;

    //script da barra de progresso com jQuery
    $(function () {

        var progresslabel = $(".progress-label");

        $("#progressbar").progressbar({ //cria a barra no div
            value: false,
            change: function () {
                progresslabel.text($("#progressbar").progressbar('value') + "%");
            },
            complete: function () {
                progresslabel.text('Completo!');
            }
        });

        function progress() {
            val = $("#progressbar").progressbar("value") || 0;

            $("#progressbar").progressbar("value", val + 2);
            //setTimeout(progress, 80);

            if (val < 99) {
               setTimeout(progress,80);
            }
        }

      setTimeout(progress, 1000);// Chamado ao abrir a tela, 2 segundos

    });


    //script da barra de progresso com javascript
    function exibirBarra() {
        var elem = document.getElementById("myBar");
        var width = 1; // Largura
        var id = setInterval(frame, 25);

        /*Atraso da barra de progresso*/

        function frame() {
            if (width >= 100) {
                clearInterval(id);
            } else {
                width++;
                elem.style.width = width + "%";
            }
        }
    }

</script>

</body>
</html>
