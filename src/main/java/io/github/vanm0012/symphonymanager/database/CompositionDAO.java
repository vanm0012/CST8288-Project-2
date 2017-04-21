package io.github.vanm0012.symphonymanager.database;

import io.github.vanm0012.symphonymanager.domain.Composition;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * This class collects all the data for the compositions from the database
 */
public class CompositionDAO
{
    private ResultSet resultSet = null;
    private PreparedStatement preparedStatement = null;
    private Connection connection = null;
    private DatabaseConnection dbc;
    private ArrayList<Composition> compositions = null;

    /**
     * Default constructor runs everything for data collection and setting
     */
    public CompositionDAO(){
        compositions = new ArrayList<>();
        setConnection();
        runQuery("select * from composition");
    }

    /**
     * gets connection to database
     */
    public void setConnection(){
        dbc = new DatabaseConnection();
        connection = dbc.getConnection();
    }

    /**
     * Runs query to collect data and set it
     * @param s query string to be run
     */
    public void runQuery(String s){
        try {
            preparedStatement = connection.prepareStatement(s);
            resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                compositions.add(new Composition());
                compositions.get(compositions.size()-1).setComposer(resultSet.getString(1));
                compositions.get(compositions.size()-1).setName(resultSet.getString(2));
                /*System.out.println(resultSet.getString(1));
                System.out.println( compositions.get(compositions.size()-1).getComposer());*/
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Returns the current compositions list
     * @return Current compositions list
     */
    public ArrayList getCompositions(){
        return compositions;
    }

    /**
     * Returns the connection made to the database
     * @return Connection to database
     */
    public Connection getConnection(){return connection;}
}
