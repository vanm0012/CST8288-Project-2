package io.github.vanm0012.symphonymanager.database;

import io.github.vanm0012.symphonymanager.domain.Composition;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CompositionDAO
{
    private ResultSet resultSet = null;
    private PreparedStatement preparedStatement = null;
    private Connection connection = null;
    private DatabaseConnection dbc;
    private ArrayList<Composition> compositions = null;

    public CompositionDAO(){
        compositions = new ArrayList<>();
        dbc = new DatabaseConnection();
        connection = dbc.getConnection();
        try {
            preparedStatement = connection.prepareStatement("select * from composition");
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

    public ArrayList getCompositions(){
        return compositions;
    }
}
