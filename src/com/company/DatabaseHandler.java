package com.company;
import java.lang.reflect.InvocationTargetException;
import java.sql.*;
import java.lang.String;


public class DatabaseHandler extends Configs {

        Connection connection;
        Statement statement;


    {
        //jdbc:mysql://127.0.0.1:3306/?user=root
        try {
            String MS_DRIVER = "com.mysql.jdbc.Driver";
            connection = DriverManager.getConnection("jdbc:mysql://localhost/university", dbUser, dbPass);
            statement = connection.createStatement();

            System.out.println("Connected.");

        } catch (SQLException throwables) {
            System.out.println("Connected failed ...");

            throwables.printStackTrace();
        }
    }
    public void output(String txt){

        try {



            ResultSet resultSet = statement.executeQuery("SELECT * FROM " + txt);

            while (resultSet.next()){
                System.out.println(resultSet.getString("namet"));
            }


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }


    public void insertSQL(String name, String surname, String subjects, String gender){
        try {
            String insert = "INSERT INTO " + dbTeacher
                    + "(namet, surnamet, subjectst, gender)" +  " VALUES(?, ?, ?, ?)";


            PreparedStatement preparedStatement = connection.prepareStatement(insert);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, surname);
            preparedStatement.setString(3, subjects);
            preparedStatement.setString(4, gender);

            int row = preparedStatement.executeUpdate();
            System.out.printf("Was update: %d", row);



            Statement statement = connection.createStatement();




        }catch (SQLException throwables){
            throwables.printStackTrace();
        }
    }

}
