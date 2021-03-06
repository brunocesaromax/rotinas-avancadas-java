<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Capturar Exeções</title>
    <%--Referência ao jquery pelo link do mesmo--%>
    <script type="text/javascript" src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
</head>
<body>

<h2>Capturar Exeções com jquery</h2>

<input type="text" value="valor informado" id="txtvalor">
<input type="button" value="Testar Exceção" onclick="testarExcecao();">

</body>

<script type="text/javascript">

    function testarExcecao() {

        var valorInformado = $('#txtvalor').val();

        /*Ajax do jQuery*/
        $.ajax({
            method: "POST", /*GET,POST,DELETE*/
            url: "capturarExecao", // para qual servlet?
            data: {valorParam: valorInformado} // Capturando do formulario

        }).done(function (response) { //resposta ok- nenhum erro
            alert("Sucesso, " +response);// Resposta do ajax na variavel response
            //Fazer algo se deu certo
        }).fail(function (xhr, status, errorThrown) { // xhr --> objeto com vários atributos
            alert("Error, "+ xhr.responseText);//mostras resposta do servidor
            //Fazer algo se deu errado
        });


    }

</script>

</html>
