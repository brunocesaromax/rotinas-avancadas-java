package connection;

import java.sql.Connection;
import java.sql.DriverManager;

/*Responsável pela conexão com o banco de dados postgres*/
public class SingleConnectionSecondBD {

    private static String banco = "jdbc:postgresql://localhost:5432/aprendendo-jsp?autoReconnect=true&useUnicode=yes&characterEncoding=UTF-8";// Se banco cair ele irá se auto reconectar
    private static String password = "postgres";
    private static String user = "postgres";
    private static Connection connection = null;

    static {
        conectar();
    }

    public SingleConnectionSecondBD() {
        conectar();
    }

    private static void conectar() {

        try {
            if (connection == null) {
                Class.forName("org.postgresql.Driver");
                connection = DriverManager.getConnection(banco, user, password);
                connection.setAutoCommit(false); // Programador define quando uma operação será realmente salva no banco
            }

        } catch (Exception e) {
            e.printStackTrace();// Mostrar a pilha de erros
            throw new RuntimeException("Erro ao conectar com o banco de dados.");
        }

    }

    public static Connection getConnection() {
        return connection;
    }
}
