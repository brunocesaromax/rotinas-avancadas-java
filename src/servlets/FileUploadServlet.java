package servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/pages/fileUpload")
public class FileUploadServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {
            //Usar essa variável para salvar no BD
            String fileUpload = request.getParameter("fileUpload");

            response.getWriter().write("Upload realizado com sucesso!");
        }catch (Exception e){
            e.printStackTrace();
            response.getWriter().write("Erro fatal ao realizar upload");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


    }
}
