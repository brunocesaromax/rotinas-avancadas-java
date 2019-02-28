package connection;

import java.sql.Connection;
import java.sql.DriverManager;

/*Responsável pela conexão com o banco de dados mysql*/
public class SingleConnectionMySql {

    private static String banco = "jdbc:mysql://localhost:3306/java-rotinas-avancadas?autoReconnect=true&useUnicode=yes&characterEncoding=UTF-8";// Se banco cair ele irá se auto reconectar
    //private static String password = "postgres";
    private static String user = "root";
    private static Connection connection = null;

    static {
        conectar();
    }

    public SingleConnectionMySql() {
        conectar();
    }

    private static void conectar() {

        try {
            if (connection == null) {
                Class.forName("com.mysql.cj.jdbc.Driver");
                connection = DriverManager.getConnection(banco, user, null);
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
