package servlets;

import dao.EventoDao;
import model.Evento;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/pages/buscarCalendarioDatas")
public class BuscarCalendarioDatas extends HttpServlet {

    private EventoDao eventoDao = new EventoDao();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {

            List<Evento> eventos = eventoDao.listar();

            if (!eventos.isEmpty()) {

                String datas = "[";

                int contador = 1;
                for (Evento evento : eventos) {
                    datas += "{ \"title\":\"" + evento.getDescricao() + "\", \"start\":\"" + evento.getDataEvento() + "\"}";

                    if (contador < eventos.size()) {
                        contador++;
                        datas += ",";
                    }

                }

                datas += "]";
                response.setStatus(200);
                response.getWriter().write(datas);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}