<%--
  Created by IntelliJ IDEA.
  User: brunocesar
  Date: 04/02/19
  Time: 20:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
</head>
<body>

<h1>Autenticar usuário</h1>

<form action="UsuarioServlet" method="post">
    <input readonly="readonly" type="hidden" id="url" name="url" value="<%=request.getParameter("url")%>"><%--oculto--%>
    <table>
        <tr>
            <td>Login:</td>
            <td><input type="text" id="login" name="login"></td>
        </tr>
        <tr>
            <td>Senha:</td>
            <td><input type="password" id="senha" name="senha"></td>
        </tr>
        <tr>
            <td/>
            <td><input type="submit" id="logar" name="logar" value="Logar" style="width: 100px"></td>
        </tr>
    </table>

</form>

</body>
</html>
