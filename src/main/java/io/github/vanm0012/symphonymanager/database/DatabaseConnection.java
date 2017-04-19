package io.github.vanm0012.symphonymanager.database;

import java.sql.*;

/**
 * Created by Sheldon on 2017-04-18.
 */
public class DatabaseConnection {
    private Connection connection = null;
    /*private ResultSet resultSet = null;
    private PreparedStatement preparedStatement = null;*/
    private String url = "jdbc:mysql://localhost:3306/symphony";
    private String user = "root";
    private String password = "100scout";

    public DatabaseConnection(){
        try{
            connection = DriverManager.getConnection(url,user,password);
            /*preparedStatement = connection.prepareStatement("select * from movements");
            resultSet = preparedStatement.executeQuery();*/

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Connection getConnection(){
        return connection;
    }
}
