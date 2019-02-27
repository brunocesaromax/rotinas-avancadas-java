package servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/pages/carregarDadosDataTable")
public class CarregarDadosDataTable extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

     String json =  "{"+
             "\"draw\": 1,"+
                "\"recordsTotal\": 57,"+
                "\"recordsFiltered\": 57,"+
                "\"data\": ["+
		"["+
        "\"Airi\","+
                "\"Satou\","+
                "\"Accountant\","+
                "\"Tokyo\","+
                "\"28th Nov 08\","+
                "\"$162,700\""+
		"]"+
	"]"+
"}";


        response.setStatus(200); // Resposta completa ok
        response.getWriter().write(json);//json de resposta (escreve a resposta Http)
    }
}
