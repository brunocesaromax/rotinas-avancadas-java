package filter;

import connection.SingleConnectionMySql;
import connection.SingleConnectionPrimaryBD;
import connection.SingleConnectionSecondBD;
import model.Usuario;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;

//Interceptação de urls
@WebFilter(urlPatterns = {"/pages/*"})
// Com essa anotação não precisa declarar arquivo no web.xml da aplicação
public class FilterAutenticacao implements Filter {

    private static Connection connectionPrimaryBD;
    private static Connection connectionSecondBD;
    private static Connection connectionMySql;



    //Executa algo quando a aplicação é iniciada
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        connectionPrimaryBD = SingleConnectionPrimaryBD.getConnection();
        connectionSecondBD = SingleConnectionSecondBD.getConnection();
        connectionSecondBD = SingleConnectionMySql.getConnection();

    }

    //Intercepta todas as requisições
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        //Saber se o usuário está na seção
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        //Pegando seção
        HttpSession session = httpServletRequest.getSession();
        //Pegando usuário da seção, retorna null caso usuário nao esteja logado

        String urlParaAutenticacao = httpServletRequest.getServletPath();

        Usuario usuarioLogado = (Usuario) session.getAttribute("usuario");

        if (usuarioLogado == null && !urlParaAutenticacao.equalsIgnoreCase("/pages/UsuarioServlet")) { // Usuário não logado
            //Redirecionamento em java
            RequestDispatcher dispatcher = servletRequest.getRequestDispatcher("/pages/login.jsp?url=" + urlParaAutenticacao);
            dispatcher.forward(servletRequest, servletResponse);
            return; // Para o processo redirecionar
        }

        //Executa as ações do request e response
        filterChain.doFilter(servletRequest, servletResponse);
    }

    //Executa algo quando a aplicação cai
    @Override
    public void destroy() {

    }
}
