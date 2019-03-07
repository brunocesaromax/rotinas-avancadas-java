package servlets;

import dao.CalculaDataFinalDao;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

@WebServlet("/pages/calcularDataFinal")
public class CalcularDataFinal extends HttpServlet {

    private CalculaDataFinalDao calculaDataFinalDao = new CalculaDataFinalDao();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        /*08:00-12:00 e 13:30-17:30*/
        /*1 dia é igual a 8 horas*/

        try {

            int horaDia = 8;
            Date dataCalculada = null;
            Double totalDias = 0.0;

            String data = request.getParameter("data");
            int tempo = Integer.parseInt(request.getParameter("tempo"));

            if (tempo <= horaDia) { // mesmo dia

                Date dateInformada = new SimpleDateFormat("dd/mm/yyyy").parse(data);
                Calendar calendar = Calendar.getInstance();

                calendar.setTime(dateInformada);
                totalDias = 1d;
                calendar.add(Calendar.DATE, totalDias.intValue());

                dataCalculada = calendar.getTime();

            } else {

                totalDias = (double) (tempo / horaDia);

                if (totalDias <= 1) {
                    dataCalculada = new SimpleDateFormat("dd/MM/yyyy").parse(data);
                } else {
                    Date dateInformada = new SimpleDateFormat("dd/MM/yyyy").parse(data);
                    Calendar calendar = Calendar.getInstance();

                    calendar.setTime(dateInformada);
                    calendar.add(Calendar.DATE, totalDias.intValue());

                    dataCalculada = calendar.getTime();

                }

            }

            calculaDataFinalDao.salvarDataFinal(new SimpleDateFormat("dd/MM/yyyy").format(dataCalculada));

            RequestDispatcher dispatcher = request.getRequestDispatcher("datas.jsp");
            request.setAttribute("dataFinal", new
                    SimpleDateFormat("dd/MM/yyyy").format(dataCalculada));
            request.setAttribute("dias", totalDias.intValue());
            dispatcher.forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
