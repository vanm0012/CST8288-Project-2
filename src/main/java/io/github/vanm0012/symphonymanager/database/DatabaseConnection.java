package io.github.vanm0012.symphonymanager.database;

import java.sql.*;

/**
 * This class creates the connection to the database
 */
public class DatabaseConnection {
    private Connection connection = null;
    /*private ResultSet resultSet = null;
    private PreparedStatement preparedStatement = null;*/
    private String url = "jdbc:mysql://localhost:3306/symphony";
    private String user = "root";
    private String password = "100scout";

    /**
     * Default constructor creates connection from set strings
     */
    public DatabaseConnection(){
        try{
            connection = DriverManager.getConnection(url,user,password);
            /*preparedStatement = connection.prepareStatement("select * from movements");
            resultSet = preparedStatement.executeQuery();*/

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Returns the established connection
     * @return Connection to DB
     */
    public Connection getConnection(){
        return connection;
    }
}
