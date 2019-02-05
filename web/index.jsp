<%--
  Created by IntelliJ IDEA.
  User: brunocesar
  Date: 02/02/19
  Time: 17:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Recursos avançados em Java</title>
</head>
<body>
<h3>Recursos avançados em Java</h3>

<table>
    <tr>
        <td><a href="pages/capturarExecoes.jsp">Capturar exeções</a></td>
    </tr>
    <tr>
        <td><a href="pages/acessoSistema.jsp">Acessar o sistema</a></td>
    </tr>
    <tr>
        <%--Passando por get, ou seja, por url--%>
        <td><a href="pages/UsuarioServlet?deslogar=true">Deslogar</a></td>
    </tr>
</table>


</body>
</html>
