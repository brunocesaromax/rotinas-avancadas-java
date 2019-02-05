package servlets;

import model.Usuario;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/pages/UsuarioServlet")
public class UsuarioServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String login = request.getParameter("login");
        String senha = request.getParameter("senha");
        String url = request.getParameter("url");

        //Pode ser feita uma validação por meio do banco de dados (opcional)
        if (login.equalsIgnoreCase("admin") && senha.equalsIgnoreCase("123")){
            //Se o login foi bem sucedido

            Usuario usuarioLogado = new Usuario();
            usuarioLogado.setLogin(login);
            usuarioLogado.setSenha(senha);

            //Adiciona usuário logado na seção
            HttpServletRequest httpServletRequest = (HttpServletRequest) request;
            HttpSession session = httpServletRequest.getSession();
            session.setAttribute("usuario",usuarioLogado);

            //Redireciona para o sistema e autoriza
            RequestDispatcher dispatcher = request.getRequestDispatcher(url);
            dispatcher.forward(request, response);

        }else{
            //Redireciona para o login novamente
            RequestDispatcher dispatcher = request.getRequestDispatcher("/pages/login.jsp");
            dispatcher.forward(request, response);
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        if (Boolean.parseBoolean(request.getParameter("deslogar"))){

            HttpServletRequest httpServletRequest = (HttpServletRequest) request;
            HttpSession session = httpServletRequest.getSession();
            session.invalidate(); // Invalidando usuário da seção
            // redirecionando para tela de login novamente
            response.sendRedirect("../index.jsp");
        }

    }
}
