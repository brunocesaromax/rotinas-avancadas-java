package execoes;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/pages/capturarExecao")//Mapeamento de servlet sem precisar configurar o web.xml
public class CapturarExecao extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {
            System.out.println(request.getParameter("valorParam"));

            Integer.parseInt(request.getParameter("valorParam"));

            response.setStatus(200); //Sucesso
            response.getWriter().write("Processada com sucesso");

        } catch (Exception e) {
            response.setStatus(500); // Erro no servidor
            response.getWriter().write("Erro ao processar: "+e.getMessage()); // adiciona valor ao responseText
        }


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
