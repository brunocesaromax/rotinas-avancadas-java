package servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/pages/buscarDatasPlanejamento")
public class BuscarDatasPlanejamento extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String grantJson = "[" +
                "{" +
                "\"id\": \"1\", \"name\": \"Projeto Java Web\", \"series\": [" +
                "{\"name\": \"Planejado\", \"start\": new Date(2019, 00, 01), \"end\": new Date(2019, 00, 03)}," +
                "{\"name\": \"Real\", \"start\": new Date(2019, 00, 02), \"end\": new Date(2019, 00, 05), \"color\": \"#f0f0f0\"}" +
                "]" +
                "}" +
                "]";

        response.setStatus(200);
        response.getWriter().write(grantJson);

    }
}
