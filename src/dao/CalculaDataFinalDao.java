package dao;

import connection.SingleConnectionPrimaryBD;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class CalculaDataFinalDao {

    private Connection connection;

    public CalculaDataFinalDao() {
        connection = SingleConnectionPrimaryBD.getConnection();
    }

    public void salvarDataFinal(String data) throws Exception {

        String sql = "insert into finalprojeto (\"datafinal\") values (?)";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, data);
        statement.execute();
        connection.commit();

    }

}
