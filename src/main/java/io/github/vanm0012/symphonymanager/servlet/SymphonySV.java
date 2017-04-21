package io.github.vanm0012.symphonymanager.servlet;

import com.google.gson.Gson;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;

public class SymphonySV extends HttpServlet
{
    public void init(ServletConfig cfg) throws ServletException
    {
        super.init(cfg);
    }

    public void destroy()
    {
        super.destroy();
    }

    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        doGet(req, resp);
    }

    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        String uri = req.getRequestURI();
        String command = uri.split("/")[uri.split("/").length - 1];

        switch (command)
        {
            case "composers":
                getComposers(req, resp);
                break;
        }
    }

    private Connection getConnection() throws SQLException
    {
        Connection connection = null;

        try
        {
            Class.forName("org.mariadb.jdbc.Driver");
           connection = DriverManager.getConnection(
              "jdbc:mysql://localhost:3306/symphony",
                    "symphony",
                    "symphonypass"
            );
        }
        catch (SQLException | ClassNotFoundException e)
        {
            throw new SQLException(e);
        }

        return connection;
    }

    private void getComposers(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        ArrayList<String> composers = new ArrayList<>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        // check name param
        String composerName = req.getParameter("name");
        if (composerName != null)
        {
            getCompositions(req, resp, composerName);
            return;
        }

        // make query to db
        try
        {
            connection = getConnection();
            preparedStatement = connection.prepareStatement("SELECT DISTINCT composer FROM Composition;");
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next())
            {
                composers.add(resultSet.getString(1));
            }
        }
        catch (SQLException e)
        {
            throw new ServletException("Could not connect to the database", e);
        }
        finally
        {
            try { resultSet.close(); } catch (Exception e) { /* ignored */ }
            try { preparedStatement.close(); } catch (Exception e) { /* ignored */ }
            try { connection.close(); } catch (Exception e) { /* ignored */ }
        }

        // create response
        resp.setContentType("text/json");
        PrintWriter out = new PrintWriter(resp.getOutputStream());

        Gson gson = new Gson();
        out.write(gson.toJson(composers));


        out.flush();
        out.close();
    }

    private void getCompositions(HttpServletRequest req, HttpServletResponse resp, String composerName) throws ServletException, IOException
    {
        HashMap<String, ArrayList<String>> compositions = new HashMap<>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        // make query to db
        try
        {
            connection = getConnection();
            preparedStatement = connection.prepareStatement("SELECT compositionName FROM Composition WHERE composer = ?;");
//                    .replace("?", composerName));
            preparedStatement.setString(1, composerName);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next())
            {
                String compositionName = resultSet.getString(1);
                compositions.put(compositionName, getMovements(compositionName));
            }
        }
        catch (SQLException e)
        {
            throw new ServletException("Could not connect to the database", e);
        }
        finally
        {
            try { resultSet.close(); } catch (Exception e) { /* ignored */ }
            try { preparedStatement.close(); } catch (Exception e) { /* ignored */ }
            try { connection.close(); } catch (Exception e) { /* ignored */ }
        }

        // create response
        resp.setContentType("text/json");
        PrintWriter out = new PrintWriter(resp.getOutputStream());

        Gson gson = new Gson();
        out.write(gson.toJson(compositions));

        out.flush();
        out.close();
    }

    private ArrayList<String> getMovements(String compositionName) throws ServletException
    {
        ArrayList<String> movements = new ArrayList<>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try
        {
            connection = getConnection();
            preparedStatement = connection.prepareStatement("SELECT movementName FROM Movements WHERE compositionName = ?;");
            preparedStatement.setString(1, compositionName);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next())
            {
                movements.add(resultSet.getString(1));
            }
        }
        catch (SQLException e)
        {
            throw new ServletException("Could not connect to the database", e);
        }
        finally
        {
            try { resultSet.close(); } catch (Exception e) { /* ignored */ }
            try { preparedStatement.close(); } catch (Exception e) { /* ignored */ }
            try { connection.close(); } catch (Exception e) { /* ignored */ }
        }

        return movements;
    }
}
