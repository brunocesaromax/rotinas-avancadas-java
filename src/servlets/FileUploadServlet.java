package servlets;

import dao.UsuarioDao;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/pages/fileUpload")
public class FileUploadServlet extends HttpServlet {

    private UsuarioDao usuarioDao = new UsuarioDao();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {
            //Usar essa variável para salvar no BD
            String fileUpload = request.getParameter("fileUpload");

            usuarioDao.gravarImagem(fileUpload);

            response.getWriter().write("Upload realizado com sucesso!");

        }catch (Exception e){
            e.printStackTrace();
            response.getWriter().write("Erro fatal ao realizar upload");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {
            //Redirecionamento
            RequestDispatcher view = request.getRequestDispatcher("upload.jsp");
            request.setAttribute("listaUserImagem", usuarioDao.listar());
            view.forward(request,response);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
