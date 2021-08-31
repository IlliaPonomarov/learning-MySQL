package com.company;
import java.lang.reflect.InvocationTargetException;
import java.sql.*;
import java.lang.String;


public class DatabaseHandler extends Configs {

           Connection connection;

    {
        //jdbc:mysql://127.0.0.1:3306/?user=root
        try {
            String MS_DRIVER = "com.mysql.jdbc.Driver";
            connection = DriverManager.getConnection("jdbc:mysql://localhost/university", dbUser, dbPass);

            System.out.println("Connected.");

        } catch (SQLException throwables) {
            System.out.println("Connected failed ...");

            throwables.printStackTrace();
        }
    }


    public void output(String txt){
        Statement statement = null;
        try {
            statement = connection.createStatement();


            ResultSet resultSet = statement.executeQuery("SELECT * FROM " + txt);

            while (resultSet.next()){
                System.out.println(resultSet.getString("names"));
            }




        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

}
