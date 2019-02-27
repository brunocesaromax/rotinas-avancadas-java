package servlets;

import dao.UsuarioDao;
import model.Usuario;
import org.apache.tomcat.util.codec.binary.Base64;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
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

        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().write("Erro fatal ao realizar upload");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        String acao = request.getParameter("acao");

        if (acao.equalsIgnoreCase("carregar")) {
            //Redirecionamento
            RequestDispatcher view = request.getRequestDispatcher("upload.jsp");
            try {
                request.setAttribute("listaUserImagem", usuarioDao.listar());
                view.forward(request, response);

            } catch (SQLException e) {
                e.printStackTrace();
            }

        } else if (acao.equalsIgnoreCase("download")) {

            String idUser = request.getParameter("idUser");
            Usuario usuario = usuarioDao.getUsuario(Long.valueOf(idUser));
            String imagem = usuario.getImagem();

            /*Tutorial para pegar a imagem do banco e trazer para o navegador*/
            if (imagem != null){

                response.setHeader("Content-Disposition","attachment;filename=file."+ usuario.getTipofile());

                /*Pega somente imagem pura, sem metadados*/
                String imagemPura = imagem.split(",")[1];
                /*Converte base 64 em bytes*/
                byte [] imageBytes = new Base64().decode(imagemPura);

                /*Coloca os bytes em um objeto de entrada para processar*/
                InputStream input = new ByteArrayInputStream(imageBytes);

                /*INICIO - escrever dados da resposta*/
                int read = 0;
                byte[] bytes = new byte[1024];
                OutputStream output = response.getOutputStream();

                while ((read = input.read(bytes)) != -1){
                    output.write(bytes, 0, read);
                }

                output.flush();
                output.close();

                /*FIM - escrever dados da resposta*/
            }

        }
    }
}
