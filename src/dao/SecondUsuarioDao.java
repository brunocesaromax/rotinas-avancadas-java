package dao;

import connection.SingleConnectionSecondBD;
import model.Usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SecondUsuarioDao {

    private Connection connection;

    public SecondUsuarioDao() {
        connection = SingleConnectionSecondBD.getConnection();
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

        String sql = "select * from usuario order by id";
        PreparedStatement statement = connection.prepareStatement(sql);
        ResultSet result = statement.executeQuery();

        while (result.next()) {
            Usuario usuario = new Usuario();
            usuario.setId(result.getLong("id"));
            usuario.setLogin(result.getString("login"));
            usuario.setSenha(result.getString("senha"));
            usuario.setImagem(result.getString("imagem"));
            usuario.setTipofile(result.getString("tipofile"));

            usuarios.add(usuario);
        }

        return usuarios;
    }

    public void gravarImagem(String imagem) {

        String tipoDados = imagem.split(",")[0].split(";")[0].split("/")[1];

        try {
            String sql = "insert into usuario(imagem, tipoFile) values (?,?)";
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

    public Usuario getUsuario(Long idUsuario) {

        try {
            String sql = "select * from usuario where id = ?";
            PreparedStatement statement = null;
            statement = connection.prepareStatement(sql);
            statement.setLong(1, idUsuario);
            ResultSet result = statement.executeQuery();

            while (result.next()) {
                Usuario usuario = new Usuario();
                usuario.setId(result.getLong("id"));
                usuario.setLogin(result.getString("login"));
                usuario.setSenha(result.getString("senha"));
                usuario.setImagem(result.getString("imagem"));
                usuario.setTipofile(result.getString("tipofile"));

                return usuario;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
}
