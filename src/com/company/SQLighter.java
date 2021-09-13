package com.company;

import java.io.File;
import java.sql.*;
import java.lang.*;

public class SQLighter {

    private static SQLighter instance;
    Connection connection;
    Statement  statement;
    ResultSet resultSet;


    private SQLighter() {

        File path_sqlte = new File("src\\com\\company");
        File path_jdbc = new File("lib\\");

        String url = "jdbc:sqlite:" + path_sqlte.getAbsolutePath() + "\\omg.db";
        System.out.println(path_jdbc.getAbsolutePath());

        try {
            Class.forName("org.sqlite.JDBC");
            System.out.println("Connecting to database ...");
            connection = DriverManager.getConnection(url);
            statement = connection.createStatement();
            System.out.println("Connected");
        }
        catch (SQLException | ClassNotFoundException throwables){
            throwables.printStackTrace();
        }
    }

    public static SQLighter getInstance() {
        if (instance == null)
            instance = new SQLighter();
        return instance;
    }

    public void output() throws SQLException{

        String sql_requests = "SELECT * FROM Test";
        resultSet = statement.executeQuery(sql_requests);
        while (resultSet.next()){
            System.out.println(resultSet.getString("name"));
            System.out.println(resultSet.getString("surname"));
        }
    }


    public void insert() throws  SQLException{
        String sql_requests ="INSERT INTO Test (name, surname) VALUES (?, ?)";
        PreparedStatement preparedStatement = connection.prepareStatement(sql_requests);
        preparedStatement.setString(1, "Illia");
        preparedStatement.setString(2, "Ponomarov");

        preparedStatement.executeUpdate();
    }


    public void delete() throws  SQLException{
        String sql_requests = "DELETE FROM Test WHERE id=1";
        int r = statement.executeUpdate(sql_requests);
        System.out.printf("Delete %d\n", r);
    }
    
    

    public void update() throws SQLException{
        String sql_requests = "UPDATE Test SET name=\'IGOR\' WHERE id=1";
        int r = statement.executeUpdate(sql_requests);
        System.out.println();
    }





}

