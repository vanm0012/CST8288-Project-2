package io.github.vanm0012.symphonymanager.database;


import io.github.vanm0012.symphonymanager.domain.Composition;
import io.github.vanm0012.symphonymanager.domain.Movement;

import java.sql.*;
import java.util.ArrayList;

/**
 * This class pulls the movement data from a database and saves in the proper composition
 */
public class MovementDAO
{
    private ResultSet resultSet = null;
    private PreparedStatement preparedStatement = null;
    private Connection connection = null;
    private ArrayList<Composition> compositions = null;
    private CompositionDAO compdao = null;


    /**
     * Default constructor makes connection, sets compositions list from compositiondao,pulls and saves data from database
     */
    public MovementDAO(){
        makeConnection(false);
        runQuery();
    }

    /**
     * same as default except uses given compositions list
     * @param comp
     */
    public MovementDAO(ArrayList<Composition> comp){
        compositions = comp;
        makeConnection(true);
        runQuery();
    }

    /**
     * creates the connection to database and sets compositions list if not given one
     * @param Comp boolean that determines if compositions list was given
     */
    public void makeConnection(boolean Comp){
        if(Comp == false){
            compdao = new CompositionDAO();
            compositions = compdao.getCompositions();
            connection = compdao.getConnection();
        }else{
            DatabaseConnection dbc = new DatabaseConnection();
            connection = dbc.getConnection();
        }
    }

    /**
     * runs query on database and saves data in the proper composition
     */
    public void runQuery(){
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
