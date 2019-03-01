package servlets;

import dao.PrimaryUsuarioDao;
import model.Usuario;
import services.RelatorioService;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@WebServlet("/DownloadFile")
public class DownloadFile extends HttpServlet {

    private RelatorioService relatorioService = new RelatorioService();
    private PrimaryUsuarioDao usuarioDao = new PrimaryUsuarioDao();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {

            ServletContext context = request.getServletContext();

            String tipoExportacao = request.getParameter("tipoExportar");

            List<Usuario> usuarios = null;
            try {
                usuarios = usuarioDao.listar();
            } catch (SQLException e) {
                e.printStackTrace();
            }

            List dados = new ArrayList(); // Lista genérica
            dados.add(usuarios);

            String fileUrl = relatorioService.gerarRelatorio(dados, new HashMap(), "rel_usuario", "rel_usuario", context);

            /*Construir caminho completo e absoluto do arquivo*/
            File downloadFile = new File(fileUrl);
            FileInputStream inputStream = new FileInputStream(downloadFile);

            /*Obter o tipo MIME do arquivo*/
            String mimeType = context.getMimeType(fileUrl);

            if (mimeType == null){
                //define como tipo binário se mapeamento mime não for encontrado
                mimeType = "application/octet-stream"; //Conseque fazer download de qualquer coisa
            }

            //define atributos para resposta
            response.setContentType(mimeType);
            response.setContentLength((int) downloadFile.length());

            //Definir cabeçalhos para a resposta
            String headerKey = "Content-Disposition";
            String headerValue = String.format("attachment; filename= \"%s\"",downloadFile.getName());

            response.setHeader(headerKey,headerValue);

            //Obter um fluxo de saída da resposta
            OutputStream outputStream = response.getOutputStream();

            byte[] buffer = new byte[4096]; // Tamanho padrão para fazer o download
            int bytesReader = -1;

            //Escrever bytes lidos a partir do fluxo de entrada para o fluxo de saída

            while ((bytesReader = inputStream.read(buffer)) != -1){
                outputStream.write(buffer,0,bytesReader);
            }

            inputStream.close();
            outputStream.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
