package servlets;

import dao.PrimaryUsuarioDao;
import dao.UsuarioDaoMySql;
import model.Usuario;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/pages/carregarDadosDataTable")
public class CarregarDadosDataTable extends HttpServlet {

    private PrimaryUsuarioDao primaryUsuarioDao = new PrimaryUsuarioDao();
    private UsuarioDaoMySql usuarioDaoMySql = new UsuarioDaoMySql();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {

            List<Usuario> usuarios = primaryUsuarioDao.listar();
            //List<Usuario> usuarios = usuarioDaoMySql.listar();
            String dados = "";
            int contador = 1;

            //Processando a lista de dados
            for(Usuario usuario: usuarios) {

                dados+= "[" +
                        "\""+usuario.getId()+"\"," +
                        "\""+usuario.getLogin()+"\"," +
                        "\""+usuario.getTipofile()+"\"" +
                        "]";

                /*Condição para saber quando a última vírgula poderá ser colocada ou não*/
                if (contador < usuarios.size()){
                    dados+=",";
                }
                contador++;
            }
            ///////////////////////////////

            String json = "{";

            if (!usuarios.isEmpty()) {
                json += "\"draw\": 1," +
                        "\"recordsTotal\": " + usuarios.size() + "," +
                        "\"recordsFiltered\": " + usuarios.size() + "," +
                        "\"data\": [" + dados + "]";//fechamento do data
            }

            json+="}";

            response.setStatus(200); // Resposta completa ok
            response.getWriter().write(json);//json de resposta (escreve a resposta Http)

        } catch (SQLException e) {
            e.printStackTrace();
            response.setStatus(500);
        }

    }
}
