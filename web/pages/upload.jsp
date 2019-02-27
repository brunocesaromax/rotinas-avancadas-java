<%--
  Created by IntelliJ IDEA.
  User: brunocesar
  Date: 08/02/19
  Time: 13:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Upload files</title>
    <script type="text/javascript" src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
</head>
<body>

<H1>Upload Files</H1>
<input type="file" id="file" name="file" onchange="uploadFile()"/>
<img alt="Imagem" src="" id="target" width="200" height="200">

<br/>
<br/>
<br/>
<br/>

<a href="fileUpload?acao=carregar">Carregar imagem</a>

<br/>

<table>

    <c:forEach items="${listaUserImagem}" var="user">
        <tr>
            <td>${user.id}</td>
            <td>${user.login}</td>
                <%--Capturando o tipo de acao que o parametro acao ira fazer e baixar a imagem pelo id do usuario--%>
            <td><a target="_blank" href="fileUpload?acao=download&idUser=${user.id}">Download arquivo</a></td>
        </tr>

    </c:forEach>

</table>

</body>

<script type="text/javascript">

    function uploadFile() {
        var target = document.querySelector("img"); // pegando elemento imagem
        var file = document.querySelector("input[type=file]").files[0]; // Pegando o input
        var reader = new FileReader();

        /*Executado por último*/
        reader.onloadend = function () {
            target.src = reader.result; // mostrando a imagem

            /// ---------Upload ajax do jQuery--------
            $.ajax({
                method: "POST", // base 64
                url: "fileUpload", // Chama a servlet
                data: {fileUpload: target.src} //dado obtido em JSON

            }).done(function (response) {
                alert("Sucesso: " + response);

            }).fail(function (xhr, status, errorThrown) {
                alert("Error: " + xhr.responseText);
            });

            /////////////////////
        };

        if (file) {
            reader.readAsDataURL(file);

        } else {
            target.src = "";
        }

    }

</script>

</html>
