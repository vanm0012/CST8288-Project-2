package io.github.vanm0012.symphonymanager.database;


import io.github.vanm0012.symphonymanager.domain.Composition;
import io.github.vanm0012.symphonymanager.domain.Movement;

import java.sql.*;
import java.util.ArrayList;

public class MovementDAO
{
    private ResultSet resultSet = null;
    private PreparedStatement preparedStatement = null;
    private Connection connection = null;
    private DatabaseConnection dbc;
    private ArrayList<Composition> compositions = null;
    private CompositionDAO compdao = null;

    public MovementDAO(){
        compdao = new CompositionDAO();
        compositions = compdao.getCompositions();
        dbc = new DatabaseConnection();
        connection = dbc.getConnection();


        try {
            preparedStatement = connection.prepareStatement("select * from movements");
            resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                for(int i = 0;i<compositions.size();i++){
                    //System.out.println(compositions.get(i).getName());
                    if(compositions.get(i).getName().equalsIgnoreCase(resultSet.getString(2))){
                        compositions.get(i).addMovement(resultSet.getString(4),resultSet.getInt(3));
                        System.out.println(compositions.get(i).getMovements().get(compositions.get(i).getMovements().size() - 1).getName());
                    }
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }


    }
}
