package dao;

import connection.SingleConnectionPrimaryBD;
import model.Evento;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EventoDao {

    private Connection connection;

    public EventoDao() {
        connection = SingleConnectionPrimaryBD.getConnection();
    }

  /*  public void salvar(Evento Evento) {

        try {
            String sql = "insert into Evento(login,senha) values (?,?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, Evento.getLogin());
            statement.setString(2, Evento.getSenha());

            statement.execute();
            connection.commit();

        } catch (SQLException e) {

            e.printStackTrace();

            try {
                connection.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }

        }

    }*/

    public List<Evento> listar() throws SQLException {

        List<Evento> Eventos = new ArrayList<>();

        String sql = "select * from eventos order by id";
        PreparedStatement statement = connection.prepareStatement(sql);
        ResultSet result = statement.executeQuery();

        while (result.next()) {
            Evento Evento = new Evento();
            Evento.setId(result.getLong("id"));
            Evento.setDataEvento(result.getString("dataEvento"));
            Evento.setDescricao(result.getString("descricao"));
            Eventos.add(Evento);
        }

        return Eventos;
    }

    /*public void gravarImagem(String imagem) {

        String tipoDados = imagem.split(",")[0].split(";")[0].split("/")[1];

        try {
            String sql = "insert into Evento(imagem, tipoFile) values (?,?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, imagem);
            statement.setString(2,tipoDados);

            statement.execute();
            connection.commit();

        } catch (SQLException e) {

            e.printStackTrace();

            try {
                connection.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }

        }

    }

    public Evento getEvento(Long idEvento) {

        try {
            String sql = "select * from Evento where id = ?";
            PreparedStatement statement = null;
            statement = connection.prepareStatement(sql);
            statement.setLong(1, idEvento);
            ResultSet result = statement.executeQuery();

            while (result.next()) {
                Evento Evento = new Evento();
                Evento.setId(result.getLong("id"));
                Evento.setLogin(result.getString("login"));
                Evento.setSenha(result.getString("senha"));
                Evento.setImagem(result.getString("imagem"));
                Evento.setTipofile(result.getString("tipofile"));

                return Evento;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }*/
}
