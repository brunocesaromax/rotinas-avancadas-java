package dao;

import connection.SingleConnection;
import model.Usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UsuarioDao {

    private Connection connection;

    public UsuarioDao() {
        connection = SingleConnection.getConnection();
    }

    public void salvar(Usuario usuario) {

        try {
            String sql = "insert into usuario(login,senha) values (?,?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, usuario.getLogin());
            statement.setString(2, usuario.getSenha());

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


    public void gravarImagem(String imagem) {

        try {
            String sql = "insert into usuario(imagem) values (?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, imagem);

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
}
