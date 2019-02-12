package dao;

import connection.SingleConnection;
import model.Usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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

    public List<Usuario> listar() throws SQLException {

        List<Usuario> usuarios = new ArrayList<>();

        String sql = "select * from usuario";
        PreparedStatement statement = connection.prepareStatement(sql);
        ResultSet result = statement.executeQuery();

        while (result.next()) {
            Usuario usuario = new Usuario();
            usuario.setId(result.getLong("id"));
            usuario.setLogin(result.getString("login"));
            usuario.setSenha(result.getString("senha"));
            usuario.setImagem(result.getString("imagem"));

            usuarios.add(usuario);
        }

        return usuarios;
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

    public String getImagem(Long idUsuario) {

        try {
            String sql = "select imagem from usuario where id = ?";
            PreparedStatement statement = null;
            statement = connection.prepareStatement(sql);
            statement.setLong(1, idUsuario);
            ResultSet result = statement.executeQuery();

            while (result.next()) {
                return result.getString("imagem");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
}
